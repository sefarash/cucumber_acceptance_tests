Feature: Verify product information

@temp
Scenario Outline: Verify item name
    Given the user is on the home page
    When the user selects "<product>" 
	Then product page title should contain "<product>" 
	And product name should be "<product>" 
	
	Examples: 
	|product              |
	|Printed Summer Dress |
	|Printed Dress        | 
	