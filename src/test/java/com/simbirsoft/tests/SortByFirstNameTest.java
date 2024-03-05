package com.simbirsoft.tests;

import com.simbirsoft.TestApplication;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class SortByFirstNameTest extends TestApplication {

  @Test
  public void sortByFirstNameTest() {
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

    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(firstNamesActual).isEqualTo(firstNamesExpected);
    softAssertions.assertAll();
  }

}
