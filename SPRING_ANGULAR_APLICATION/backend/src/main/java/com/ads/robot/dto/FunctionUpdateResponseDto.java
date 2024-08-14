package com.ads.robot.dto;

import java.util.List;

public class FunctionUpdateResponseDto {

  private List<MissingFunctionDto> missingFunctions;
  private List<FunctionDto> newFunctions;
  private List<FunctionChangeDto> changedFunctions;

  public List<MissingFunctionDto> getMissingFunctions() {
    return missingFunctions;
  }

  public void setMissingFunctions(List<MissingFunctionDto> missingFunctions) {
    this.missingFunctions = missingFunctions;
  }

  public List<FunctionDto> getNewFunctions() {
    return newFunctions;
  }

  public void setNewFunctions(List<FunctionDto> newFunctions) {
    this.newFunctions = newFunctions;
  }

  public List<FunctionChangeDto> getChangedFunctions() {
    return changedFunctions;
  }

  public void setChangedFunctions(List<FunctionChangeDto> changedFunctions) {
    this.changedFunctions = changedFunctions;
  }
}
