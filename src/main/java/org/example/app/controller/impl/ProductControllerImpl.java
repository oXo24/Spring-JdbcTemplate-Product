package org.example.app.controller.impl;

import org.example.app.controller.BaseController;
import org.example.app.entity.contact.Product;
import org.example.app.service.impl.ContactServiceImpl;
import org.example.app.utils.starter.AppStarter;
import org.example.app.utils.Constants;
import org.example.app.view.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("contactController")
public class ProductControllerImpl implements BaseController {

    @Autowired
    ProductMenuView menuView;
    @Autowired
    ProductCreateView createView;
    @Autowired
    ProductReadView readView;
    @Autowired
    ProductReadByIdView readByIdView;
    @Autowired
    ProductUpdateView updateView;
    @Autowired
    ProductDeleteView deleteView;
    @Autowired
    ContactServiceImpl serviceImpl;

    public ProductControllerImpl() {

    }

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

    public void create() {
        String[] data = createView.getData();
        Product product = new Product(data[0], data[1], data[2]);
        createView.getOutput(serviceImpl.create(product));
        AppStarter.startApp();
    }

    public void getAll() {
        readView.getOutput(serviceImpl.getAll());
        AppStarter.startApp();
    }

    public void getById() {
        readByIdView.getOutput(serviceImpl
                .getById(readByIdView.getData()));
        AppStarter.startApp();
    }

    public void update() {
        Map<String, String> data = updateView.getData();
        Product product = new Product(Integer.parseInt(data.get("id")),
                data.get("name"), data.get("quota"), data.get("price"));
        updateView.getOutput(serviceImpl.update(product));
        AppStarter.startApp();
    }

    public void delete() {
        deleteView.getOutput(serviceImpl
                .delete(deleteView.getData()));
        AppStarter.startApp();
    }
}
