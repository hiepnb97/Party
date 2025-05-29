<%-- 
    Document   : list
    Created on : May 27, 2025, 9:20:40 PM
    Author     : hiepn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Party Members" scope="request" />
<jsp:include page="/includes/header.jsp">
    <jsp:param name="title" value="${title}"/>
</jsp:include>

<div class="table-container">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>${title}</h2>
        <p class="mb-0"><i class="fas fa-eye"></i> Visit count: ${visitCount}</p>
    </div>

    <table class="table">
        <thead>
            <tr>
                <th><i class="fas fa-user"></i> Name</th>
                <th><i class="fas fa-birthday-cake"></i> Age</th>
                <c:if test="${not empty username}">
                    <th><i class="fas fa-cogs"></i> Actions</th>
                </c:if>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="person" items="${persons}">
                <tr>
                    <td>${person.name}</td>
                    <td>${person.age}</td>
                    <c:if test="${not empty username}">
                        <td>
                            <a href="edit?id=${person.id}" class="btn btn-primary btn-sm me-2">
                                <i class="fas fa-edit"></i> Edit
                            </a>
                            <form action="delete" method="POST" class="d-inline-block">
                                <input type="hidden" name="id" value="${person.id}" />
                                <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this member?')">
                                    <i class="fas fa-trash-alt"></i> Delete
                                </button>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="/includes/footer.jsp" />
