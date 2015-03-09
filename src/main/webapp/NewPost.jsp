<%-- 
    Document   : NewPost
    Created on : Mar 7, 2015, 6:44:45 PM
    Author     : Brenton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Post</title>
    </head>
    <body>
        <h1>Make A New Post</h1>
        <a href="PostComment"><h3>View Page</h3></a>
        <form action="PostComment" method="POST">
            Comment: <br/>
            <textarea name ="comment" rows="5" cols="100"></textarea> <br/>
            <input type="submit" value="Post Comment">
        </form>
    </body>
</html>
