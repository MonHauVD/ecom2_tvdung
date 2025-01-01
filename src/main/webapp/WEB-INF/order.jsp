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
            <form action="check_out" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" value="Check out" class="btn btn-success">
            </form>

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

                        </tr>
                    </c:forEach>


                    <!-- Shipment Selection -->
                <form action="/order/select_shipment_method" method="post" id="shipmentForm">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="form-group">
                        <label for="shipmentMethod">Shipment Method</label>
                        <select class="form-control" name="shipmentMethod" id="shipmentMethod" onchange="document.getElementById('shipmentForm').submit();">
                            <c:forEach var="shipmentMethod" items="${shipmentMethods}">
                                <option value="${shipmentMethod}" ${shipmentMethod == orderDto.selectedShipmentMethod ? 'selected' : ''}>
                                    ${shipmentMethod}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                </form>
                
                <!-- Payment Selection -->
                <form action="/order/select_payment_method" method="post" id="paymentForm">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="form-group">
                        <label for="paymentMethod">Payment Method</label>
                        <select class="form-control" name="paymentMethod" id="paymentMethod" onchange="document.getElementById('paymentForm').submit();">
                            <c:forEach var="paymentMethod" items="${paymentMethods}">
                                <option value="${paymentMethod}" ${paymentMethod == orderDto.selectedPaymentMethod ? 'selected' : ''}>
                                    ${paymentMethod}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
                
                
                <div class="form-group">
                    <label for="preTax">Total price Amount: ${orderDto.formatedTotalPrice}</label>
                </div>
                <div class="form-group">
                    <label for="shippingFee">Shipping Fee: ${orderDto.formatedShipmentFee}</label>
                </div>
                <div class="form-group">
                    <label for="preTax">Pre-tax Amount: ${orderDto.formatedPreTax}</label>
                </div>
                <div class="form-group">
                    <label for="tax">Tax: ${orderDto.formatedTax}</label>
                </div>
                <div class="form-group">
                    <label for="total">Total Amount: ${orderDto.formatedTotal}</label>
                </div>

                
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