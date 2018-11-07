package com.prestashop.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.prestashop.utilities.Driver;

public class SigninPage {
	public SigninPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(id = "email_create")
	public WebElement signupEmail;
	
	@FindBy(id = "email")
	public WebElement loginEmail;
	
	@FindBy(id = "passwd")
	public WebElement password;
	
	@FindBy(id = "SubmitLogin")
	public WebElement submitLogin;
	
	@FindBy(id = "create_account_error")
	public WebElement errorMessage;
	
	
	
	
}
