package com.prestashop.step_definitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.prestashop.pages.HomePage;
import com.prestashop.pages.MyAccountPage;
import com.prestashop.pages.RegistrationPage;
import com.prestashop.pages.SigninPage;
import com.prestashop.utilities.BrowserUtils;
import com.prestashop.utilities.ConfigurationReader;
import com.prestashop.utilities.Driver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginStepDefs {

	SigninPage signinPage = new SigninPage();
	String email;

	@Given("the user is on the login page")
	public void the_user_is_on_the_login_page() {
		// open webste
		Driver.getDriver().get(ConfigurationReader.getProperty("url"));
		// go to login page
		new HomePage().signin.click();
	}

	@When("I login using username {string} and password {string}")
	public void i_login_using_username_and_password(String username, String password) {
		signinPage.loginEmail.sendKeys(username);
		signinPage.password.sendKeys(password);
		signinPage.submitLogin.click();
	}

	@Then("users full name {string} {string} should be displayed")
	public void users_full_name_should_be_displayed(String firstName, String lastName) {
		String expected = firstName + " " + lastName;
		String actual = new MyAccountPage().fullName.getText();
		assertEquals(expected, actual);
	}

	@When("the user tries to register an invalid email")
	public void the_user_tries_to_register_an_invalid_email() {
		signinPage.signupEmail.sendKeys("adfgsdfg.com");
		signinPage.signupEmail.submit();
	}

	@Then("the system should display error message {string}")
	public void the_system_should_display_error_message(String message) {
		// signinPage.errorMessage is always present in the page, with or without any
		// error
		// but it only becomes visible when there is an actual error message
		// calling an explicit wait utility method to wait for the visibility of the
		// message
		BrowserUtils.waitForVisibility(signinPage.errorMessage, 5);
		// now that element is fully loaded, we can capture the text
		String actual = signinPage.errorMessage.getText();
		assertEquals(message, actual);

	}

	@When("the user tries to register blank email")
	public void the_user_tries_to_register_blank_email() {
		// submit the form without entering email
		signinPage.signupEmail.sendKeys("");
		signinPage.signupEmail.submit();
	}

	@Given("there is an existing user")
	public void there_is_an_existing_user() {
		Faker fake = new Faker();
		new HomePage().signin.click();
		email = fake.name().username() + "@gmail.com";
		System.out.println(email);
		signinPage.signupEmail.sendKeys(email + Keys.ENTER);

		RegistrationPage registrationPage = new RegistrationPage();
		String firstName = fake.name().firstName();
		registrationPage.firstName.sendKeys(firstName);
		registrationPage.lastName.sendKeys(fake.name().lastName());
		registrationPage.password.sendKeys("password123");
		Address adress = fake.address();
		registrationPage.address.sendKeys(adress.buildingNumber() + " " + adress.streetName());
		registrationPage.city.sendKeys(adress.city());
//		BrowserUtils.waitFor(2);
		Select stateList = registrationPage.stateList();
		stateList.selectByValue("2");
		registrationPage.zipCode.sendKeys(adress.zipCode().substring(0, 5));
		registrationPage.mobilePhone.sendKeys(fake.phoneNumber().cellPhone());
		registrationPage.register.click();
		
		MyAccountPage myAccountPage = new MyAccountPage();
		
		myAccountPage.logout.click();

	}

	@When("the user tries to register the same email")
	public void the_user_tries_to_register_the_same_email() {
		signinPage.signupEmail.sendKeys(email + Keys.ENTER);
	}

}
