package com.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.AsyncContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;


@WebServlet(urlPatterns = "/asyncb", asyncSupported = true)
public class HugeStreamWithThreads extends HttpServlet {

    private long id = 0;
    private String message = "";
    private final ThreadPoolExecutor pool = 
        new ThreadPoolExecutor(1, 1, 50000L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
        // it is explicitly small for demonstration purpose

    private final Thread timer = new Thread(new Runnable() {
        public void run()
        {
            try {
                while(true) {
                    Thread.sleep(1000);
                    sendKeepAlive();
                }
            }
            catch(InterruptedException e) {
                // exit
            }
        }
    });


    class RunJob implements Runnable {
        volatile long lastUpdate = System.nanoTime();
        long id = 0;
        AsyncContext ac;
        RunJob(AsyncContext ac) 
        {
            this.ac = ac;
        }
        public void keepAlive()
        {
            if(System.nanoTime() - lastUpdate > 1000000000L)
                pool.submit(this);
        }
        String formatMessage(String msg)
        {
            StringBuilder sb = new StringBuilder();
            sb.append("id");
            sb.append(id);
            for(int i=0;i<100000;i++) {
                sb.append("data:");
                sb.append(msg);
                sb.append("\n");
            }
            sb.append("\n");
            return sb.toString();
        }
        public void run()
        {
            String message = null;
            synchronized(HugeStreamWithThreads.this) {
                if(this.id != HugeStreamWithThreads.this.id) {
                    this.id = HugeStreamWithThreads.this.id;
                    message = HugeStreamWithThreads.this.message;
                }
            }
            if(message == null)
                message = ":keep-alive\n\n";
            else
                message = formatMessage(message);

            if(!sendMessage(message))
                return;

            boolean once_again = false;
            synchronized(HugeStreamWithThreads.this) {
                if(this.id != HugeStreamWithThreads.this.id)
                    once_again = true;
            }
            if(once_again)
                pool.submit(this);

        }
        boolean sendMessage(String message) 
        {
            try {
                PrintWriter out = ac.getResponse().getWriter();
                out.print(message);
                out.flush();
//                ServletOutputStream out = ac.getResponse().getOutputStream();
//                out.print(message);
//                out.flush();
                lastUpdate = System.nanoTime();
                return true;
            }
            catch(IOException e) {
                ac.complete();
                removeContext(this);
                return false;
            }
        }
    };

    private HashSet<RunJob> asyncContexts = new HashSet<RunJob>();

    @Override
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        timer.start();
    }
    @Override
    public void destroy()
    {
        for(;;){
            try {
                timer.interrupt();
                timer.join();
                break;
            }
            catch(InterruptedException e) {
                continue;
            }
        }
        pool.shutdown();
        super.destroy();
    }


    protected synchronized void removeContext(RunJob ac)
    {
        asyncContexts.remove(ac);
    }

    // GET method is used to establish a stream connection
    @Override
    protected synchronized void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Content-Type header
//        response.setContentType("text/event-stream");
//        response.setCharacterEncoding("utf-8");

        // Access-Control-Allow-Origin header
//        response.setHeader("Access-Control-Allow-Origin", "*");

        final AsyncContext ac = request.startAsync();

        ac.setTimeout(0);
        RunJob job = new RunJob(ac);
        asyncContexts.add(job);
        if(id!=0) {
            pool.submit(job);
        }
    }

    private synchronized void sendKeepAlive()
    {
        for(RunJob job : asyncContexts) {
            job.keepAlive();
        }
    }

    // POST method is used to communicate with the server
    @Override
    protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        request.setCharacterEncoding("utf-8");
        id++;
        message = request.getParameter("m");        
        for(RunJob job : asyncContexts) {
            pool.submit(job);
        }
    }


}