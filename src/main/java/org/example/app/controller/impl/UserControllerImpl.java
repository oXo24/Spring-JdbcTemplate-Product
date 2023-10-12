package org.example.app.controller.impl;

import org.example.app.controller.BaseController;
import org.example.app.entity.user.User;
import org.example.app.service.impl.UserServiceImpl;
import org.example.app.utils.Constants;
import org.example.app.utils.starter.AppStarter;
import org.example.app.view.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("userController")
public class UserControllerImpl implements BaseController {

    @Autowired
    UserMenuView menuView;
    @Autowired
    UserCreateView createView;
    @Autowired
    UserReadView readView;
    @Autowired
    UserReadByIdView readByIdView;
    @Autowired
    UserUpdateView updateView;
    @Autowired
    UserDeleteView deleteView;
    @Autowired
    UserServiceImpl serviceImpl;

    public void getOption() {
        int option = Integer.parseInt(menuView.getOption());
        switch (option) {
            case 1 -> create();
            case 2 -> getAll();
            case 3 -> getById();
            case 4 -> update();
            case 5 -> delete();
            case 0 -> menuView.getOutput(Constants.APP_CLOSE_MSG);
        }
    }

    @Override
    public void create() {
        Map<String, String> data = updateView.getData();
        User user = new User(Integer.parseInt(data.get("id")),
                data.get("userName"), data.get("name"), data.get("email"));
        createView.getOutput(serviceImpl.create(user));
        AppStarter.startApp();
    }

    @Override
    public void getAll() {
        readView.getOutput(serviceImpl.getAll());
        AppStarter.startApp();
    }

    @Override
    public void getById() {
        readByIdView.getOutput(serviceImpl
                .getById(readByIdView.getData()));
        AppStarter.startApp();
    }

    @Override
    public void update() {
        Map<String, String> data = updateView.getData();
        User user = new User(Integer.parseInt(data.get("id")),
                data.get("userName"), data.get("name"), data.get("email"));
        updateView.getOutput(serviceImpl.update(user));
        AppStarter.startApp();
    }

    @Override
    public void delete() {
        deleteView.getOutput(serviceImpl
                .delete(deleteView.getData()));
        AppStarter.startApp();
    }
}
