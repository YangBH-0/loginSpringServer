package com.tfe.server.repository.mysql;

import java.sql.*;

public class DBManager {
    private String driver="com.mysql.cj.jdbc.Driver";
    private String url;
    private String userid;
    private String password;

    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;

    public DBManager() {
        String ip=DBConfig.instance.getIp();
        int port=DBConfig.instance.getPort();
        String schema=DBConfig.instance.getSchema();
        String encoding=DBConfig.instance.getEncoding();
        this.url=
                "jdbc:mysql://"+ip+":"+port+"/"
                        +schema+"?"+"allowPublicKeyRetrieval=true&"+
                        "useUnicode=true&characterEncoding="+encoding+
                        "&serverTimezone=Asia/Seoul&useSSL=false";
        this.userid=DBConfig.instance.getUserid();
        this.password=DBConfig.instance.getPassword();
    }
    public boolean connectDB() {
        this.conn = null;
        try {
            this.conn = DriverManager.getConnection(this.url, this.userid, this.password);
            System.out.println("Connection Successed  -- mysql --");
            this.stmt = this.conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean disconnectDB() {
        try {
            if (this.stmt != null)
                this.stmt.close();
            if (this.pstmt != null)
                this.pstmt.close();
            if (this.conn != null)
                this.conn.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
        return true;
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        pstmt = conn.prepareStatement(sql);
        return pstmt.executeQuery();
    }
    public boolean executeAsk(String sql)throws SQLException{
        pstmt =conn.prepareStatement(sql);
        ResultSet rs =pstmt.executeQuery();
        rs.next();
        return 1 ==rs.getInt("flag");
    }
    public void executeUpdate(String sql) throws SQLException {
        Statement stmt = this.conn.createStatement();
        stmt.executeUpdate(sql);
    }

    public void execute(String sql) throws SQLException{
        Statement stmt = this.conn.createStatement();
        this.stmt.execute(sql);
    }
}
