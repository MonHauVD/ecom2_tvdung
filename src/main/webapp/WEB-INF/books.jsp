<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@page import ="java.io.FileOutputStream" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<%@page import=" java.io.ObjectOutputStream" %>
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

<title>Book</title>
</head>
<body class="bg-light">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"> <img
				th:src="@{/images/logo.png}" src="../images/logo.png"
				width="auto" height="40" class="d-inline-block align-top" alt="" />
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto"></ul>
				<ul class="navbar-nav">
					<li class="nav-item active"><a class="nav-link" href="Dashboard">Home
							Page</a></li>
					<li class="nav-item active"><form action="/admin/logout" method="post">
                                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                <input type="submit" value="Logout" />
                                            </form>
					</li>

				</ul>

			</div>
		</div>
	</nav><br>
	<div class="container-fluid">

		<a style="margin: 20px 0" class="btn btn-primary"
			href="/admin/books/add">Add book</a><br>
		<table class="table">

			<tr>
                                <th scope="col">Image</th>
				<th scope="col">Serial No.</th>
                                <th scope="col">Item Name</th>
				<th scope="col">Price</th>
				<th scope="col">Quantity</th>
				<th scope="col">Producer</th>
				<th scope="col">Descrption</th>
                                
                                <th scope="col">Author</th>
                                <th scope="col">ISBN</th>
                                <th scope="col">Number of Pages</th>
                                
				<th scope="col">Delete</th>
				<th scope="col">Update</th>
			</tr>
			<tbody>
                                <h3 class="text-center text-danger mt-3">${msg}</h3>
				<c:forEach var="book" items="${books}">
				<tr>
					<td>
                                            <img src="${book.image}"
						height="100px" width="100px">
                                        </td>

					<td>
						${book.bookId}
					</td>
					<td>
						${book.name}
					</td>
					<td>
						${book.price}

					</td>
                                        <td>
						${book.quantity}
					</td>
                                        <td>
						${book.producer}
					</td>
                                        
					<td>
						${book.description }
					</td>
                                        
					<td>
						${book.author}
					</td>
					<td>
						${book.isbn}
					</td><td>
						${book.numberPage}
					</td>

					<td>
                                            <a class="nav-link" href="books/delete_book/${book.bookId}" >Delete</a>
                                        </td>
                                        <td>
                                            <a class="nav-link" href="books/update_book/${book.bookId}" >Update</a>
                                        </td>

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