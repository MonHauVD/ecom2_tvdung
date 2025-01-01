<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
              integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
        <title>Dashboard</title>

    </head>
    <body>

        <section class="wrapper">
            <div class="container-fostrap">
                <nav class="navbar navbar-expand-lg navbar-light bg-light" >
                    <div class="container-fluid">
                        <a class="navbar-brand" href="/">
                            <img th:src="@{/images/logo.png}"  src="../images/logo.png" width="auto" height="40" class="d-inline-block align-top" alt=""/>
                        </a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>

                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <h4>Welcome ${ username } </h4>
                            <ul class="navbar-nav mr-auto"></ul>
                            <ul class="navbar-nav">
                                <li class="nav-item active">
                                    <a class="nav-link"  href="/orderlist/">Order List</a>
                                </li>
                                <li class="nav-item active">
                                    <a class="nav-link"  href="/cart/${currentUserId}">Cart</a>
                                </li>
                                <li class="nav-item active">
                                    <a class="nav-link" href="/profileDisplay/${currentUserId}" >Profile</a>
                                </li>
                                <li class="nav-item active">
                                    <form action="/logout" method="post">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <input type="submit" value="Logout" />
                                    </form> </li>
                                </li>

                            </ul>

                        </div>
                    </div>
                </nav>                       
                <!-- New Catalog Navigation Bar -->
                <div class="container-fluid bg-light py-2 d-flex justify-content-center">
                    <ul class="nav nav-pills justify-content-between" style="width: 90%;">
                        <li class="nav-item flex-fill text-center">
                            <a href="/catalog/book" class="btn btn-outline-primary w-100"
                               ${bookButton ? 'tabindex="-1" aria-disabled="true"' : ''}>
                                <i class="fas fa-book"></i> Books

                            </a>
                        </li>
                        <li class="nav-item flex-fill text-center">
                            <a href="/catalog/clothes" class="btn btn-outline-primary w-100"
                               ${clothesButton ? 'tabindex="-1" aria-disabled="true"' : ''}>
                                <i class="fas fa-tshirt"></i> Clothes

                            </a>
                        </li>
                        <li class="nav-item flex-fill text-center">
                            <a href="/catalog/electronics" class="btn btn-outline-primary w-100"
                               ${electronicButton ? 'tabindex="-1" aria-disabled="true"' : ''}>
                                <i class="fas fa-tv"></i> Electronics

                            </a>
                        </li>
                        <li class="nav-item flex-fill text-center">
                            <a href="/catalog/laptops" class="btn btn-outline-primary w-100"
                               ${laptopButton ? 'tabindex="-1" aria-disabled="true"' : ''}>
                                <i class="fas fa-laptop"></i> Laptops

                            </a>
                        </li>
                        <li class="nav-item flex-fill text-center">
                            <a href="/catalog/mobilephones" class="btn btn-outline-primary w-100"
                               ${mobilePhoneButton ? 'tabindex="-1" aria-disabled="true"' : ''}>
                                <i class="fas fa-mobile-alt"></i> Mobile Phones

                            </a>
                        </li>
                        <li class="nav-item flex-fill text-center">
                            <a href="/catalog/shoes" class="btn btn-outline-primary w-100"
                               ${shoesButton ? 'tabindex="-1" aria-disabled="true"' : ''}>
                                <i class="fas fa-shoe-prints"></i> Shoes

                            </a>
                        </li>
                    </ul>
                </div>



                <!DOCTYPE html>
                <html lang="en">
                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <title>Perishable Shop</title>
                        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
                        <style>
                            body {
                                padding: 20px;
                            }
                            .card-body {
                                height: 250px; /* Set a fixed height for the card body */
                            }

                            .card-img-top {
                                max-height: 100px; /* Limit the height of the product image */
                                object-fit: contain;
                            }
                            .card-link {
                                text-decoration: none; /* Remove underline */
                                color: inherit; /* Inherit text color */
                            }

                            .card-link:hover .card {
                                box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.2); /* Add shadow on hover */
                            }

                            .card {
                                cursor: pointer; /* Change cursor to pointer */
                            }
                        </style>


                    </head>
                    <body class="bg-light">
                        <header>

                        </header>
                        <main>

                            <div class="container">
                                <h1>Welcome to Việt Dũng's Shop</h1>


                                <div class="row">
                                    <c:forEach var="item" items="${items}">
                                        <div class="col-md-3">
                                            <a href="/detail_item/${item.id}" class="card-link">
                                                <div class="card mb-4">
                                                    <img class="card-img-top" src="${item.image}" alt="Product 1">
                                                    <div class="card-body">
                                                        <b> <h4 class="card-title">${item.name}</h4></b>
                                                        <h5 class="card-text">Category: ${item.catalog}</h5>
                                                        <h5 class="card-text">Price: ${item.price}</h5>
                                                        <p class="card-text">Description: ${item.description}</p>
                                                        <h5 class="card-text">Item left: ${item.quantity}</h5>
                                                        <a href="/addToCart/${item.id}" class="btn btn-primary">Add to Cart</a>
                                                    </div>
                                                </div>
                                            </a>
                                        </div> </c:forEach>
                                </div>

                            </div>
                        </main>
                        <footer>
                            <div class="container">
                                <p>&copy; 2024 Viet Dung's Shop. All rights reserved

                                    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
                                    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
                                    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
                                    </body>
                                    </html>