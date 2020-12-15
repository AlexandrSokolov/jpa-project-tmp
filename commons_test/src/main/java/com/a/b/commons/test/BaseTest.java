package com.a.b.commons.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public abstract class BaseTest {

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  @BeforeClass
  public static void initLogging() {
    System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
    //System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "DEBUG");
    System.setProperty("org.apache.commons.logging.simplelog.log.com.a.b", "DEBUG");
  }

  public InputStream testInputStream(String filePath) {
    try {
      String withoutFirstSlash = filePath.startsWith(File.separator) ?
        filePath.substring(File.separator.length()) : filePath;

      URL pathResource = getClass()
        .getClassLoader()
        .getResource(withoutFirstSlash);
      if (pathResource == null) {
        throw new IllegalStateException(
          "Could not find resource for the path in test resources: [" + filePath + "]");
      }
      String absolutePath = new File(pathResource.toURI()).getAbsolutePath();
      return new FileInputStream(absolutePath);
    } catch (FileNotFoundException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  public File tempTestFile(final String fileName){
    try {
      File testTempFolder = folder.newFolder();
      return new File(testTempFolder, fileName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}