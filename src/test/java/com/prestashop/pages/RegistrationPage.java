package com.prestashop.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.prestashop.utilities.Driver;

public class RegistrationPage {
	public RegistrationPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy (id="customer_firstname")
	public WebElement firstName;
	
	@FindBy (id="customer_lastname")
	public WebElement lastName;
	
	@FindBy (id="email")
	public WebElement email;
	
	@FindBy (id="passwd")
	public WebElement password;
	
	@FindBy (id="firstname")
	public WebElement firstNameAddress;
	
	@FindBy (id="lastname")
	public WebElement lastNameAddress;
	
	@FindBy (id="address1")
	public WebElement address;
	
	@FindBy (id="city")
	public WebElement city;
	
	@FindBy (id="id_state")
	public WebElement state;
	
	@FindBy (id="postcode")
	public WebElement zipCode;
	
	@FindBy (id="phone_mobile")
	public WebElement mobilePhone;
	
	@FindBy (id="submitAccount")
	public WebElement register;
	
	@FindBy (id="company")
	public WebElement company;
	
	public Select stateList() {
		return new Select(state);
	}
}
