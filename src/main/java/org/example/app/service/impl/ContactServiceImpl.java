package org.example.app.service.impl;

import org.example.app.entity.contact.Product;
import org.example.app.repository.impl.ProductRepositoryImpl;
import org.example.app.exceptions.ProductDataException;
import org.example.app.service.BaseService;
import org.example.app.utils.Constants;
import org.example.app.utils.validator.IdValidator;
import org.example.app.utils.validator.PriceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service("productService")
public class ContactServiceImpl implements BaseService<Product> {

    @Autowired
    Product product;
    @Autowired
    ProductRepositoryImpl repoImpl;

    Map<String, String> errors = new HashMap<>();

    @Override
    public String create(Product product) {
        validateData(product);
        if (!errors.isEmpty()) {
            try {
                throw new ProductDataException("Check inputs", errors);
            } catch (ProductDataException e) {
                return e.getErrors(errors);
            }
        }

        if (repoImpl.create(product)) {
            return Constants.DATA_INSERT_MSG;
        } else {
            return Constants.SMTH_WRONG_MSG;
        }
    }

    @Override
    public String getAll() {
        Optional<List<Product>> optional = repoImpl.getAll();
        if (optional.isPresent()) {
            AtomicInteger count = new AtomicInteger(0);
            StringBuilder stringBuilder = new StringBuilder();
            List<Product> list = optional.get();
            list.forEach((contact) ->
                    stringBuilder.append(count.incrementAndGet())
                            .append(") ")
                            .append(contact.toString())
            );
            return stringBuilder.toString();
        } else return Constants.DATA_ABSENT_MSG;
    }

    @Override
    public String getById(String id) {
        validateId(id);
        if (!errors.isEmpty()) {
            try {
                throw new ProductDataException("Check inputs", errors);
            } catch (ProductDataException e) {
                return e.getErrors(errors);
            }
        }

        Optional<Product> optional = repoImpl.getById(Integer.parseInt(id));
        if (optional.isEmpty()) {
            return Constants.DATA_ABSENT_MSG;
        } else {
            Product product = optional.get();
            return product.toString();
        }
    }

    @Override
    public String update(Product product) {
        validateData(product);
        validateId(String.valueOf(product.getId()));
        if (!errors.isEmpty()) {
            try {
                throw new ProductDataException("Check inputs",
                        errors);
            } catch (ProductDataException e) {
                return e.getErrors(errors);
            }
        }

        if (repoImpl.update(product)) {
            return Constants.DATA_UPDATE_MSG;
        } else {
            return Constants.SMTH_WRONG_MSG;
        }
    }

    @Override
    public String delete(String id) {
        validateId(id);
        if (!errors.isEmpty()) {
            try {
                throw new ProductDataException("Check inputs", errors);
            } catch (ProductDataException e) {
                return e.getErrors(errors);
            }
        }

        product.setId(Integer.parseInt(id));
        if (repoImpl.delete(product)) {
            return Constants.DATA_DELETE_MSG;
        } else {
            return Constants.SMTH_WRONG_MSG;
        }
    }

    private void validateData(Product product) {
        if (product.getName().isEmpty())
            errors.put("name", Constants.INPUT_REQ_MSG);
        if (product.getQuota().isEmpty())
            errors.put("quota", Constants.INPUT_REQ_MSG);
        if (PriceValidator.isPhoneValid(product.getPrice()))
            errors.put("price", Constants.PHONE_ERR_MSG);
    }

    private void validateId(String id) {
        if (IdValidator.isIdValid(id))
            errors.put("id", Constants.ID_ERR_MSG);
    }
}
