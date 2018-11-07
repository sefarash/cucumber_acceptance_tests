Feature: User account information

Scenario Outline: verify account info
	Given the user is on the login page
	And I login using username "<username>" and password "<password>"
	When the user clicks on the my personal information button
	Then the system should display the user's account information 
		|First Name  |Last Name   |Email      | Password  |
		|<firstname> |<lastname>  |<username> | <password>|

		Examples: 
		| firstname|lastname   |username		                      |password|
		|John      |Smith   	   |nitoyey@dumoac.net                |password|
		|Geraldine |Whitehead  |lectus@sociisnatoquepenatibus.org |password|
		|Inigo     |Montoya    |vel.quam@Donec.org                |password|
#        |Elija     |Baker      |mollis.nec@quama.org              |password|
#        |Hakeem    |Wagner     |gravida.molestie@sagittissemper.ca|password|
#        |Gareth    |Ruiz     |at.risus.Nunc@tempor.ca           |password|
#        |Bryar     |Gilbert  |at@tempusscelerisque.co.uk        |password|
#        |Charity   |Deleon   |tincidunt.tempus@egestas.com      |password|
#        |Cullen    |Stafford |Proin.nisl@acarcuNunc.net         |password|
#        |Hadassah  |Fuentes  |et@ipsumprimisin.net              |password|
#        |Sydney    |Vazquez  |tincidunt.nunc.ac@atiaculis.edu   |password|
#        |Martena   |Murray   |Donec.fringilla@mauris.org        |password|
#        |Quynn     |Rocha    |lorem@enimCurabiturmassa.net      |password|
        