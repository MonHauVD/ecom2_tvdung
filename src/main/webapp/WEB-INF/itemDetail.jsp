<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">

        <title>Item Detail</title>

        <style>
            .stars {
                font-size: 30px;
                color: #ccc;
                cursor: pointer;
            }

            .stars span {
                transition: color 0.2s ease;
            }

            .stars .filled {
                color: gold;
            }

            .rating-value {
                display: inline-block;
                margin-left: 10px;
                font-size: 20px;
            }

            body {
                font-family: Arial, sans-serif;
                margin: 20px;
            }
            .product-details {
                margin-bottom: 20px;
            }
            .product-details img {
                width: 200px;
                height: auto;
            }
            .product-info {
                margin-left: 20px;
            }
            .product-info div {
                margin-bottom: 10px;
            }
            .catalog-specific-info {
                margin-top: 20px;
            }
            .rating {
                margin-top: 20px;
            }
            .comment-box {
                width: 100%;
                height: 100px;
                margin-top: 10px;
            }
            .submit-btn {
                margin-top: 20px;
            }
            
            .product-details, .catalog-specific-info {
        width: 80%;
        margin: 20px auto;
        padding: 20px;
        border: 1px solid #ddd;
        border-radius: 8px;
        background-color: #f9f9f9;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        font-family: Arial, sans-serif;
    }

    .product-details img {
        max-width: 200px;
        max-height: 200px;
        display: block;
        margin: 0 auto 20px auto;
    }

    .product-info, .catalog-specific-info {
        margin-top: 20px;
    }

    .product-info div, .catalog-specific-info div {
        padding: 5px 0;
        font-size: 16px;
    }

    .product-info strong, .catalog-specific-info strong {
        color: #333;
    }

    .product-details {
        text-align: center;
    }

    .catalog-specific-info {
        border-top: 1px solid #ccc;
        padding-top: 20px;
    }

    @media (min-width: 768px) {
        .product-info, .catalog-specific-info {
            text-align: left;
        }
    }
    
    .review-table {
        width: 80%;
        margin: 20px auto;
        border-collapse: collapse;
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
        border: 1px solid #ddd;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    .review-table thead {
        background-color: #333;
        color: white;
        text-align: center;
    }

    .review-table th, .review-table td {
        padding: 10px 15px;
        border: 1px solid #ddd;
        text-align: center;
    }

    .review-table tbody tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    .review-table tbody tr:hover {
        background-color: #e9ecef;
    }

    .star {
        color: #FFD700; /* Gold color for filled stars */
        font-size: 16px;
    }

    .star-gray {
        color: #ccc; /* Gray color for unfilled stars */
        font-size: 16px;
    }

    h2 {
        text-align: center;
        margin-top: 20px;
        font-family: Arial, sans-serif;
        color: #333;
    }
        </style>

    </head>
    <body class="bg-light">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="/"> <img
                        src="../images/logo.png"
                        width="auto" height="40" class="d-inline-block align-top" alt="" />
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <h4 style="color: white;">${ username }'s Item Detail</h4>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto"></ul>
                    <ul class="navbar-nav">
                        <li class="nav-item active">
                            <a class="nav-link"  href="/orderlist/">Order List</a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link"  href="/cart/${currentUserId}">Cart</a>
                        </li>
                        <li class="nav-item active"><a class="nav-link" href="/">Home
                                Page</a></li>
                        <li class="nav-item active"><form action="/logout" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="submit" value="Logout" />
                            </form> </li>
                        </li>

                    </ul>

                </div>
            </div>
        </nav>
        <div class="container-fluid">
            <!-- Product Details Section -->
            <div class="product-details">
                <div>
                    <img src="${item.image}" alt="Item Image">
                </div>
                <div class="product-info">
                    <div><strong>Serial No:</strong> ${item.id}</div>
                    <div><strong>Item Name:</strong> ${item.name}</div>
                    <div><strong>Price:</strong> ${item.price}</div>
                    <div><strong>Quantity:</strong> ${item.quantity}</div>
                    <div><strong>Producer:</strong> ${item.producer}</div>
                    <div><strong>Description:</strong> ${item.description}</div>
                </div>
            </div>

            <!-- Catalog Specific Information -->
            <div class="catalog-specific-info">
                <c:choose>
                    <c:when test="${item.catalog == 'book'}">
                        <div><strong>Author:</strong> ${item.author}</div>
                        <div><strong>ISBN:</strong> ${item.isbn}</div>
                        <div><strong>Number of Pages:</strong> ${item.numberPage}</div>
                    </c:when>
                    <c:when test="${item.catalog == 'clothes'}">
                        <div><strong>Size:</strong> ${item.size}</div>
                        <div><strong>Color:</strong> ${item.color}</div>
                        <div><strong>Material:</strong> ${item.material}</div>
                        <div><strong>Gender:</strong> ${item.gender}</div>
                    </c:when>
                    <c:when test="${item.catalog == 'electronic'}">
                        <div><strong>Model:</strong> ${item.model}</div>
                        <div><strong>Type of Machine:</strong> ${item.typeOfMachine}</div>
                        <div><strong>Weight:</strong> ${item.weight}</div>
                        <div><strong>Dimensions:</strong> ${item.dimensions}</div>
                    </c:when>
                    <c:when test="${item.catalog == 'laptop'}">
                        <div><strong>Processor:</strong> ${item.processor}</div>
                        <div><strong>RAM:</strong> ${item.ram}</div>
                        <div><strong>Storage:</strong> ${item.storage}</div>
                        <div><strong>Screen Size:</strong> ${item.screenSize}</div>
                        <div><strong>Operating System:</strong> ${item.operatingSystem}</div>
                    </c:when>
                    <c:when test="${item.catalog == 'mobilephone'}">
                        <div><strong>Model:</strong> ${item.model}</div>
                        <div><strong>RAM:</strong> ${item.ram}</div>
                        <div><strong>Storage:</strong> ${item.storage}</div>
                        <div><strong>Battery:</strong> ${item.battery}</div>
                        <div><strong>Screen Size:</strong> ${item.screenSize}</div>
                        <div><strong>Operating System:</strong> ${item.operatingSystem}</div>
                    </c:when>
                    <c:when test="${item.catalog == 'shoes'}">
                        <div><strong>Size:</strong> ${item.size}</div>
                        <div><strong>Color:</strong> ${item.color}</div>
                        <div><strong>Type:</strong> ${item.type}</div>
                        <div><strong>Gender:</strong> ${item.gender}</div>
                    </c:when>
                </c:choose>
            </div>

             <!-- Part 3: Reviews Section -->
    <h2>Reviews</h2>
    <table class="review-table">
        <thead>
            <tr>
                <th>Customer Name</th>
                <th>Order Date</th>
                <th>Rating</th>
                <th>Comment</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="review" items="${reviews}">
                <tr>
                    <td>${review.customerName}</td>
                    <td>${review.reviewDate}</td>
                    <td>
                        <c:forEach begin="1" end="${review.rating.ordinal() + 1}">
                            <span class="star">&#9733;</span>
                        </c:forEach>
                        <c:forEach begin="${review.rating.ordinal() + 2}" end="5">
                            <span class="star-gray">&#9733;</span>
                        </c:forEach>
                    </td>
                    <td>${review.comment}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>


        </div>


        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
                integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
        <script
            src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
        <script
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>



    </body>
</html>