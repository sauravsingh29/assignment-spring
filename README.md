#  Assignment-spring for SS
## Resources
-Google GepCoding Map Service : https://developers.google.com/maps/documentation/geocoding/intro


## Features
Feature:
------------------------------------------------
H2 in memory database | Consumed Google Map api 
------------------------------------------------
- Saved shod details can be visible by access url - http://localhost:8080/h2-console without password
- Used service to find longitude and latitude by address and vice versa
- Accept json request to save in memory database by consuming google geocoding service to find longitude and latitude.
- Accept url paramater 
	1. customerLatitude
	2. customerLongitude

Demo Setup:
------------------------------
1. Checkout project from https://github.com/sauravsingh29/assignment-spring.git
2. Run application as spring boot.
3. For saving and fetching shop details please refere Swagger-UI url : http://localhost:8080/swagger-ui.html
4. Sample request for saving can be found at location : assignment-spring/src/main/resources/sample_request/sample_request.json