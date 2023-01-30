<!-- Name: Leith Rabah
     Course: CNT 4714 – Fall 2022 – Project Three
     Assignment title:  A Three-Tier Distributed Web-Based Application
     Date:  November 27, 2022
 -->

<!-- clientHome.jsp - front-end page for client-level users ---> 
<!DOCTYPE html>

<%
    String textBox = (String) session.getAttribute("clientUserInput");
    String result = (String) session.getAttribute("result");
    if(result == null)
    {
        result = " ";
   }
   if(textBox == null)
   {
       textBox = " ";
   }
%>
<html>
    <head>
	    <title>Client-User</title>
		<meta charset="utf-8">
        <style>
        	 body {
        	 	text-align: center; 
        	 	background-color: black;
        	 	color: white;
        	 }
        	 span {
             	color: red;
             }
                 
             table {
             color: black; 
             margin-left: auto; 
             margin-right: auto; 
             border: 1px solid black;
		     border-collapse: collapse;
             }
             
             table, td, th {
             border: 1px solid black
             }
             
             table th {
             background-color: red; 
             }
             table td {
             background-color: lightgray;
             }
             
 			 span {
 			 color:red;
 			 }
        </style>
        <script type="text/javascript">
      		function eraseText(){
      			document.getElementById("clientUserInput").innerHTML = " ";
      		}
      		function eraseData(){
      			document.getElementById("data").innerHTML = " ";
      		}
      	</script>
    </head>
    <body>
        <h1>Welcome to the Fall 2022 Project 4 Enterprise Database System</h1>
        <h2>A Servlet/JSP-based Multi-tiered Enterprise Application Using A Tomcat Container</h2>
        <h3>You are Connected to the Project 4 Enterprise System database as a <span>client-level</span> user.
            <br>Please enter any valid SQL query or update command in the box below
        </h3>
        <hr>

        <form action="logicClient" method="post">
            <textarea rows="20" cols="60" name="clientUserInput" id="clientUserInput"></textarea>
            <br><br><br>
           <input type="submit" value="Execute Command">
           <input type="button" name="clear" value="Clear results" onclick="javascript:eraseData();">
           <input type="reset" name="reset" value="Reset Form" onclick="javascript:eraseText();">
        </form>
        <hr>
        <h4>Database Results:</h4>
        <table id="data">
        	<%=result %>
         </table>
    </body>
</html>