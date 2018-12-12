package com.greco.passwordhashloader;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class AsyncJDBCInserter {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Value("${passwordhash.insertSql}")
    private String insertSQL;

    @Async
    public void insertData(String theLine){
        jdbcTemplate.update(insertSQL, theLine);
    }
}
