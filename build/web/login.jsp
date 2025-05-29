<%-- 
    Document   : login
    Created on : May 27, 2025, 9:11:46 PM
    Author     : hiepn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Login" scope="request" />
<jsp:include page="/includes/header.jsp">
    <jsp:param name="title" value="${title}"/>
</jsp:include>

<div class="form-container">
    <h2 class="text-center mb-4"><i class="fas fa-sign-in-alt"></i> ${title}</h2>
    
    <c:if test="${not empty error}">
        <div class="alert alert-danger mb-4" role="alert">
            <i class="fas fa-exclamation-circle"></i> ${error}
        </div>
    </c:if>
    
    <form action="login" method="POST">
        <div class="form-group">
            <label for="username"><i class="fas fa-user"></i> Username</label>
            <input type="text" class="form-control" id="username" name="username" required 
                   placeholder="Enter your username">
        </div>
        
        <div class="form-group">
            <label for="password"><i class="fas fa-lock"></i> Password</label>
            <input type="password" class="form-control" id="password" name="password" required
                   placeholder="Enter your password">
        </div>
        
        <div class="text-center mt-4">
            <button type="submit" class="btn btn-primary">
                <i class="fas fa-sign-in-alt"></i> Login
            </button>
            <a href="list" class="btn btn-secondary">
                <i class="fas fa-arrow-left"></i> Back to List
            </a>
        </div>
    </form>
</div>

<jsp:include page="/includes/footer.jsp" />
