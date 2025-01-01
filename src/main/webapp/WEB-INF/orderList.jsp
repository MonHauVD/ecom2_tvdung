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

        <title>Order List</title>
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
                <h4 style="color: white;">${username}'s Order List</h4>
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


            <table class="table">

                <tr>
                    <th scope="col">Serial No.</th>
                    <th scope="col">Order Date</th>
                    <th scope="col">List Item</th>
                    <th scope="col">Total Price</th>
                    <th scope="col">State</th>
                    <th scope="col">Received</th>
                    <th scope="col">Detail</th>

                </tr>
                <tbody>
                    <c:forEach var="order" items="${orders.lsOrder}" varStatus="status">
                        <tr>
                            <td>
                                <input type="hidden" name="lsOrder[${status.index}].id" value="${order.id}" />
                                ${order.id}
                            </td>
                            <td>
                                <input type="hidden" name="lsOrder[${status.index}].orderDate" value="${order.orderDate}" />
                                ${order.orderDate}
                            </td>
                            <td>
                                <input type="hidden" name="lsOrder[${status.index}].listItem" value="${order.listItem}" />
                                ${order.listItem}

                            </td>

                            <td>
                                <input type="hidden" name="lsOrder[${status.index}].totalPrice" value="${order.totalPrice}" />
                                ${order.totalPrice}
                            </td>
                            <td>
                                <input type="hidden" name="lsOrder[${status.index}].state" value="${order.state}" />
                                ${order.state}
                            </td>
                            <td>
                                <c:if test="${order.state == 'InTransit'}">
                                    <button type="button" class="btn btn-success" onclick="window.location.href = 'received_order/${order.id}'">
                                        Confirm received!
                                    </button>
                                </c:if>
                            </td>
                            <td>
                                <button type="button" class="btn btn-info" onclick="window.location.href = 'detail_order/${order.id}'">
                                    Detail
                                </button>
                            </td>

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