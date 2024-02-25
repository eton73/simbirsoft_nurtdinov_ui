package com.simbirsoft;

import com.simbirsoft.helpers.AlphabetConverter;
import com.simbirsoft.helpers.DigitalHelper;
import com.simbirsoft.pages.AddCustPage;
import com.simbirsoft.pages.CustomersPage;
import com.simbirsoft.pages.ManagerPage;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestApplication {

  private static final String ADD_CUSTOMER_SUCCESSFUL_ALERT = "Customer added successfully with customer id :";

  public static WebDriver driver;
  public static ManagerPage managerPage;
  public static AddCustPage addCustPage;
  public static CustomersPage customersPage;

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

  @Test
  public void test_1() {
    managerPage.addCustomerButtonClick();

    addCustPage.lastNameFieldSend(ConfProperties.getProperty("lastName"));
    String postCode = ConfProperties.getProperty("postCode");
    addCustPage.postCodeFieldSend(postCode);
    String firstName = AlphabetConverter.getValue(postCode);
    addCustPage.firstNameFieldSend(firstName);
    addCustPage.addCustomerButtonClick();

    Alert alert = driver.switchTo().alert();
    Assertions.assertTrue(alert.getText().contains(ADD_CUSTOMER_SUCCESSFUL_ALERT));
    alert.accept();

    managerPage.showCustButtonClick();
    customersPage.searchCustomerFieldSend(firstName);
    String actualFirstName = customersPage.getFirstFirstName(customersPage.getTable());

    Assertions.assertEquals(firstName, actualFirstName);
  }

  @Test
  public void test_2() {
    managerPage.showCustButtonClick();

    WebElement table = customersPage.getTable();

    List<WebElement> firstNamesElements = customersPage.getRowsFromTable();
    List<String> firstNamesExpected = new ArrayList<>();
    for (int i = 1; i <= firstNamesElements.size(); i++) {
      firstNamesExpected.add(customersPage.getFirstNameByNumberRow(table, i));
    }
    Collections.sort(firstNamesExpected);

    customersPage.firstNameTdClick();
    customersPage.firstNameTdClick();

    List<WebElement> firstNamesElementsWithSort = customersPage.getRowsFromTable();
    List<String> firstNamesActual = new ArrayList<>();
    for (int i = 1; i <= firstNamesElementsWithSort.size(); i++) {
      firstNamesActual.add(customersPage.getFirstNameByNumberRow(table, i));
    }

    Assertions.assertEquals(firstNamesExpected, firstNamesActual);
  }

  @Test
  public void test_3() {
    managerPage.showCustButtonClick();

    WebElement table = customersPage.getTable();

    List<WebElement> firstNamesElements = customersPage.getRowsFromTable();

    int sum = 0;
    Integer[] lengths = new Integer[firstNamesElements.size()];
    for (int i = 0; i < firstNamesElements.size(); i++) {
      String name = customersPage.getFirstNameByNumberRow(table, i + 1);
      sum = sum + name.length();
      lengths[i] = name.length();
    }
    int avg = sum / lengths.length;

    int nearestValue = DigitalHelper.getNearestValue(avg, lengths);

    List<WebElement> elementsForDelete = new ArrayList<>();
    for (int i = 0; i < lengths.length; i++) {
      if (lengths[i] == nearestValue) {
        elementsForDelete.add(customersPage.deleteRow(table, i + 1));
      }
    }
    elementsForDelete.forEach(s -> customersPage.deleteRowClick(s));

    List<WebElement> firstNamesElementsAfterDelete = customersPage.getRowsFromTable();

    Assertions.assertNotEquals(firstNamesElements.size(), firstNamesElementsAfterDelete.size());
    for (int i = 1; i <= firstNamesElementsAfterDelete.size(); i++) {
      int length = customersPage.getFirstNameByNumberRow(table, i).length();

      Assertions.assertNotEquals(nearestValue, length);
    }
  }

  @AfterAll
  public static void exit() {
    driver.quit();
  }

}
