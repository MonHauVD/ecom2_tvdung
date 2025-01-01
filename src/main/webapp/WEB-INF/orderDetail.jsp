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
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            .order-details {
                width: 100%;
                /*max-width: 600px;*/
                margin: 20px auto;
                border: 1px solid #ddd;
                border-radius: 5px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                padding: 20px;
                background-color: #f9f9f9;
            }
            .order-header {
                display: grid;
                grid-template-columns: 1fr 1fr 1fr; /* Three equal columns */
                font-weight: bold;
                font-size: 1.2em;
                margin-bottom: 10px;
                text-align: center;
            }
            .order-header div {
                padding: 5px 0;
            }
            .order-section {
                margin-bottom: 10px;
            }
            .order-section table {
                width: 100%;
                border-collapse: collapse;
            }
            .order-section th,
            .order-section td {
                text-align: left;
                padding: 8px;
                border: 1px solid #ddd;
            }
            .order-section th {
                background-color: #f1f1f1;
            }
            .separator {
                height: 2px;
                background-color: #ddd;
                margin: 20px 0;
            }
        </style>
        <title>Order</title>
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
                <h4 style="color: white;">${ username }'s Order</h4>
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

            <div class="order-details">
                <!-- Order Header -->
                <div class="order-header">
                    <div>Order</div>
                    <div>ID: ${order.orderId}</div>
                    <div>State: ${order.state}</div>
                </div>

                <!-- Order Section -->
                <div class="order-section">
                    <table>
                        <thead>
                            <tr>
                                <th>Date</th>
                                <th>Shipment</th>
                                <th>Payment</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <div>Order Date: <span>${order.orderDate}</span></div>
                                    <div>Receive Date: <span>${order.receiveDate}</span></div>
                                </td>
                                <td>
                                    <div>Method: <span>${order.selectedShipmentMethod}</span></div>
                                    <div>Address: <span>${order.shipmentAddress}</span></div>
                                </td>
                                <td>
                                    <div>Method: <span>${order.selectedPaymentMethod}</span></div>
                                    <div>Total: <span>${order.formatedTotalPrice}</span></div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <table class="table">

                <tr>
                    <th scope="col">Serial No.</th>
                    <th scope="col">Item Name</th>
                    <th scope="col">Category</th>
                    <th scope="col">Preview</th>
                    <th scope="col">Price per item</th>
                    <th scope="col">Producer</th>
                    <th scope="col">Descrption</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Total</th>
                    <th scope="col">Review</th>
                    <th scope="col">Detail</th>

                </tr>
                <tbody>
                    <c:forEach var="item" items="${items}">
                        <tr>
                            <td>
                                ${item.id}
                            </td>
                            <td>
                                ${item.name}
                            </td>
                            <td>
                                ${item.catalog}

                            </td>

                            <td><img src="${item.image}"
                                     height="100px" width="100px">
                            </td>
                            <td>
                                ${item.price}
                            </td>
                            <td>
                                ${item.producer}
                            </td>
                            <td>
                                ${item.description}
                            </td>
                            <td>
                                ${item.quantity}
                            </td>
                            <td>
                                ${item.totalPrice}
                            </td>
                            
                            <td>
                                    <button type="button" class="btn btn-success" onclick="window.location.href = 'review/${order.orderId}/${item.id}'"
                                    <c:if test="${isNotReceived || item.isReviewed}">disabled</c:if>>
                                        Review
                                    </button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-info" onclick="window.location.href = 'detail_item/${item.id}'">
                                    Detail
                                </button>
                            </td>

                        </tr>
                    </c:forEach>



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