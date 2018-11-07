package com.prestashop.step_definitions;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.Keys;

import com.github.javafaker.Faker;
import com.prestashop.beans.User;
import com.prestashop.pages.AccountInformationPage;
import com.prestashop.pages.MyAccountPage;
import com.prestashop.pages.RegistrationPage;
import com.prestashop.pages.SigninPage;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.ast.DataTable;

public class UserRegistrationStepDefs {

	SigninPage signInPage = new SigninPage();
	RegistrationPage registrationPage = new RegistrationPage();
	MyAccountPage myAccountPage = new MyAccountPage();

	@Given("the user enters a random email")
	public void the_user_enters_a_random_email() {
		String emailAddress = new Faker().internet().emailAddress();
		signInPage.signupEmail.sendKeys(emailAddress + Keys.ENTER);
	}

	@Given("the user enters personal information")
	public void the_user_enters_personal_information(Map<String, String> user) {
		System.out.println(user);

		registrationPage.firstName.sendKeys(user.get("First Name"));
		registrationPage.lastName.sendKeys(user.get("Last Name"));
		registrationPage.address.sendKeys(user.get("Address"));
		registrationPage.city.sendKeys(user.get("City"));
		registrationPage.company.sendKeys(user.get("Company"));
		System.out.println();
	}

	@Given("the user enters user information")
	public void the_user_enters_user_information(List<User> users) {
		for (User user : users) {
			System.out.println(user.getFistName());
			System.out.println(user.getLastName());
			System.out.println(user.getAddress());
			System.out.println(user.getCity());
			System.out.println(user.getCompany());

			registrationPage.firstName.sendKeys(user.getFistName());
			registrationPage.lastName.sendKeys(user.getLastName());
			registrationPage.address.sendKeys(user.getAddress());
			registrationPage.city.sendKeys(user.getCity());
			registrationPage.company.sendKeys(user.getCompany());
		}
	}

	@When("the user clicks on the my personal information button")
	public void the_user_clicks_on_the_my_personal_information_button() {
		myAccountPage.myPersonalInformaiton.click();
	}

	@Then("the system should display the user's account information")
	public void the_system_should_display_the_user_s_account_information(List<User> users) {
		AccountInformationPage accountInformationPage = new AccountInformationPage();
		User user = users.get(0);
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		
		assertEquals(firstName, accountInformationPage.firstName.getAttribute("value"));
		assertEquals(lastName, accountInformationPage.lastName.getAttribute("value"));

		
		
				
		
	}

}
