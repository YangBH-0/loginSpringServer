package com.tfe.server.repository;

import com.tfe.server.domain.UserInfo;
import com.tfe.server.domain.app.LoginRequest;
import com.tfe.server.domain.app.SignupRequest;
import com.tfe.server.repository.mysql.DBConfig;
import com.tfe.server.repository.mysql.DBManager;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MysqlRepository {
    DBManager dbManager;

    public MysqlRepository(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public boolean signUp(SignupRequest signupRequest) throws SQLException {
        dbManager.connectDB();
        String sql = "INSERT INTO android.todayfacialexpression (ID, password, name) VALUES ('"
                + signupRequest.getInputId() + "', '" + signupRequest.getInputPw() + "', '" + signupRequest.getNickname() + "');";
        dbManager.execute(sql);
        dbManager.disconnectDB();
        return true;
    }

    public UserInfo login(LoginRequest loginRequest) throws SQLException {

        UserInfo result = new UserInfo();
        dbManager.connectDB();
        String sql = "SELECT * FROM android.todayfacialexpression WHERE ID='"
                + loginRequest.getInputId() + "' and password='" + loginRequest.getInputPw() + "';";
        ResultSet resultSet = dbManager.executeQuery(sql);
        while (resultSet.next()) {
            result.setUserName(resultSet.getString(3));
        }
        if (result.getUserName() == null) {
            dbManager.disconnectDB();
            System.out.println("check null");
            throw new SQLException();
        }
        dbManager.disconnectDB();
        return result;
    }

    public boolean getIDCheck(String... userInfo) throws SQLException {
        dbManager.connectDB();
        boolean result = true;
        ResultSet resultSet = dbManager.executeQuery("SELECT EXISTS(SELECT * FROM android.todayfacialexpression where ID = '" + userInfo[0] + "' ) as success;");

        while (resultSet.next()) {
            result = resultSet.getBoolean(1);
        }
        dbManager.disconnectDB();
        return result;
    }
}
