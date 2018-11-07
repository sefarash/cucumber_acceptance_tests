package com.prestashop.step_definitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import com.prestashop.pages.HomePage;
import com.prestashop.pages.ItemPage;
import com.prestashop.utilities.Driver;
import com.prestashop.utilities.ExcelUtil;

import cucumber.api.java.en.Then;

public class PromotedItemsStepDefs {
	HomePage homePage = new HomePage();
	ItemPage itemPage = new ItemPage();

	@Then("the system should display the promoted items")
	public void the_system_should_display_the_promoted_items() {
		// read excel file
		ExcelUtil excelObject = new ExcelUtil("src/test/resources/com/prestashop/test-data/Products.xlsx", "Sheet1");
		// get all the rows
		List<Map<String, String>> allRows = excelObject.getDataList();
		// look at each row individually
		for (Map<String, String> row : allRows) {
			// get the value of execute from each row, if yes
			String product = row.get("Product");
			if (row.get("Execute").equalsIgnoreCase("y")) {
				System.out.println("Testing " + product);
				// get the product name and verify it is displayed on the homepage
				assertTrue(homePage.item(product).isDisplayed());
			} else {
				System.out.println("Skip " + product);
			}
		}

	}

	@Then("the item details should be correct")
	public void the_item_details_should_be_correct() {
		// read excel file
		ExcelUtil excelObject = new ExcelUtil("src/test/resources/com/prestashop/test-data/Products.xlsx", "Sheet1");
		// get all the rows
		List<Map<String, String>> allRows = excelObject.getDataList();
		// look at each row individually
		for (int i = 0; i < allRows.size(); i++) {

			Map<String, String> row = allRows.get(i);
			
			// get the value of execute from each row, if yes
			String product = row.get("Product");
			if (row.get("Execute").equalsIgnoreCase("y")) {
				System.out.println("Testing " + product);
				homePage.item(product).click();
				// get the expected values from the excel
				String price = row.get("Price");
				String color = row.get("Color");
				String style = row.get("Styles");
				System.out.println("Price " + price);
				System.out.println("Color " + color);
				System.out.println("Style " + style);
				// actual values from the UI
				String actualPrice = itemPage.price.getText();
				// color element has no text, the color is saved in the name attribute
				String actualColor = itemPage.selectedColor.getAttribute("name");
				String actualStyle = itemPage.styles.getText();
				if (price.endsWith(".0")) {
					price = "$" + price + "0";
				} else {
					price = "$" + price;
				}
				assertEquals(price, actualPrice);
				assertEquals(color, actualColor);
				assertEquals(style, actualStyle);

				// go back to home page
				Driver.getDriver().navigate().back();

				excelObject.setCellData("Pass", "Status", i+1);

			} else {
				System.out.println("Skip " + product);
				excelObject.setCellData("Skipped", "Status", i+1);
			}
		}
	}
}
