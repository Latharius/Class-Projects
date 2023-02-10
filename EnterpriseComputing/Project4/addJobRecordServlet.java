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

public class addJobRecordServlet extends HttpServlet {
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
		String jnumInput = request.getParameter("jnumInput");
		String jnameInput = request.getParameter("jnameInput");
		String numWorkersInput = request.getParameter("numWorkersInput");
		String cityInput = request.getParameter("cityInput");
		
		//run insert statement
		try {
			int resultSet;
            java.sql.ResultSetMetaData metaData = null;
      
            String executeInsert = "INSERT INTO Jobs values('" +jnumInput+ "', '" +jnameInput+ "', '" +numWorkersInput+ "', '" +cityInput+ "')";
            resultSet = statement.executeUpdate(executeInsert);
                
                 
            result = "<tr>" +
            "<th style=\"background-color: lime\">" +
            "<strong>New Jobs record: (" + jnumInput + ", "+ jnameInput + ", "+ numWorkersInput + ", "+ cityInput+") - successfully entered into databse.</strong><br>";
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
		session.setAttribute("jnumInput", jnumInput);
		session.setAttribute("jnameInput", jnameInput);
		session.setAttribute("numWorkersInput", numWorkersInput);
		session.setAttribute("cityInput", cityInput);
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