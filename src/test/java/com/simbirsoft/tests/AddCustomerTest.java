package com.simbirsoft.tests;

import com.simbirsoft.ConfProperties;
import com.simbirsoft.TestApplication;
import com.simbirsoft.helpers.AlphabetConverter;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;

public class AddCustomerTest extends TestApplication {

  private static final String ADD_CUSTOMER_SUCCESSFUL_ALERT = "Customer added successfully with customer id :";

  @Test
  public void addCustomerTest() {
    managerPage.addCustomerButtonClick();

    addCustPage.lastNameFieldSend(ConfProperties.getProperty("lastName"));
    String postCode = ConfProperties.getProperty("postCode");
    addCustPage.postCodeFieldSend(postCode);
    String firstName = AlphabetConverter.getValue(postCode);
    addCustPage.firstNameFieldSend(firstName);
    addCustPage.addCustomerButtonClick();

    SoftAssertions softAssertions = new SoftAssertions();

    Alert alert = driver.switchTo().alert();
    softAssertions.assertThat(alert.getText()).contains(ADD_CUSTOMER_SUCCESSFUL_ALERT);
    alert.accept();

    managerPage.showCustButtonClick();
    customersPage.searchCustomerFieldSend(firstName);
    String actualFirstName = customersPage.getFirstFirstName(customersPage.getTable());

    softAssertions.assertThat(actualFirstName).isEqualTo(firstName);
    softAssertions.assertAll();
  }

}
