<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Home</title>
</head>

<body>

<%
    HttpSession hs = request.getSession(false);

    String email = null;
    String name = null;
    String regdno = null;

    if (hs != null) {
        email = (String) hs.getAttribute("email");
        name = (String) hs.getAttribute("name");
        regdno = (String) hs.getAttribute("regdno");
    } else {
        response.sendRedirect("login.html");
        return;
    }
%>

<h1>Welcome <%= name %></h1>

<p>Email: <%= email %></p>
<p>Registration No: <%= regdno %></p>

</body>
</html>