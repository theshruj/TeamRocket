<%-- 
    Document   : Student
    Created on : Mar 24, 2015, 8:32:26 PM
    Author     : spari_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
 <html>
    <head>
        <title></title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
  <link href='http://fonts.googleapis.com/css?family=Belgrano' rel='stylesheet' type='text/css'>
  <link href="css/mainStyles.css" rel="stylesheet" type="text/css">
   <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>

   <script language="javascript" type="text/javascript">
            // <!CDATA[
            function loginButton_onclick() 
            {
    $.ajax({
        url: '/TeamRocket/Hello',
        type: 'GET',
        dataType: 'text',
        success: function (data)
        {
           $("#divw").html(data);
        }
    });
}

            function registerButton_onclick() {
                window.open("RegistrationMain.html", "_self");
            }
            // ]]>
        </script>
  
  </head>
  <body>
  <div class="wrapper">
      <header>
          <div class="logo">
            <h1><a href="">Rocket System</a></h1></div>
          <nav>
              <ul id="navlist">
                  <li id="active"><a href="#">Home</a></li>
                  <li><a href="#">Student</a></li>
              </ul>
          </nav>
          <div class="clearfloat"></div>
      </header>
      <div class="bodyContainer">
          <section>
              <div><img src="images/headerPic.jpg" alt=""></div>
              <br />
                          <form name="loginForm" action="login.jsp" method="post">
                              <span style="font-size: 16pt">User ID :</span>
                              <input id="username" name="username" type="text" />
                              &nbsp; <span style="font-size: 16pt">Password:</span>
                              <input id="password" name="userpasswd" type="password" />
                              &nbsp;
                              <input id="loginButton" style="width: 70px" type="button" value="Log In" onclick="return loginButton_onclick()" />
                              &nbsp;
                              <input id="registerButton" style="width: 70px" type="button" value="Register" onclick="return registerButton_onclick()" />
                              <br />
                              </form>
 
          </section>
         
          
          <section>
              <h2>About</h2>
              <p>Rocket System is...</p>
              
          </section>
      </div>
      <div class="clearfloat"></div>
      <footer>
          <p>
          Copyright &copy; Rocket System. All rights reserved. </p>
      </footer>
  </div>
  </body>
  </html>
