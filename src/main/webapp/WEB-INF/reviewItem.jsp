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

        <title>Order Detail</title>

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
        </style>
        <style>
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
    form {
        width: 60%;
        margin: 20px auto;
        padding: 20px;
        background-color: #f9f9f9;
        border: 1px solid #ddd;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        font-family: Arial, sans-serif;
    }

    .rating,
    .comment {
        margin-bottom: 20px;
    }

    label {
        font-weight: bold;
        color: #333;
        display: block;
        margin-bottom: 10px;
    }

    .stars {
        display: inline-block;
    }

    .star {
        font-size: 20px;
        color: #ccc; /* Gray for unselected stars */
        cursor: pointer;
        transition: color 0.3s ease;
    }

    .star:hover,
    .star.filled {
        color: #FFD700; /* Gold for selected stars */
    }

    textarea {
        width: 100%;
        padding: 10px;
        font-size: 14px;
        border: 1px solid #ccc;
        border-radius: 4px;
        resize: none;
    }

    .btn {
        display: inline-block;
        padding: 10px 20px;
        font-size: 14px;
        font-weight: bold;
        color: white;
        background-color: #28a745;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        text-align: center;
        transition: background-color 0.3s ease;
    }

    .btn:hover {
        background-color: #218838;
    }
</style>        
    </head>
    <body class="bg-light">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="/"> <img
                        src="/images/logo.png"
                        width="auto" height="40" class="d-inline-block align-top" alt="" />
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <h4 style="color: white;">${ username }'s Order</h4>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto"></ul>
                    <ul class="navbar-nav">
                        <li class="nav-item active">
                            <a class="nav-link"  href="orderlist/">Order List</a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link"  href="cart/${currentUserId}">Cart</a>
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
                    <img src="/${item.image}" alt="Item Image">
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
                        <div><strong>Size:</strong> ${item.asize}</div>
                        <div><strong>Color:</strong> ${item.color}</div>
                        <div><strong>Type:</strong> ${item.type}</div>
                        <div><strong>Gender:</strong> ${item.gender}</div>
                    </c:when>
                </c:choose>
            </div>

            <form action="../send_review" method="post">               
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="abcorderId" id="orderId" value="${reviewDto.orderId}"/>
                <input type="hidden" name="abcitemId" id="itemId" value="${reviewDto.itemId}"/>
                <!-- Rating stars -->
                <div class="rating">
                    <label for="rating">Đánh giá (1-5 sao):</label><br>
                    <div class="stars" id="rating-stars">
                        <span class="star" data-value="1">&#9733;</span>
                        <span class="star" data-value="2">&#9733;</span>
                        <span class="star" data-value="3">&#9733;</span>
                        <span class="star" data-value="4">&#9733;</span>
                        <span class="star" data-value="5">&#9733;</span>
                    </div>
                </div>

                <input type="hidden" name="abcrating" id="rating-input"  value = 6/>
                <!-- Comment -->
                <div class="comment">
                    <label for="comment">Bình luận:</label><br>
                    <textarea name="abccomment" id="comment" placeholder="Bình luận (Không bắt buộc)"></textarea>
                </div>

                <!-- Submit button -->
                <button type="submit" class="btn btn-success">Send</button>
            </form>

            <script>
                const stars = document.querySelectorAll('.star');
                const ratingInput = document.getElementById('rating-input');
                let selectedRating = null;

                stars.forEach(star => {
                    star.addEventListener('click', function () {
                        selectedRating = (parseInt(star.getAttribute('data-value')) - 1);
                        ratingInput.value = selectedRating; // Set the hidden input field to the selected rating
                        highlightStars(selectedRating + 1);
//                        console.log("||||||||||");
//                        console.log(document.getElementById('orderId').value);
//                        console.log(document.getElementById('itemId').value);
//                        console.log(document.getElementById('rating-input').value);
//                        console.log(document.getElementById('comment').value);
//                        console.log("End ||||||||||");
                    });

                    // Function to highlight the stars based on rating
                    function highlightStars(rating) {
                        stars.forEach(star => {
                            if (parseInt(star.getAttribute('data-value')) <= parseInt(rating)) {
                                star.classList.add('filled');
                            } else {
                                star.classList.remove('filled');
                            }
                        });
                    }
                });
            </script>


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