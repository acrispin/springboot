package com.anton.dev.servlet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class AppServletListener implements ServletContextListener {

	private static final Logger LOGGER = LogManager.getLogger(AppServletListener.class);
    private boolean isRunningEvenlog = true;

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			Thread.sleep(1000*3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Create listener");
		LOGGER.info("Create listener");
		LOGGER.info("Main thread: " + Thread.currentThread().getName());
		
		Thread t1 =  new Thread(new Runnable() {
            @Override
            public void run() {
            	startEventLog();
            }
        });
        t1.start();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			Thread.sleep(1000*6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Destroy listener");
		LOGGER.info("Destroy listener");
        isRunningEvenlog = false;		
	}
	
	private void startEventLog() {
        while(isRunningEvenlog) {
        	try {
				Thread.sleep(1000*2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	LOGGER.info("Run thread: " + Thread.currentThread().getName());
        	writteFileTest1();
        	writteFileTest2();
        }
    }
	
	// http://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java/1625263#1625263
	private void writteFileTest1() {
		try {
			// tiene que existir el archivo en la ruta /raizProyecto/test/test1.txt
		    Files.write(Paths.get("test/test1.txt"), "Texto a adicionar ....\n".getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			LOGGER.error(e);
		}
	}
	
	// http://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java/1625263#1625263
	// https://www.mkyong.com/java/how-to-append-content-to-file-in-java/
	private void writteFileTest2() {
		try(FileWriter fw = new FileWriter("test/test2.txt", true); // no es necesario que exista el archivo pero si la ruta /raizProyecto/test/
		    BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter out = new PrintWriter(bw)) {
			out.println("una linea de texto");
		    out.println("otra linea de texto");
		} catch (IOException e) {
			LOGGER.error(e);
		}
	}
}
