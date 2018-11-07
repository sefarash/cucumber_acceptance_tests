package com.prestashop.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.prestashop.utilities.Driver;

public class AccountInformationPage {
	public AccountInformationPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy (id="firstname")
	public WebElement firstName;
	
	@FindBy (id="lastname")
	public WebElement lastName;
	
	@FindBy (id="email")
	public WebElement email;
	
	@FindBy (id="passwd")
	public WebElement password;
	
	
	
	
}
