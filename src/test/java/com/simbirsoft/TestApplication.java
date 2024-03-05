package com.simbirsoft;

import com.simbirsoft.pages.AddCustPage;
import com.simbirsoft.pages.CustomersPage;
import com.simbirsoft.pages.ManagerPage;
import java.time.Duration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestApplication {

  protected static WebDriver driver;
  protected static ManagerPage managerPage;
  protected static AddCustPage addCustPage;
  protected static CustomersPage customersPage;

  @BeforeAll
  public static void setup() {
    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.get(ConfProperties.getProperty("startPage"));

    managerPage = new ManagerPage(driver);
    addCustPage = new AddCustPage(driver);
    customersPage = new CustomersPage(driver);
  }

  @AfterAll
  public static void exit() {
    driver.quit();
  }

}
