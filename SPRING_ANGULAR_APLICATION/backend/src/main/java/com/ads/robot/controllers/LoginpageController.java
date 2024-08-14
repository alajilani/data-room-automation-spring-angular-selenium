package com.ads.robot.controllers;

import com.ads.robot.dto.UserDto;
import com.ads.robot.entities.Loginpage;
import com.ads.robot.services.LoginpageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/loginpage")
public class LoginpageController {

    @Autowired
    LoginpageService loginpageService;

    @PostMapping("/save")
    public ResponseEntity<Loginpage> addloginpage(@RequestBody Loginpage loginpage) {

        Loginpage newloginpage= loginpageService.saveLoginpage(loginpage);
        return new ResponseEntity<>(newloginpage, HttpStatus.CREATED);

    }
}
