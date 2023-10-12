package org.example.app.repository.impl;

import org.example.app.repository.BaseRepository;
import org.example.app.entity.user.User;
import org.example.app.entity.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

// Named parameters are used in SQL-queries
@Repository("userRepository")
public class UserRepositoryImpl implements BaseRepository<User> {

    /*
    Spring NamedParameterJdbcTemplate — це клас шаблону з базовим набором операцій
    JDBC, що дозволяє використовувати іменовані параметри замість традиційного '?'
    (заповнювачі/placeholders).
    Після заміни іменованих параметрів на стиль JDBC, NamedParameterJdbcTemplate делегує
    обернутому JdbcTemplate свою роботу.
    */
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserRepositoryImpl(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public boolean create(User user) {
        String sql = "INSERT INTO users (user_name, name, email) " +
                "VALUES (:userName, :name, :email)";
        SqlParameterSource paramSource = new MapSqlParameterSource()
                .addValue("userName", user.getUserName())
                .addValue("name", user.getName())
                .addValue("email", user.getEmail());
        return namedParameterJdbcTemplate.update(sql, paramSource) > 0;
    }

    @Override
    public Optional<List<User>> getAll() {
        String sql = "SELECT * FROM users";
        Optional<List<User>> optional;
        try {
            optional = Optional.of(namedParameterJdbcTemplate
                    .query(sql, new UserMapper()));
        } catch (Exception ex) {
            optional = Optional.empty();
        }
        return optional;
    }

    @Override
    public Optional<User> getById(Integer id) {
        String sql = "SELECT * FROM users WHERE id = :id LIMIT 1";
        SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
        Optional<User> optional;
        try {
            optional = Optional.ofNullable(namedParameterJdbcTemplate
                    .queryForObject(sql, paramSource, User.class));
        } catch (Exception ex) {
            optional = Optional.empty();
        }
        return optional;
    }

    @Override
    public boolean update(User user) {
        Optional<User> optional = getById(user.getId());
        if (optional.isEmpty()) return false;
        else {
            String sql = "UPDATE users SET user_name = :userName, name = :name, " +
                    "email = :email WHERE id = :id";
            SqlParameterSource paramSource = new MapSqlParameterSource()
                    .addValue("userName", user.getUserName())
                    .addValue("name", user.getName())
                    .addValue("email", user.getEmail())
                    .addValue("id", user.getId());
            return namedParameterJdbcTemplate.update(sql, paramSource) > 0;
        }
    }

    @Override
    public boolean delete(User user) {
        Optional<User> optional = getById(user.getId());
        if (optional.isEmpty()) return false;
        else {
            String sql = "DELETE FROM users WHERE id = :id";
            SqlParameterSource paramSource =
                    new MapSqlParameterSource("id", user.getId());
            return namedParameterJdbcTemplate.update(sql, paramSource) > 0;
        }
    }

}
