package com.ads.robot.services;

import com.ads.robot.Utils.FileManager;
import com.ads.robot.entities.Loginpage;
import com.ads.robot.repositories.LoginpageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class LoginpageService {


    private String path1="/home/alajilani/quest-automated-tests/test/Login/LoginPage.robot";
    @Autowired
    LoginpageRepository loginpageRepository;
    @Autowired
    private FileManager fileManager;
    @Autowired
    private RobotFileGeneratorService robotFileGeneratorService;


    public Loginpage findloginpagebyid (long id ) {
        return loginpageRepository.findById(id);
    }

    public Loginpage saveLoginpage(Loginpage loginpage) {
        loginpage = loginpageRepository.save(loginpage);
        robotFileGeneratorService.generateloginparametrs(loginpage,path1);
        return loginpage;

    }




}
