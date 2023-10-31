package utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        sc.setRequestCharacterEncoding("UTF-8");
        sc.setResponseCharacterEncoding("UTF-8");
        DBUtil.initDB();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

