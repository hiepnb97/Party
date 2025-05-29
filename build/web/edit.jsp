<%-- 
    Document   : edit
    Created on : May 28, 2025, 1:34:36 PM
    Author     : hiepn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Edit Member" scope="request" />
<jsp:include page="/includes/header.jsp">
    <jsp:param name="title" value="${title}"/>
</jsp:include>

<div class="form-container">
    <h2 class="text-center mb-4"><i class="fas fa-user-edit"></i> ${title}</h2>
    
    <form action="edit" method="POST">
        <input type="hidden" name="id" value="${person.id}">
        
        <div class="form-group">
            <label for="name"><i class="fas fa-user"></i> Name</label>
            <input type="text" class="form-control" id="name" name="name" value="${person.name}" required>
        </div>
        
        <div class="form-group">
            <label for="age"><i class="fas fa-birthday-cake"></i> Age</label>
            <input type="number" class="form-control" id="age" name="age" value="${person.age}" required min="0" max="150">
        </div>
        
        <div class="text-center mt-4">
            <button type="submit" class="btn btn-primary">
                <i class="fas fa-save"></i> Update Member
            </button>
            <a href="list" class="btn btn-secondary">
                <i class="fas fa-arrow-left"></i> Back to List
            </a>
        </div>
    </form>
</div>

<jsp:include page="/includes/footer.jsp" />
