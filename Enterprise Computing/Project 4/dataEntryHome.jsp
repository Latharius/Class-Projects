<!-- Name: Leith Rabah
     Course: CNT 4714 – Fall 2022 – Project Three
     Assignment title:  A Three-Tier Distributed Web-Based Application
     Date:  December 2, 2022
 -->

 <!-- dataEntryHome.jsp - front-end page for data-entry users ---> 
 <!DOCTYPE html>

 <%
     String snumInput = (String) session.getAttribute("snumInput");
     String snameInput = (String) session.getAttribute("snameInput");
     String statusInput = (String) session.getAttribute("statusInput");
     String cityInput = (String) session.getAttribute("cityInput");
 
     String pnumInput = (String) session.getAttribute("pnumInput");
     String pnameInput = (String) session.getAttribute("pnameInput");
     String colorInput = (String) session.getAttribute("colorInput");
     String weightInput = (String) session.getAttribute("weightInput");
 
     String jnumInput = (String) session.getAttribute("jnumInput");
     String jnameInput = (String) session.getAttribute("jnameInput");
     String numWorkersInput = (String) session.getAttribute("numWorkersInput");
 
     String quantityInput = (String) session.getAttribute("quantityInput");
 
 
 
     String result = (String) session.getAttribute("result");
 
    if(result == null)
    {
         result = " ";
    }
 
 
 %>
 <html lang="en">
     <head>
         <title>Data-Entry-User-Supplier</title>
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
             margin-right: auto
             }
             
             table th {
             background-color: red;
             }
             
             table td {
             background-color: lightgray;
             }
             
             span {
             color: red;
             }
             
             .text
             {
             font-weight: bold;
             }
             
         </style>
         <script type="text/javascript">
               function eraseData(){
                   document.getElementById("snumInput").innerHTML = " ";
                   document.getElementById("snameInput").innerHTML = " ";
                   document.getElementById("statusInput").innerHTML = " ";
                   document.getElementById("cityInput").innerHTML = " ";
                   document.getElementById("data").innerHTML = " ";
 
                   document.getElementById("pnumInput").innerHTML = " ";
                   document.getElementById("pnameInput").innerHTML = " ";
                   document.getElementById("colorInput").innerHTML = " ";
                   document.getElementById("weightInput").innerHTML = " ";
 
                   document.getElementById("jnumInput").innerHTML = " ";
                   document.getElementById("jnameInput").innerHTML = " ";
                   document.getElementById("numWorkersInput").innerHTML = " ";
 
                   document.getElementById("quantityInput").innerHTML = " ";
               }
           </script>
     </head>
     <body>
         <h1>Welcome to the Fall 2022 Project 4 Enterprise Database System</h1>
         <h2>DataEntry Application</h2>
         <h3>You are Connected to the Project 4 Enterprise System database as a <span>data-entry-level</span> user.
             <br>Enter the data value in a form below to add a new record to the corresponding database table.
         </h3>
 
 
         <div class="text">Suppliers Record Insert</div>
         <form action="addSupplierRecord" method="post"  autocomplete="on">
             <fieldset>
                 <table>
                     <thead>
                     <tr>
                         <th>snum</th>
                         <th>sname</th>
                         <th>status</th>
                         <th>city</th>
                     </tr>
                     </thead>
                     <tbody>
                         <tr>
                             <td><input type="text" id="snumInput" name="snumInput"></td>
                             <td><input type="text" id="snameInput" name="snameInput"></td>
                             <td><input type="text" id="statusInput" name="statusInput"></td>
                             <td><input type="text" id="cityInput" name="cityInput"></td>
                         </tr>
                     </tbody>
                  </table>
                  <input type="submit" value="Enter supplier record into database">
                  <input type="reset" value="Clear data and results" onclick="javascript:eraseData();">
             </fieldset>
         </form>
 
         <br><br>
 
         <div class="text">Parts Record Insert</div>
         <form action="addPartsRecord" method="post">
             <fieldset>
                 <table>
                 <thead>
                 <tr>
                     <th>pnum</th>
                     <th>pname</th>
                     <th>color</th>
                     <th>weight</th>
                     <th>city</th>
                 </tr>
                 </thead>
                 <tbody>
                 <tr>
                     <td><input type="text" id="pnumInput" name="pnumInput"></td>
                     <td><input type="text" id="pnameInput" name="pnameInput"></td>
                     <td><input type="text" id="colorInput" name="colorInput"></td>
                     <td><input type="text" id="weightInput" name="weightInput"></td>
                     <td><input type="text" id="cityInput" name="cityInput"></td>
                 </tr>
                 </tbody>
                 </table>
                 <input type="submit" value="Enter part record into database">
                 <input type="reset" value="Clear data and results" onclick="javascript:eraseData();">
             </fieldset>
             </form>
 
             <br><br>
 
             <div class="text">Job Record Insert</div>
             <form action="addJobRecord" method="post">
                 <fieldset>
                     <table>
                     <thead>
                     <tr>
                         <th>jnum</th>
                         <th>jname</th>
                         <th>numWorkers</th>
                         <th>city</th>
                     </tr>
                     </thead>
                     <tbody>
                     <tr>
                         <td><input type="text" id="jnumInput" name="jnumInput"></td>
                         <td><input type="text" id="jnameInput" name="jnameInput"></td>
                         <td><input type="text" id="numWorkersInput" name="numWorkersInput"></td>
                         <td><input type="text" id="cityInput" name="cityInput"></td>
                     </tr>
                     </tbody>
                     </table>
                     <input type="submit" value="Enter job record into database">
                     <input type="reset" value="Clear data and results" onclick="javascript:eraseData();">
                 </fieldset>
                 </form>
 
                 <br><br>
 
                 <div class="text">Shipment Record Insert</div>
                 <form action="addShipmentRecord" method="post">
                     <fieldset>
                         <table>
                         <thead>
                         <tr>
                             <th>snum</th>
                             <th>pnum</th>
                             <th>jnum</th>
                             <th>quantity</th>
                         </tr>
                         </thead>
                         <tbody>
                         <tr>
                             <td><input type="text" id="snumInput" name="snumInput"></td>
                             <td><input type="text" id="pnumInput" name="pnumInput"></td>
                             <td><input type="text" id="jnumInput" name="jnumInput"></td>
                             <td><input type="text" id="quantityInput" name="quantityInput"></td>
                         </tr>
                         </tbody>
                         </table>
                         <input type="submit" value="Enter shipment record into database">
                         <input type="reset" value="Clear data and results" onclick="javascript:eraseData();">
                     </fieldset>
                     </form>
 
         <h4>Database Results:</h4>
         <table id="data">
              <%=result %>
          </table>
     </body>
 </html>
 