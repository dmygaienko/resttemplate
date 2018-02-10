package com.mygaienko;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class JdbcTemplateTest {

    @Test
    public void test() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/documentservice");
//        dataSource.setUrl("jdbc:postgresql://192.168.60.26:5432/documentservice");
        dataSource.setUsername("documentservice");
        dataSource.setPassword("documentservice");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println(jdbcTemplate.queryForList("select * from documentservice.document_configuration"));
    }
}
