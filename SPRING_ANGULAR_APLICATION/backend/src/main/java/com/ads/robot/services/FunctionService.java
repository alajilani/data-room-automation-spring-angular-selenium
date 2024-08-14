package com.ads.robot.services;

import com.ads.robot.dto.FunctionChangeDto;
import com.ads.robot.dto.FunctionDto;
import com.ads.robot.dto.FunctionUpdateDto;
import com.ads.robot.dto.FunctionUpdateResponseDto;
import com.ads.robot.dto.MissingFunctionDto;
import com.ads.robot.dto.converters.FunctionConverter;
import com.ads.robot.dto.converters.LibraryConverter;
import com.ads.robot.entities.FunctionRobot;
import com.ads.robot.entities.FunctionRobot;
import com.ads.robot.entities.Library;
import com.ads.robot.entities.TestCaseRow;
import com.ads.robot.repositories.FunctionRepository;
import com.ads.robot.repositories.TestCaseRowRepository;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FunctionService {

  @Autowired
  private FunctionRepository functionRepository;
  @Autowired
  private FunctionConverter functionConverter;
  @Autowired
  private LibraryConverter libraryConverter;
  @Autowired
  private TestCaseRowRepository testCaseRowRepository;
  @Autowired
  private GitService gitService;
  @Autowired
  private LibraryService libraryService;
  @Autowired
  private TestSuiteService testSuiteService;

  @Transactional
  public List<FunctionRobot> getAllFunctions() {
    return functionRepository.findAll();
  }

  public FunctionRobot getFunction(int id) {
    return functionRepository.findById(id).get();
  }

  public void addFunction(FunctionRobot function) {
    functionRepository.save(function);
  }

  public void updateFunction(FunctionRobot function) {
    functionRepository.save(function);
  }

  public void deleteFunction(int id) {
    functionRepository.deleteById(id);
  }

  public List<FunctionRobot> initFuntions() {
    List<FunctionRobot> functions = parsePythonLib();
    functionRepository.deleteAll();
    return functionRepository.saveAll(functions);
  }

  private List<FunctionRobot> parsePythonLib() {
    List<FunctionRobot> functions = new ArrayList<>();
    getPythonFileList(gitService.getLibPath()).forEach(path -> {
      functions.addAll(getFunctionsFromPythonFile(path));
    });
    return functions;
  }

  private List<String> getPythonFileList(String path) {
    File file = new File(path);
    List<String> pythonFilesPaths = new ArrayList<>();
    String[] paths = file.list();
    for (String p : paths) {
      if (p.endsWith(".py")) {
        pythonFilesPaths.add(path + "/" + p);
      }
    }
    return pythonFilesPaths;
  }

  private List<FunctionRobot> getFunctionsFromPythonFile(String path) {
    List<FunctionRobot> functions = new ArrayList<>();
    int start = path.lastIndexOf("/"), end = path.indexOf(".py");
    String libraryName = path.substring(start + 1, end);
    Library library = libraryService.getLibrary(libraryName);
    if (library == null) {
      library = new Library();
      library.setName(libraryName);
      library = libraryService.saveLibrary(library);
    }
    try {
      Scanner scanner = new Scanner(new File(path));
      while (scanner.hasNextLine()) {
        String Line = scanner.nextLine();
        if (Line.contains("@keyword")) {
          String func = "";
          while (scanner.hasNextLine() && !func.contains("def ")) {
            func = scanner.nextLine();
          }
          System.out.println(func);
          FunctionRobot function = extractFunctionFromString(func);
          function.setLibrary(library);
          functions.add(function);
        }
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return functions;
  }

  private FunctionRobot extractFunctionFromString(String code) {
    FunctionRobot function = new FunctionRobot();
    StringTokenizer st1 = new StringTokenizer(code, "(");
    String p1 = st1.nextToken();
    String parameters = st1.nextToken();
    StringTokenizer st2 = new StringTokenizer(p1, " ");
    st2.nextToken();
    String functionName = st2.nextToken();
    List<String> parameterList = new ArrayList<>();
    StringTokenizer st3 = new StringTokenizer(parameters, ",");
    functionName = functionName.replaceAll("_", " ");
    System.out.println("FUNCTION NAME :" + functionName);
    System.out.println("LABELS :");
    st3.nextToken();
    while (st3.hasMoreTokens()) {
      String parameter = st3.nextToken();
      parameter = parameter.replaceAll("\\s+", "");
      if (!st3.hasMoreTokens()) {
        parameter = parameter.substring(0, parameter.length() - 2);
      }
      int index = parameter.indexOf("=");
      if (index > 0) {
        parameter = parameter.substring(0, index);
      }
      parameterList.add(parameter);
      System.out.println(parameter);
    }
    function.setName(functionName);
    function.setParameters(parameterList);
    return function;
  }

  public FunctionUpdateDto getFunctionsUpdate() {
    FunctionUpdateDto functionUpdateDto = new FunctionUpdateDto();
    List<FunctionRobot> newFunctions = parsePythonLib();
    List<FunctionRobot> oldFunctions = functionRepository.findAll();
    List<FunctionDto> missingFunctions = oldFunctions.stream()
        .filter(function -> !newFunctions.contains(function))
        .map(functionConverter::convertToDto).collect(Collectors.toList());
    List<FunctionChangeDto> changedFunctions = new ArrayList<>();
    newFunctions.stream().forEach(function -> {
      for (FunctionRobot oldFunction : oldFunctions) {
        if (function.equals(oldFunction)) {
          function.setId(oldFunction.getId());
          if (!function.getParameters().equals(oldFunction.getParameters())) {
            FunctionChangeDto functionChangeDto = new FunctionChangeDto();
            functionChangeDto.setId(oldFunction.getId());
            functionChangeDto.setName(oldFunction.getName());
            functionChangeDto.setOldParameters(oldFunction.getParameters());
            List<String> parameterMapping = new ArrayList<>();
            for (int i = 0; i < function.getParameters().size(); i++) {
              if (i < oldFunction.getParameters().size()) {
                parameterMapping.add(oldFunction.getParameters().get(i));
              } else {
                parameterMapping.add("default");
              }
            }
            functionChangeDto.setParameterMapping(oldFunction.getParameters());
            functionChangeDto.setNewParameters(function.getParameters());
            functionChangeDto.setLibraryDto(libraryConverter.convertToDto(function.getLibrary()));
            changedFunctions.add(functionChangeDto);
          }
        }
      }
    });
    functionUpdateDto.setMissingFunctions(missingFunctions);
    functionUpdateDto.setChangedFunctions(changedFunctions);
    List<FunctionDto> newFunctionDtos = newFunctions.stream().map(functionConverter::convertToDto)
        .collect(Collectors.toList());
    functionUpdateDto.setNewFunctions(newFunctionDtos);
    return functionUpdateDto;
  }

  public void updateFunctions(FunctionUpdateResponseDto response) {
    List<FunctionRobot> newFunctions = response.getNewFunctions().stream()
        .map(functionConverter::convertToEntity).collect(Collectors.toList());
    functionRepository.saveAll(newFunctions);
    Set<Integer> testSuiteSet = new HashSet<>();
    response.getChangedFunctions().forEach(
        functionChangeDto -> testSuiteSet.addAll(updateChangedFunction(functionChangeDto)));
    response.getMissingFunctions().forEach(
        missingFunctionDto -> testSuiteSet.addAll(updateMissingFunction(missingFunctionDto)));
    libraryService.deleteLibraryWithNoFunctions();
    testSuiteSet.forEach(this.testSuiteService::updateTestSuiteFile);
  }

  private Set<Integer> updateMissingFunction(MissingFunctionDto missingFunctionDto) {
    FunctionRobot missingFunction = functionRepository.findById(
        missingFunctionDto.getMissingFunction().getId()).get();
    FunctionRobot newFunction = functionRepository.findByName(missingFunctionDto.getNewFunction().getName());
    List<TestCaseRow> testCaseRows = testCaseRowRepository.findByfunctionId(
        missingFunction.getId());
    testCaseRows.forEach(testCaseRow -> {
      testCaseRow.setFunction(newFunction);
      List<String> newParameters = getNewParameterValues(missingFunction.getParameters(),
          missingFunctionDto.getParameterMapping()
          , testCaseRow.getParameterValues());
      testCaseRow.setParameterValues(newParameters);
      testCaseRowRepository.save(testCaseRow);
    });
    functionRepository.deleteById(missingFunction.getId());
    return testCaseRows.stream()
        .map(testCaseRow -> testCaseRow.getTestCase().getTestSuite().getId()).collect(
            Collectors.toSet());

  }

  private Set<Integer> updateChangedFunction(FunctionChangeDto functionChangeDto) {
    FunctionRobot oldFunction = functionRepository.findById(functionChangeDto.getId()).get();
    List<TestCaseRow> testCaseRows = testCaseRowRepository.findByfunctionId(oldFunction.getId());
    testCaseRows.forEach(testCaseRow -> {
      List<String> newValues = getNewParameterValues(functionChangeDto.getOldParameters(),
          functionChangeDto.getParameterMapping(),
          testCaseRow.getParameterValues());
      testCaseRow.setParameterValues(newValues);
      testCaseRowRepository.save(testCaseRow);
    });
    return testCaseRows.stream()
        .map(testCaseRow -> testCaseRow.getTestCase().getTestSuite().getId()).collect(
            Collectors.toSet());
  }

  private List<String> getNewParameterValues(List<String> oldParameters,
      List<String> parameterMapping, List<String> oldParameterValues) {
    List<String> newParameterValues = new ArrayList<>();
    parameterMapping.forEach(parameter -> {
      int index = oldParameters.indexOf(parameter);
      newParameterValues.add(index > -1 ? oldParameterValues.get(index) : "");
    });
    return newParameterValues;
  }
}