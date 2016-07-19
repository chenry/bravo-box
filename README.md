BravoBox - The Friendliest Movie Rental Ever!
=============================================

**BravoBox** is a friendly box that lets you rent movies... For Free! Of course, we need to make sure
the movies are returned. As such, we do collect a credit card number, expiration date, email, etc.
This is just in case we happen to need to buy another movie using that card should you decide to keep
the movie.

Our budget is small, so we won't have a lot of features but we will need to let the renters obtain a list of all 
movies, search by category, rent and return the movies.

Basics
------

The basic project `master` contains a `POM` that contains all the dependencies that you should need to 
complete the project. Also, a bare bones **Camel** context file is available in `src/main/resources` and `src/test/resources`. There is a basic `RouteBuilder` too. 

You may, also, find the following links helpful.
* [Camel Routes](http://camel.apache.org/routes.html)
* [Camel RouteBuilder](http://camel.apache.org/routebuilder.html)
* [Camel Properties Component](http://camel.apache.org/properties.html)
* [Camel HTTP Component](http://camel.apache.org/http.html)
* [Camel Mail Component](http://camel.apache.org/mail.html)
* [Camel Testing](http://camel.apache.org/testing.html)
* [Camel Spring](http://camel.apache.org/spring.html)
* [OMdb API](http://www.omdbapi.com/)

Assumptions
-----------

Here are some basic assumptions that you will need to know for the code provided in the solution branches. You don't 
have to follow these assumptions if you are writing your own solution from scratch. You will, however, need to know these if you intend to run the code we've presented here.

It is assumed that you will have the following installed.
* Java 8
* Maven
* Linux/OS X - paths will need to be changed for Windows machines

Problem 1
---------

The first thing we have to do is create a search. The search should, somehow, take as input a type. We will solve 
this one together. A sample CSV (our database) is available in `src/test/resources/data`. The solution should search 
through all of the movies in the CSV and return only those that have a type that matches the input. A movie can have multiple types associate with it. These types are separated by a `;`. If the input types is empty, all movies should be returned.

**Input Data**
* Type

**Response Data**
* IMDB ID
* Title
* Type

Problem 2
---------

Now we need allow people to rent the movies. To ensure that the move is eventually returned we will need to gather 
credit card information, email (for notifications), zip and the due date (we will let the calling application set that 
to allow for multi-day rentals).

**Input Data**
* IMDB ID
* Credit Card Number
* Credit Card Expiration
* Zip Code
* Email

**Response Data**
* Approval

Problem 3
---------

Returns need to be made obviously. So, let's implement the reverse of the work we did in **Problem 2**.

**Input Data**
* IMDB ID

**Response Data**
* Returned Status

Problem 4
---------

Before we charge a customer, we should probably notify them that they should return the movie. Being the nicest rental 
service in the world, we need to encourage our customers to think about the other users who might want to view the 
movie.

**Input Data**
* None

**Response Data**
* None

Notes
-----

Some possible solutions to all of these problems are available as branches to this repo. To view the possible solution 
just look at the *next* problems branch. Of course, for problem 4, view `fullVersion`.

 **Repos**
* fullVersion - All Done
* prob1solution - Solution 1
* prob2solution - Solution 2
* prob3solution - Solution 3
* prob4solution - Solution 4