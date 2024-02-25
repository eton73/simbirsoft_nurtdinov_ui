package com.simbirsoft.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomersPage {

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
    return driver.findElement(
        By.xpath("//*[@class=\"table table-bordered table-striped\"]/tbody")
    );
  }

  public String getFirstFirstName(WebElement table) {
    WebElement element = table.findElement(By.xpath("tr[1]/td[1]"));
    return element.getText();
  }

  public List<WebElement> getRowsFromTable() {
    return driver.findElements(By.xpath("//*[@class=\"table table-bordered table-striped\"]/tbody/tr"));
  }

  public String getFirstNameByNumberRow(WebElement table, Integer numberRow) {
    WebElement element = table.findElement(
        By.xpath(String.format("tr[%s]/td[1]", numberRow))
    );
    return element.getText();
  }

  public WebElement deleteRow(WebElement table, Integer numberRow) {
    return table.findElement(
        By.xpath(String.format("tr[%s]/td[5]/button", numberRow))
    );
  }

  public void deleteRowClick(WebElement element) {
    element.click();
  }

}
