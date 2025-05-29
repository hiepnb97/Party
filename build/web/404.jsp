<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>404 - Page Not Found</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            .error-container {
                text-align: center;
                padding: 100px 20px;
                min-height: 100vh;
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                background-color: #f8f9fa;
            }
            .error-code {
                font-size: 120px;
                font-weight: bold;
                color: #dc3545;
                margin: 0;
                line-height: 1;
            }
            .error-message {
                font-size: 24px;
                color: #6c757d;
                margin: 20px 0;
            }
            .back-home {
                display: inline-block;
                padding: 12px 24px;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                transition: background-color 0.3s;
            }
            .back-home:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="error-container">
            <h1 class="error-code">404</h1>
            <p class="error-message">Sorry, the page you are looking for does not exist.</p>
            <a href="${pageContext.request.contextPath}/list" class="back-home">Back to Home</a>
        </div>
    </body>
</html> 