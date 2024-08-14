package com.ads.robot.services;

import com.ads.robot.entities.Configuration;
import com.ads.robot.entities.TestSuite;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GitService {

  private String DELETED_STRING_REGEX = """
      #top-right-header {
          position: fixed;
          top: 0;
          right: 0;
          z-index: 1000;
          width: 12em;
          text-align: center;
      }""";
  private String REPLACEMENT_STRING = """
      #top-right-header {
          display: none;
      }""";

  private String url;
  private String projectName;
  private String password;
  private String relativePath;
  private String homeDirectory;
  private String path;
  private String projectPath;
  private String libPath;


  @Autowired
  private ConfigurationService configurationService;

  @PostConstruct
  public void init() {
    Configuration configuration = this.configurationService.getConfiguration();
    this.url = configuration.getUrl();
    this.projectName = configuration.getProjectName();
    this.password = configuration.getPassword();
    relativePath = "/" + projectName + "/test/";
    homeDirectory = System.getProperty("user.home");
    path = homeDirectory + relativePath;
    projectPath = homeDirectory + "/" + projectName;
    libPath = path + "lib";

  }

  public String getLibPath() {
    return libPath;
  }

  public void updateRemote() {
    StringTokenizer st = new StringTokenizer(url, "@");
    String newUrl = st.nextToken() + ":" + password + "@" + st.nextToken();
    System.out.println(newUrl);

    ProcessBuilder processBuilder = new ProcessBuilder();
    processBuilder.inheritIO();
    try {
      Process process;
      BufferedReader reader;
      String line;
      processBuilder.command(homeDirectory + "/push.bash", projectPath, newUrl);
      process = processBuilder.start();
      process.waitFor();

      reader =
          new BufferedReader(new InputStreamReader(process.getInputStream()));
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }


    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public void removeTestSuiteFromRemote(TestSuite testSuite) {
    StringTokenizer st = new StringTokenizer(url, "@");
    String newUrl = st.nextToken() + ":" + password + "@" + st.nextToken();
    System.out.println(newUrl);

    ProcessBuilder processBuilder = new ProcessBuilder();
    processBuilder.inheritIO();
    try {
      Process process;
      BufferedReader reader;
      String line;
      processBuilder.command(homeDirectory + "/remove.bash", path, testSuite.getName() + ".robot");
      process = processBuilder.start();
      process.waitFor();

      reader =
          new BufferedReader(new InputStreamReader(process.getInputStream()));
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    updateRemote();
  }


}
