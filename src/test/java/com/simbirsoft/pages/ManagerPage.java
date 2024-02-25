package com.simbirsoft.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagerPage {

  private final WebDriver driver;

  @FindBy(xpath = "//*[contains(@ng-click, 'addCust()')]")
  private WebElement addCustomerButton;

  @FindBy(xpath = "//*[contains(@ng-click, 'openAccount()')]")
  private WebElement openAccountButton;

  @FindBy(xpath = "//*[contains(@ng-click, 'showCust()')]")
  private WebElement showCustButton;

  public ManagerPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
  }

  public void addCustomerButtonClick() {
    addCustomerButton.click();
  }

  public void openAccountButtonClick() {
    openAccountButton.click();
  }
  public void showCustButtonClick() {
    showCustButton.click();
  }

}
