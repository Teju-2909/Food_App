<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Signup</title>
</head>
<body>
    <h1>Signup</h1>
   
    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>
   
    <c:if test="${not empty message}">
        <p style="color:green;">${message}</p>
    </c:if>
    <form action="signup" method="post">
        <table>
            <tr>
                <td>Name :</td>
                <td><input type="text" name="name" placeholder="Enter your name" required></td>
            </tr>
            <tr>
                <td>Email :</td>
                <td><input type="email" name="email" placeholder="Enter your email" required></td>
            </tr>
            <tr>
                <td>Password :</td>
                <td><input type="password" name="password" placeholder="Enter your password" required></td>
            </tr>
            <tr>
                <td>Phone :</td>
                <td><input type="tel" name="phone" placeholder="Enter your phone" required></td>
            </tr>
            <tr>
                <td>Address :</td>
                <td><input type="text" name="address" placeholder="Enter your address" required></td>
            </tr>
            <tr>
                <td>Role :</td>
                <td>
                    <select name="role" required>
                        <option>Select</option>
                        <option value="manager">Manager</option>
                        <option value="staff">Staff</option>
                        <option value="customer">Customer</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form>
    <a href="login.jsp"><button>LOGIN</button></a>
</body>
</html>
