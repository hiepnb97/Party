<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${param.title} - Party</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="header">
            <div class="container">
                <h1>Party</h1>
                <div class="nav-links">
                    <a href="list"><i class="fas fa-home"></i> Home</a>
                    <c:if test="${not empty username}">
                        <a href="add"><i class="fas fa-user-plus"></i> Add Member</a>
                        <a href="logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
                    </c:if>
                    <c:if test="${empty username}">
                        <a href="login"><i class="fas fa-sign-in-alt"></i> Login</a>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="container"> 