<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset password</title>
    </head>
    <body>
        <h1>Reset password</h1>
        <br>
        <p>Please enter your email address to reset your password.</p>
        <br>
        <form action="reset" method="post">
            <label>Email Address: </label>
            <input id="email" name="email" type="text">
            <input type="submit">
        </form>
    </body>
</html>
