<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style>
        input[type="submit"]:hover {
            color: #ffffff;
            background-color: #0076A2;
        }
    </style>
</head>
<body style="padding: 0; margin: 0; background-color: #d7f4ff;">

<div style="    height: 500px;    width: 250px;    margin: 0 auto;    background-color: #002A4B;    padding: 100px;">
    <img src="images/medic.jpg"/>

    <div style="    margin-bottom: 50px;    width: 230px;    color: #c59d2e;    font-size: 22;    text-align: center;">Invalid email or password</div>
    <form action='j_security_check' id="loginForm" method="post" autocomplete="on">
        <input id="j_username" name="j_username" type="text" placeholder="Email"
               style="	width: 230px;  margin-bottom: 15px;  border: 2px solid #3e7cad;  border-radius: 8px;  color: #186099;  font-size: 17px;    padding: 8px;  background-color: #d7f4ff; "><br>
        <input id="j_password" name="j_password" type="password" placeholder="password"
               style="	width: 230px;  margin-bottom: 15px;  border: 2px solid #3e7cad;  border-radius: 8px;  color: #186099;  font-size: 17px;    padding: 8px;  background-color: #d7f4ff; "><br>
        <input type="submit" id="submit" value="Log In"
               style="	width: 250px; height: 25px;font-size: 17px;  height: 35px;  border: 1px solid #000;  border-radius: 8px;  color: #245582;  cursor: pointer; background-color: #7AD1F1; "><br>
    </form>
</div>

</body>
</html>