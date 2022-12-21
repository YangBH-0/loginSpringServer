package com.tfe.server.service;

import com.tfe.server.domain.app.LoginRequest;
import com.tfe.server.domain.app.LoginResponse;
import com.tfe.server.domain.app.SignupRequest;
import com.tfe.server.domain.app.SignupResponse;
import com.tfe.server.repository.MysqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class DBservice {
    private final MysqlRepository mysqlRepository;
    @Autowired
    public DBservice(MysqlRepository mysqlRepository) {
        this.mysqlRepository = mysqlRepository;
    }

    public LoginResponse login(LoginRequest loginRequest){

        LoginResponse loginResponse = new LoginResponse();
        try {
            loginResponse.setNickname(mysqlRepository.login(loginRequest).getUserName());
            loginResponse.setCode(code.OK);

            if(loginResponse.getNickname() == null){
                loginResponse= new LoginResponse();
                loginResponse.setNickname("");
                loginResponse.setCode(code.UnknownError);
            }
        } catch (SQLException e){
            System.out.println("check sqlException");
            loginResponse.setCode(code.SQLError);
            e.printStackTrace();
            return loginResponse;
        } catch (Exception e){
            loginResponse.setCode(code.UnknownError);
            e.printStackTrace();
        }
        return loginResponse;
    }
    public SignupResponse checkUserID(String id){
        SignupResponse signupResponse = new SignupResponse();
        try {
            signupResponse.setResult(mysqlRepository.getIDCheck(id));
            signupResponse.setCode(code.OK);
        }catch(SQLException e){
            signupResponse.setCode(code.SQLError);
        }catch(Exception e){
            signupResponse.setCode(code.UnknownError);
        }
        return signupResponse;
    }
    public SignupResponse signup(SignupRequest signupRequest){
        SignupResponse signupResponse = new SignupResponse();
        try {
            signupResponse.setResult(mysqlRepository.signUp(signupRequest));
            signupResponse.setCode(code.OK);
        }catch (SQLException e){
            signupResponse.setCode(code.SQLError);
        }catch(Exception e){
            signupResponse.setCode(code.UnknownError);
        }
        return signupResponse;
    }
}
