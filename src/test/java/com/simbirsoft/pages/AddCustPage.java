package com.simbirsoft.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustPage {

  private final WebDriver driver;

  @FindBy(xpath = "//input[contains(@placeholder, 'First Name')]")
  private WebElement firstNameField;

  @FindBy(xpath = "//input[contains(@placeholder, 'Last Name')]")
  private WebElement lastNameField;

  @FindBy(xpath = "//input[contains(@placeholder, 'Post Code')]")
  private WebElement postCodeField;

  @FindBy(xpath = "//button[@class=\"btn btn-default\"]")
  private WebElement addCustomerSubmit;

  public AddCustPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
  }

  public void firstNameFieldSend(String value) {
    firstNameField.sendKeys(value);
  }

  public void lastNameFieldSend(String value) {
    lastNameField.sendKeys(value);
  }

  public void postCodeFieldSend(String value) {
    postCodeField.sendKeys(value);
  }

  public void addCustomerButtonClick() {
    addCustomerSubmit.click();
  }

}
