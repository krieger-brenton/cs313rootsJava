<%-- 
    Document   : ViewPosts
    Created on : Mar 7, 2015, 8:42:50 PM
    Author     : Brenton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Posts</title>
    </head>
    <body>
        <h1>View Posts</h1>
   
        <ul>
            <c:forEach items="${comments}" var="comments">
                <li>${comments}</li>
            </c:forEach>
        </ul>
    </body>
</html>
