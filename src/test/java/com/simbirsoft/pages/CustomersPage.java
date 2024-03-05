package com.simbirsoft.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomersPage {

  private static final String TABLE_XPATH = "//*[@class=\"table table-bordered table-striped\"]/tbody";
  private static final String FIRST_ROW_FIRST_COLUMN_XPATH = "tr[1]/td[1]";
  private static final String ROWS_FROM_TABLE_XPATH = "//*[@class=\"table table-bordered table-striped\"]/tbody/tr";
  private static final String ANY_ROW_FIRST_COLUMN_XPATH = "tr[%s]/td[1]";
  private static final String ANY_ROW_FIFTH_COLUMN_BUTTON_XPATH = "tr[%s]/td[5]/button";

  private final WebDriver driver;

  @FindBy(xpath = "//input[contains(@placeholder, 'Search Customer')]")
  private WebElement searchCustomerField;

  @FindBy(xpath = "//a[contains(text(), 'First Name')]")
  private WebElement firstNameTd;

  public CustomersPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
  }

  public void searchCustomerFieldSend(String value) {
    searchCustomerField.sendKeys(value);
  }

  public void firstNameTdClick() {
    firstNameTd.click();
  }

  public WebElement getTable() {
    return driver.findElement(By.xpath(TABLE_XPATH));
  }

  public String getFirstFirstName(WebElement table) {
    WebElement element = table.findElement(By.xpath(FIRST_ROW_FIRST_COLUMN_XPATH));
    return element.getText();
  }

  public List<WebElement> getRowsFromTable() {
    return driver.findElements(By.xpath(ROWS_FROM_TABLE_XPATH));
  }

  public String getFirstNameByNumberRow(WebElement table, Integer numberRow) {
    WebElement element = table.findElement(
        By.xpath(String.format(ANY_ROW_FIRST_COLUMN_XPATH, numberRow))
    );
    return element.getText();
  }

  public WebElement deleteRow(WebElement table, Integer numberRow) {
    return table.findElement(
        By.xpath(String.format(ANY_ROW_FIFTH_COLUMN_BUTTON_XPATH, numberRow))
    );
  }

  public void clickButton(WebElement element) {
    element.click();
  }

}
