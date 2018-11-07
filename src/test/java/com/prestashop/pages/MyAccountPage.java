package com.prestashop.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.prestashop.utilities.Driver;

public class MyAccountPage {
	public MyAccountPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(css = ".account>span")
	public WebElement fullName;
	
	@FindBy(className = "logout")
	public WebElement logout;
	
	@FindBy(css = "a[title='Information']")
	public WebElement myPersonalInformaiton;
	
}
