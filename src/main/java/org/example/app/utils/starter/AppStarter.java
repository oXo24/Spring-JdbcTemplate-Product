package org.example.app.utils.starter;

import org.example.app.conf.AppConfig;
import org.example.app.controller.impl.ProductControllerImpl;
import org.example.app.controller.impl.UserControllerImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppStarter {

    public static void startApp() {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        ProductControllerImpl contactController = context.getBean(ProductControllerImpl.class);
        contactController.getOption();

        UserControllerImpl userController = context.getBean(UserControllerImpl.class);
        userController.getOption();

        context.close();
    }
}
