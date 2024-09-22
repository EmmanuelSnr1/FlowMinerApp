package com.netnimblelabs.flowminer.util;

import com.netnimblelabs.flowminer.services.NLPService;
import com.netnimblelabs.flowminer.services.ProcessMiningService;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import com.netnimblelabs.flowminer.services.RetrofitClient;
import org.apache.log4j.Logger;
import retrofit2.Retrofit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class StartUp implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(StartUp.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.debug("Entering contextInitialized method.");

        // Display ASCII art
        System.out.println(" _______  _        _______ \n"
                + "(  ____ \\( (    /|(  ____ )\n"
                + "| (    \\/|  \\  ( || (    )|\n"
                + "| (_____ |   \\ | || (____)|\n"
                + "(_____  )| (\\ \\) ||     __)\n"
                + "      ) || | \\   || (\\ (   \n"
                + "/\\____) || )  \\  || ) \\ \\__\n"
                + "\\_______)|/    )_)|/   \\__/\n"
                + "                           ");

        // Initialize Hibernate
        try {
            HibernateUtil.getSessionFactory();
            logger.info("Hibernate SessionFactory initialized successfully.");
        } catch (Exception e) {
            logger.error("Failed to initialize Hibernate SessionFactory", e);
        }

        // Initialize NLPService
        try {
            logger.info("NLPService initialized successfully.");
        } catch (Exception e) {
            logger.error("Failed to initialize NLPService", e);
        }

        logger.debug("Exiting contextInitialized method.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.debug("Entering contextDestroyed method.");

        // Close Hibernate SessionFactory
        try {
            HibernateUtil.shutdown();
            logger.info("Hibernate SessionFactory shut down successfully.");
        } catch (Exception e) {
            logger.error("Failed to shut down Hibernate SessionFactory", e);
        }
        AbandonedConnectionCleanupThread.checkedShutdown();
        logger.info("MySQL Abandoned Connection Cleanup Thread shut down successfully.");

        logger.debug("Exiting contextDestroyed method.");
    }

   
}

