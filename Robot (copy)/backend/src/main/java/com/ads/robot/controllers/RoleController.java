package com.ads.robot.controllers;

import com.ads.robot.entities.Role;
import com.ads.robot.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "role")
public class RoleController {
    @Autowired
    private RoleRepo roleRepo;
    @GetMapping(value = "/get/all")
    public List<Role> getAllTestCase() {
        return roleRepo.findAll();
    }

}
