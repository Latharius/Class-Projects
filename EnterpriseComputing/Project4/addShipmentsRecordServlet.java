package EnterpriseComputing.Project4;

/*  Name: Leith Rabah
     Course: CNT 4714 – Fall 2022 – Project Four 
     Assignment title:  A Three-Tier Distributed Web-Based Application 
     Date:  December 4, 2022 
*/ 
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class addShipmentsRecordServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private Statement statement;
	private MysqlDataSource dataSource = null;

	// process get request from jsp front-end
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		// connected to DB
		try
        {
            // get a connection to the database
            Properties properties = new Properties(); 
            FileInputStream filein = new FileInputStream("C:\\Users\\leith\\eclipse-workspace\\project_4\\src\\main\\webapp\\WEB-INF\\lib\\dataEntry.properties");;  

            //read a properties file
            try 
            {
                // load properties from file for connection details and MysqlDataSource object
                properties.load(filein);
                dataSource = new MysqlDataSource();
                dataSource.setURL(properties.getProperty("MYSQL_DB_URL"));
                dataSource.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
                dataSource.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));

                filein.close();
                
                connection = dataSource.getConnection();

                statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            } //end try
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
            
            catch (IOException e) 
            {
                e.printStackTrace();
            }
		
			
		
		String result;
		String snumInput = request.getParameter("snumInput");
		String pnumInput = request.getParameter("pnumInput");
		String jnumInput = request.getParameter("jnumInput");
		String quantityInput = request.getParameter("quantityInput");
		int quantityInputNum = Integer.parseInt(quantityInput);
		
		//run insert statement
		try {
			int resultSet;
            java.sql.ResultSetMetaData metaData = null;
      
          //DELETE THIS after testing*************************************************************************************
            String DisableFKey = "SET FOREIGN_KEY_CHECKS=0";
            resultSet = statement.executeUpdate(DisableFKey);
            
            String executeInsert = "INSERT INTO Shipments values('" +snumInput+ "', '" +pnumInput+ "', '" +jnumInput+ "', '" +quantityInputNum+ "')";
            resultSet = statement.executeUpdate(executeInsert);
                
                 
     
		
            String determineNumShipmentsAbove100 = "select * from shipments where quantity >= 100";
            //similar process above
            ResultSet currentShipmentsAbove100 = statement.executeQuery(determineNumShipmentsAbove100);
            int currentNumShipmentsAbove100;
            currentShipmentsAbove100.next();
            currentNumShipmentsAbove100 = currentShipmentsAbove100.getInt(4);
            
  
            if(quantityInputNum >= 100) {
            	result = "<tr>" +
                            "<th style=\"background-color: lime\">" 
                    		+ "<strong> New Shipments record: (" + snumInput + ", "+ pnumInput + ", "+ jnumInput + ", "+ quantityInputNum+")"
                    				+ " - successfully entered into databse. Business logic triggered!</strong></th></tr>";
                    // This will update suppliers by adding 5.
                    String queryToUpdate =
                            "update suppliers "
                            + "set status = status + 5" +
                                    " where snum in " +
                                        "( select snum from shipments where quantity >= 100)";
            
                    int numSupplierStatusMarks = statement.executeUpdate(queryToUpdate);
                    
                
            }else{
            	result = "<tr>" +
                        "<th style=\"background-color: lime\">" +
                		"<strong> New Shipments record: (" + snumInput + ", "+ pnumInput + ", "+ jnumInput + ", "+ quantityInputNum+")"
                				+ " - successfully entered into databse. Business Logic Not Triggered!</strong></th><tr>";
            	
            }
            
		}
		catch (SQLException e) {
			result = "<th>" + 
					"<strong>Error executing the SQL statement above.</strong><br>" +
					"Error message: " + e.getMessage() + 
					"</th>";

			e.printStackTrace();
		}catch (NullPointerException e){
			result = "<th>" + 
					"<strong>Error connecting to database.</strong><br>" +
					"Error message: " + e.getMessage() + 
					"</th>";
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		session.setAttribute("result", result);
		session.setAttribute("snumInput", snumInput);
		session.setAttribute("pnumInput", pnumInput);
		session.setAttribute("jnumInput", jnumInput);
		session.setAttribute("quantityInput", quantityInput);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dataEntryHome.jsp");
		dispatcher.forward(request, response);
	}
		catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}

	

	// process post request from jsp front-end
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doGet(request, response);
	}

}