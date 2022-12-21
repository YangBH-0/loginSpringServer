package com.tfe.server.repository.mysql;

import lombok.Getter;

@Getter
public class DBConfig {
    protected static final DBConfig instance=new DBConfig();
    private final String ip="DB IP";
    private final int port=0000; // port ex) 80
    private final String schema="your schema name";
    private final String encoding="utf8";
    private final String userid="DB id";
    private final String  password="DB password";
}
