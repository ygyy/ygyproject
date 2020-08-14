package com.glodon.spring.jdbc;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCTest {

    private ApplicationContext ctx = null;

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        namedParameterJdbcTemplate = ctx.getBean(NamedParameterJdbcTemplate.class);

    }
    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }
    @Test
    public void testInsert(){
        String sql = "insert into users(last_name,email) values('aa','aa@glodon.com')";
        jdbcTemplate.update(sql);
    }
    @Test
    public void testUpdate(){
        String sql = "update users set last_name = ? where id = ?";
        jdbcTemplate.update(sql,"cc",70);
    }
    @Test
    public void testBatchUpdate(){
        String sql = "insert into users(last_name,email) values(?,?)";
        List<Object[]> batchArgs = new ArrayList<>();
        batchArgs.add(new Object[]{"AA","AA@glodon.com"});
        batchArgs.add(new Object[]{"BB","BB@glodon.com"});
        batchArgs.add(new Object[]{"CC","CC@glodon.com"});
        batchArgs.add(new Object[]{"DD","DD@glodon.com"});
        batchArgs.add(new Object[]{"EE","EE@glodon.com"});
        jdbcTemplate.batchUpdate(sql,batchArgs);
    }

    @Test
    public void testQueryForObject(){
        String sql = "select id,last_name name,email from users where id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User user = jdbcTemplate.queryForObject(sql,rowMapper,71);
        System.out.println(user);
    }
    @Test
    public void testQueryList(){
        String sql = "select id,last_name name,email from users where id > ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        List<User> users = jdbcTemplate.query(sql,rowMapper,1);
        System.out.println(users);
    }

    @Test
    public void testNamedParameterJdbcTemplate2(){
        String sql = "INSERT INTO users(last_name, email, department_id) VALUES(:name,:email,:departmentId)";
        User user = new User();
        user.setName("XYZ");
        user.setEmail("xyz@sina.com");
        user.setDepartmentId(1);
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
        namedParameterJdbcTemplate.update(sql, paramSource);
    }




}
