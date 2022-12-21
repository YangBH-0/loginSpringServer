package com.tfe.server;

import com.tfe.server.repository.MysqlRepository;
import com.tfe.server.repository.mysql.DBManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public DBManager dbManager(){return new DBManager();}
/*
    @Bean
    public MysqlRepository mysqlRepository(DBManager dbManager){return new MysqlRepository(dbManager);}*/


}
