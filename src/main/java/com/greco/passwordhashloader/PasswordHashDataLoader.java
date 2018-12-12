package com.greco.passwordhashloader;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.stream.Stream;

@Component
@Slf4j
public class PasswordHashDataLoader {

    @Value("${passwordhash.filelocation}")
    private String fileName;

    @Value("${passwordhash.notifyInterval}")
    private int notifyInterval;

    @Autowired
    AsyncJDBCInserter asyncJDBCInserter;

    private static final String PROCESSING_MESSAGE_START = "FINISHED PROCESSING: ";
    private static final String PROCESSING_MESSAGE_END = " records total";
    private static final String HASH_AND_FREQUEUNCY_SEPARATOR = ":";
    private static final String INTERIM_PROCESSING_MESSAGE_START = "Processed ";
    private static final String INTERIM_PROCESSING_MESSAGE_END = " records";


   long counter;


    public void readFileandInsertdData() throws Exception{

        try (Stream inputStream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
            inputStream.forEach(hash -> {
                try {
                    insertData(String.valueOf(hash));
                } catch (Exception e) {
                    log.debug(ExceptionUtils.getStackTrace(e));
                }
            });
        }
        log.info(PROCESSING_MESSAGE_START + counter + PROCESSING_MESSAGE_END);
    }

    protected void insertData(String line) throws Exception{
        asyncJDBCInserter.insertData(StringUtils.substringBefore(line,HASH_AND_FREQUEUNCY_SEPARATOR));
        counter++;
        if ((counter % notifyInterval) == 0){
            log.info(INTERIM_PROCESSING_MESSAGE_START+ counter + INTERIM_PROCESSING_MESSAGE_END);
        }
    }


}
