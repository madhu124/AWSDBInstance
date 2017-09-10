package com.yourname.dao;

import com.yourname.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Repository("mysql")
public class MyClientDaoImpl implements ClientDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class StudentRowMapper implements RowMapper<Client> {

        @Override
        public Client mapRow(ResultSet resultSet, int i) throws SQLException {
            Client client = new Client();
            client.setId(resultSet.getInt("id"));
            client.setName(resultSet.getString("name"));
            client.setCity(resultSet.getString("city"));
            return client;
        }
    }


    @Override
    public Collection<Client> getAllClients() {
        // SELECT column_name(s) FROM table_name
        final String sql = "SELECT id, name, city FROM client";
        List<Client> clients = jdbcTemplate.query(sql, new StudentRowMapper());
        return clients;
    }

    @Override
    public Client getClientById(int id) {
        // SELECT column_name(s) FROM table_name where column = value
        final String sql = "SELECT id, name, city FROM client where id = ?";
        Client client = jdbcTemplate.queryForObject(sql, new StudentRowMapper(), id);
        return client;
    }

    @Override
    public void removeClientById(int id) {
        // DELETE FROM table_name
        // WHERE some_column = some_value
        final String sql = "DELETE FROM client WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateClient(Client client) {
        // UPDATE table_name
        // SET column1=value, column2=value2,...
        // WHERE some_column=some_value
        final String sql = "UPDATE client SET name = ?, city = ? WHERE id = ?";
        final int id = client.getId();
        final String name = client.getName();
        final String city = client.getCity();
        jdbcTemplate.update(sql, new Object[]{name, city, id});
    }

    @Override
    public void insertClient(Client client) {

        final String sql = "INSERT INTO client (id,name, city) VALUES (?,?, ?)";
        final int id = client.getId();
        final String name = client.getName();
        final String city = client.getCity();
        jdbcTemplate.update(sql, new Object[]{id,name, city});

    }
}
