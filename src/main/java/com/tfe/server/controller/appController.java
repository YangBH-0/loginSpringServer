package com.tfe.server.controller;

import com.google.gson.Gson;
import com.tfe.server.domain.app.LoginRequest;
import com.tfe.server.domain.app.LoginResponse;
import com.tfe.server.domain.app.SignupRequest;
import com.tfe.server.domain.app.SignupResponse;
import com.tfe.server.service.DBservice;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class appController {
    private final DBservice dbservice;

    @Autowired
    public appController(DBservice dBservice) {
        this.dbservice = dBservice;
    }

    @ResponseBody
    @RequestMapping(value = "/app/login",method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public LoginResponse login(@RequestBody  String objJson) {
        System.out.println(objJson);
        Gson gson = new Gson();
        LoginRequest loginRequest = gson.fromJson(objJson, LoginRequest.class);
        LoginResponse loginResponse = dbservice.login(loginRequest);
        System.out.println(loginResponse.getCode());
        return loginResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/app/checkID", method = {
            RequestMethod.POST }, produces = "application/json;charset=utf-8")
    public SignupResponse checkID(@RequestBody  String objJson) {
        Gson gson = new Gson();
        SignupRequest loginRequest = gson.fromJson(objJson, SignupRequest.class);
        System.out.println(objJson);
        SignupResponse signupResponse = dbservice.checkUserID(loginRequest.getInputId());
        return signupResponse;
    }
    @ResponseBody
    @RequestMapping(value = "/app/signup", method = {
            RequestMethod.POST }, produces = "application/json;charset=utf-8")
    public SignupResponse signup(@RequestBody  String objJson) {
        Gson gson = new Gson();
        System.out.println(objJson);
        SignupRequest signupRequest = gson.fromJson(objJson, SignupRequest.class);
        return dbservice.signup(signupRequest);
    }
}
