<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>

<h1>Customer Management</h1>

<!-- Form to Add or Update Customer -->
<form action="saveCustomer" method="post">
    <input type="hidden" name="id" value="${customer.id}"/>

    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" value="${customer.fullname.firstName}" required/><br/>

    <label for="middleName">Middle Name:</label>
    <input type="text" id="middleName" name="middleName" value="${customer.fullname.middleName}"/><br/>

    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" value="${customer.fullname.lastName}" required/><br/>

    <label for="username">Username:</label>
    <input type="text" id="username" name="username" value="${customer.account.username}" required/><br/>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" value="${customer.account.password}" required/><br/>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="${customer.email}" required/><br/>

    <label for="phoneNumber">Phone Number:</label>
    <input type="text" id="phoneNumber" name="phoneNumber" value="${customer.phoneNumber}" required/><br/>

    <label for="street">Street:</label>
    <input type="text" id="street" name="street" value="${customer.address.street}" required/><br/>

    <label for="district">District:</label>
    <input type="text" id="district" name="district" value="${customer.address.district}" required/><br/>

    <label for="province">Province:</label>
    <input type="text" id="province" name="province" value="${customer.address.province}" required/><br/>

    <label for="country">Country:</label>
    <input type="text" id="country" name="country" value="${customer.address.country}" required/><br/>

    <button type="submit">Save</button>
</form>

<hr/>

<!-- Table to Display Customers -->
<h2>Customer List</h2>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Full Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Username</th>
        <th>Address</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="customer" items="${customers}">
        <tr>
            <td>${customer.id}</td>
            <td>${customer.fullname.firstName} ${customer.fullname.middleName} ${customer.fullname.lastName}</td>
            <td>${customer.email}</td>
            <td>${customer.phoneNumber}</td>
            <td>${customer.account.username}</td>
            <td>${customer.address.street}, ${customer.address.district}, ${customer.address.province}, ${customer.address.country}</td>
            <td>
                <a href="editCustomer?id=${customer.id}">Edit</a> |
                <a href="deleteCustomer?id=${customer.id}" onclick="return confirm('Are you sure you want to delete this customer?');">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
