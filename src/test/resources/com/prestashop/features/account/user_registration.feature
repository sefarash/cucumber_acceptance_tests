Feature: Users should be able to login 

Scenario: User registration 
	Given the user is on the login page 
	And the user enters a random email 
	And the user enters personal information 
		|First Name| Kunnka     |
		|Last Name | Admiral    |
		|Address   | 123 Main st|
		|City      | Anytown    |
		|Company   | Volvo      |
		
Scenario: Custom User registration 
	Given the user is on the login page 
	And the user enters a random email 
	And the user enters user information 
		|First Name | Last Name |Company   |Address    |City     |
		| Admiral   |Kunnka     |Volvo     |123 Main st| Anytown |		

# lines below are not implemented
Scenario: user info
	Then correct user info should be displayed
			|First Name | Last Name |Company   |Address    |City     |
			| Admiral   |Kunnka2    |Volvo     |123 Main st| Anytown |
			| Admiral   |Kunnkaw    |Volvo     |123 Main st| Anytown |
			| Admiral   |Kunnkads   |Volvo     |123 Main st| Anytown |
	And I login as this user
		|First Name | Last Name |
		| Admiral   |Kunnka     |
	And I order these
		|name  |count|size|
		|dress |123  |s   |
		|dress |123  |M   |
		|tshirt|1    |M   |
		