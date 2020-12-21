# kytAssessment

Problem Statement

Write end to end(e2e) Test for the Product purchase flow

On the homepage of https://amazon.in, there is a search box, type some product (say Headphone) and the apply the below filters

Brand : SONY

Enter Min Max Price 5000 - 20000

Headphones Features : Noise Canceling

Discounts- 10% or more

sort by price High to low

Choose nth product from page

Add to Cart

Proceed to buy

Login into amazon 

Navigate to all until the point we have to pay 

Notes:
----------------

Experience in UIautomation using Cypress/Nightwatch/Protractor, etc

Code structuring such that we can reuse code snippets/functions

Should be able to run e2e’s in  Chrome/FF/Safari

The result of the tests to be written to the file system in a configured folder

Also feel free to improvise and recommend ideas as you work on the assignment

Solution: 
---------------

A Selenium (v3.1) TestNG (v7.3) based solution is created in this project to run the E2E UI automation for product purchase in Amazon Website.

The actual test is modularized to have TestBase and CommonConstants file to minimize the changes needed in the actual test code in case of any further modifications.

There is also further scope for improvement in the code using Page Object Models (POMs) by knowing the pages we are going to encounter in the purchase flow. 

