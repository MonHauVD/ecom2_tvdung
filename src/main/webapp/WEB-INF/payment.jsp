<%-- 
    Document   : payment
    Created on : 28 thg 11, 2024, 16:09:06
    Author     : TranVietDung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
<head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">

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
                <h4 style="color: white;">${ username }'s Payment</h4>
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
    <div class="container mt-5">
        <h2>Payment Simulation</h2>

         <!-- Display payment details -->
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Details</th>
                    <th>Amount</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Pretax Amount</td>
                    <td>${paymentDto.formatedPreTax}</td>
                </tr>
                <tr>
                    <td>Tax (10%)</td>
                    <td>${paymentDto.formatedTax}</td>
                </tr>
                <tr>
                    <td><strong>Total Amount</strong></td>
                    <td><strong>${paymentDto.formatedTotal}</strong></td>
                </tr>
            </tbody>
        </table>
        <!-- Display success message if available -->
        <c:if test="${not empty message}">
            <div class="alert alert-success">${message}</div>
            <a href="/" class="btn btn-secondary mt-3">Back to Dashboard</a>
        </c:if>

        <!-- Payment processing form -->
        <c:if test="${empty message}">
            <form action="/payment/process" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="selectedPaymentMethod" value="${paymentDto.selectedPaymentMethod}">
                <button type="submit" class="btn btn-primary">Simulate Payment</button>
            </form>
        </c:if>
    </div>
</body>
</html>

</f:view>
