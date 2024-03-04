package com.simbirsoft.tests;

import com.simbirsoft.TestApplication;
import com.simbirsoft.helpers.DigitalHelper;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class DeleteCustomerTest extends TestApplication {

  @Test
  public void deleteCustomerTest() {
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
    elementsForDelete.forEach(s -> customersPage.clickButton(s));

    List<WebElement> firstNamesElementsAfterDelete = customersPage.getRowsFromTable();

    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(firstNamesElementsAfterDelete.size()).isNotEqualTo(firstNamesElements.size());

    for (int i = 1; i <= firstNamesElementsAfterDelete.size(); i++) {
      int length = customersPage.getFirstNameByNumberRow(table, i).length();

      softAssertions.assertThat(length).isNotEqualTo(nearestValue);
    }
    softAssertions.assertAll();
  }

}
