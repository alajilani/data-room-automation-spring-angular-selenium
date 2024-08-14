package com.ads.robot.controllers;

import com.ads.robot.dto.FunctionDto;
import com.ads.robot.dto.FunctionUpdateDto;
import com.ads.robot.dto.FunctionUpdateResponseDto;
import com.ads.robot.dto.converters.FunctionConverter;
import com.ads.robot.entities.FunctionRobot;
import com.ads.robot.services.FunctionService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping(value = "function")
public class FunctionController {

  @Autowired
  private FunctionService functionService;
  @Autowired
  private FunctionConverter functionConverter;

  @GetMapping(value = "/get/all")
  public List<FunctionDto> getAllFunction() {
    return functionService.getAllFunctions().stream().map(functionConverter::convertToDto)
        .collect(Collectors.toList());
  }

  @GetMapping("/get/{id}")
  public FunctionRobot getFunction(@PathVariable int id) {
    return functionService.getFunction(id);
  }

  @PostMapping(value = "/save")
  public void addFunction(@RequestBody FunctionRobot function) {
    functionService.addFunction(function);
  }

  @PutMapping(value = "/update")
  public void updateFunction(@RequestBody FunctionRobot function) {
    functionService.updateFunction(function);
  }

  @DeleteMapping("/delete/{id}")
  public void deleteFunction(@PathVariable int id) {
    functionService.deleteFunction(id);
  }

  @GetMapping("/init")
  public List<FunctionDto> initFunctions() {
    return functionService.initFuntions().stream().map(functionConverter::convertToDto)
        .collect(Collectors.toList());
  }

  @GetMapping("/get/update")
  public FunctionUpdateDto getUpdatedFunctions() {
    return functionService.getFunctionsUpdate();
  }

  @PostMapping(value = "/update/functions")
  public void updateFunctions(@RequestBody FunctionUpdateResponseDto reponse) {
    functionService.updateFunctions(reponse);
  }
}
