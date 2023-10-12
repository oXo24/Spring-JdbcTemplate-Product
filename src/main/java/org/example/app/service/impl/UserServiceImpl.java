package org.example.app.service.impl;

import org.example.app.repository.impl.UserRepositoryImpl;
import org.example.app.entity.user.User;
import org.example.app.exceptions.ProductDataException;
import org.example.app.service.BaseService;
import org.example.app.utils.Constants;
import org.example.app.utils.validator.EmailValidator;
import org.example.app.utils.validator.IdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service("userService")
public class UserServiceImpl implements BaseService<User> {

    @Autowired
    User user;
    @Autowired
    UserRepositoryImpl repoImpl;

    Map<String, String> errors = new HashMap<>();

    @Override
    public String create(User user) {
        validateData(user);
        if (!errors.isEmpty()) {
            try {
                throw new ProductDataException("Check inputs", errors);
            } catch (ProductDataException e) {
                return e.getErrors(errors);
            }
        }

        if (repoImpl.create(user)) {
            return Constants.DATA_INSERT_MSG;
        } else {
            return Constants.SMTH_WRONG_MSG;
        }
    }

    @Override
    public String getAll() {
        Optional<List<User>> optional = repoImpl.getAll();
        if (optional.isPresent()) {
            AtomicInteger count = new AtomicInteger(0);
            StringBuilder stringBuilder = new StringBuilder();
            List<User> list = optional.get();
            list.forEach((user) ->
                    stringBuilder.append(count.incrementAndGet())
                            .append(") ")
                            .append(user.toString())
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
        Optional<User> optional = repoImpl.getById(Integer.parseInt(id));
        if (optional.isEmpty()) {
            return Constants.DATA_ABSENT_MSG;
        } else {
            User user = optional.get();
            return user.toString();
        }
    }

    @Override
    public String update(User user) {
        validateData(user);
        validateId(String.valueOf(user.getId()));
        if (!errors.isEmpty()) {
            try {
                throw new ProductDataException("Check inputs",
                        errors);
            } catch (ProductDataException e) {
                return e.getErrors(errors);
            }
        }

        if (repoImpl.update(user)) {
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
        user.setId(Integer.parseInt(id));
        if (repoImpl.delete(user)) {
            return Constants.DATA_DELETE_MSG;
        } else {
            return Constants.SMTH_WRONG_MSG;
        }
    }

    private void validateData(User user) {
        if (user.getUserName().isEmpty())
            errors.put("user name", Constants.INPUT_REQ_MSG);
        if (user.getName().isEmpty())
            errors.put("name", Constants.INPUT_REQ_MSG);
        if (EmailValidator.isEmailValid(user.getEmail()))
            errors.put("email", Constants.EMAIL_ERR_MSG);
    }

    private void validateId(String id) {
        if (IdValidator.isIdValid(id))
            errors.put("id", Constants.ID_ERR_MSG);
    }
}
