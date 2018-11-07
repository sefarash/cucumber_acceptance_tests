package com.prestashop.step_definitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.prestashop.pages.HomePage;
import com.prestashop.pages.ItemPage;

import cucumber.api.java.en.Then;

public class ProductInformationValidationsStepDefs {

	HomePage homePage = new HomePage();
	ItemPage itemPage = new ItemPage();

	@Then("the user should be to see {string}")
	public void the_user_should_be_to_see(String productName) {
		assertTrue(homePage.item(productName).isDisplayed());
	}

	@Then("the user should be able to see following products")
	public void the_user_should_be_able_to_see_following_products(List<String> products) {
		System.out.println(products.size());
		for (String product : products) {
			System.out.println(product);
			assertTrue(homePage.item(product).isDisplayed());
		}

	}

	@Then("the product be available in the following sizes:")
	public void the_product_be_available_in_the_following_sizes(List<String> sizes) {
		System.out.println("number of sizes: " + sizes.size());

		for (String expectedSize : sizes) {
			System.out.println(expectedSize);
		}

		Select sizeSelect = itemPage.size();
		// will contain the actual sizes
		List<String> actualList = new ArrayList<>();

		// get the all options from the dropdown
		// and add the text of the option to the actualList
		for (WebElement optionElement : sizeSelect.getOptions()) {
			actualList.add(optionElement.getText());
		}

		assertEquals(sizes, actualList);

	}
}
