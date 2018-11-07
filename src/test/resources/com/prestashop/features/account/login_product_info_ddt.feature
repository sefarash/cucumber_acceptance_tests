Feature: Verify product information 


Scenario Outline: Verify item name 
	Given the user is on the home page 
	When the user selects "<product>" 
	Then product name should be "<product>" 
	And the price should be "<price>" 
	And correct default count should be 1 
	
	Examples: 
		| price   	|product               |
		| $28.98    |Printed Summer Dress  |
		| $26.00  	|Printed Dress         |
		| $160.40  	|Printed Chiffon Dress |
		
Scenario Outline: Verify usernames 
	Given the user is on the login page 
	When I login using username "<username>" and password "<password>" 
	Then users full name "<firstname>" "<lastname>" should be displayed 
	
	Examples: 
		| firstname|lastname  |username		                  |password|
		|John      |Smith   	   |nitoyey@dumoac.net                |password|
		|Geraldine |Whitehead  |lectus@sociisnatoquepenatibus.org |password|
		|Inigo     |Montoya    |vel.quam@Donec.org                |password|
        |Elija     |Baker      |mollis.nec@quama.org              |password|
        |Hakeem    |Wagner     |gravida.molestie@sagittissemper.ca|password|
        |Gareth    |Ruiz     |at.risus.Nunc@tempor.ca           |password|
        |Bryar     |Gilbert  |at@tempusscelerisque.co.uk        |password|
        |Charity   |Deleon   |tincidunt.tempus@egestas.com      |password|
        |Cullen    |Stafford |Proin.nisl@acarcuNunc.net         |password|
        |Hadassah  |Fuentes  |et@ipsumprimisin.net              |password|
        |Sydney    |Vazquez  |tincidunt.nunc.ac@atiaculis.edu   |password|
        |Martena   |Murray   |Donec.fringilla@mauris.org        |password|
        |Quynn     |Rocha    |lorem@enimCurabiturmassa.net      |password|
        
       
Scenario Outline: Product details with map and outline
	Given the user is on the home page
	When the user selects "<product>"
	Then the system should display the product information:
		|name	  |	<product>   |
		|count    |	1	        |
		|condition|	New	        |
		|size	  |	S		    |
		|price    | 	<price>		|  
		
	Examples:
		| price   	|product               |
		| $28.98    |Printed Summer Dress  |
		| $26.00  	|Printed Dress         |
		| $16.40  	|Printed Chiffon Dress |
		
Scenario Outline: verify <page> title
	When the user is on the <page>
	Then the title and url should be:
		|Title|<title>|
		|Url  |<url>  |
		
	Examples: 
		|page      |title           |url                                    |
		|home page | My Store       |http://automationpractice.com/index.php|
		|login page|Login - My Store|http://automationpractice.com/index.php?controller=authentication&back=my-account|










	
		
		
		
		
		
		
		
		
		
		      
        