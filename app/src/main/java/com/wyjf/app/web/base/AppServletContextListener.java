package com.wyjf.app.web.base;

import com.wyjf.common.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Administrator on 2017/9/4.
 * http://blog.csdn.net/king_is_everyone/article/details/53116744
 */
@WebListener
public class AppServletContextListener implements ServletContextListener {
    private static final Logger log = LoggerFactory.getLogger(AppServletContextListener.class);

    @Value("${static.assets.url}")
    String url;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info("容器初始完成");
        // 静态资源存储URL
        servletContextEvent.getServletContext().setAttribute("static", url);
//        servletContextEvent.getServletContext().setAttribute("url", url);
//        UserRepo repo = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext()).getBean(UserRepo.class);
//        log.info("bean是否为空: {}",repo != null);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
//        log.info("容器已销毁");
    }
}
