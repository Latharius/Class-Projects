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

public class rootUserServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private Statement statement;
	private MysqlDataSource dataSource = null;

	// init: setup database connection
	

	// process get request from jsp front-end
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, FileNotFoundException {
		String sqlCommand = request.getParameter("rootUserInput");
		String result = null;
		
		// connected to DB
		try
        {
            // get a connection to the database
            Properties properties = new Properties(); 
            FileInputStream filein = new FileInputStream("C:\\Users\\leith\\eclipse-workspace\\project_4\\src\\main\\webapp\\WEB-INF\\lib\\root.properties");;  

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
		
		//check to see if it is a select statement
		if (sqlCommand.contains("select") == true) {

			try {
				result = doSelectQuery(sqlCommand);
			} catch (SQLException e) {
				result = "<th>" + 
						"<strong>Error executing the SQL statement above.</strong><br>" +
						"Error message" + e.getMessage() + 
						"</th>";

				e.printStackTrace();
			}catch (NullPointerException e){
				result = "<th>" + 
						"<strong>Error connecting to database.</strong><br>" +
						"Error message" + e.getMessage() + 
						"</th>";
				e.printStackTrace();
			}
		}
		else { //do insert,update, delete, create, drop
			try {
				result = doUpdateQuery(sqlCommand);
			}catch(SQLException e) {
				result = "<th>" + 
						"<strong>Error executing the SQL statement above.</strong><br>" +
						"Error message" + e.getMessage() + 
						"</th>";
				e.printStackTrace();
			}
			catch (NullPointerException e){
				result = "<th>" + 
						"<strong>Error connecting to database.</strong><br>" +
						"Error message" + e.getMessage() + 
						"</th>";
				e.printStackTrace();
			}
			
		}

		HttpSession session = request.getSession();
		session.setAttribute("result", result);
		session.setAttribute("rootUserInput", sqlCommand);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/rootHome.jsp");
		dispatcher.forward(request, response);
	}
	
	catch (ServletException e) {
        throw new RuntimeException(e);
    } 
	catch (IOException e) {
        throw new RuntimeException(e);
    }
}

	

	// process post request from jsp front-end
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doGet(request, response);
	}

	// execute a select query and create table html with resultset
	public String doSelectQuery(String textBox) throws SQLException {
		
			String result;
			ResultSet table;
			java.sql.ResultSetMetaData metaData;
			// run sql command
			table = statement.executeQuery(textBox);
		
			metaData = table.getMetaData();
			
			// table columns html
			int numOfColumns = metaData.getColumnCount();
			// html table openig html
			String tableOpeningHTML = "<div class='container-fluid'><div class='row justify-content-center'><div class='table-responsive-sm-10 table-responsive-md-10 table-responsive-lg-10'><table class='table'>";
			// table html columns
			String tableColumnsHTML = "<thead class='thead-dark'><tr>";
			for (int i = 1; i <= numOfColumns; i++) {
				tableColumnsHTML += "<th scope='col'>" + metaData.getColumnName(i) + "</th>";
			}

			tableColumnsHTML += "</tr></thead>"; // close the html tale column element

			// table html body/rows
			String tableBodyHTML = "<tbody>";
			// get row info
			while (table.next()) {
				tableBodyHTML += "<tr>";
				for (int i = 1; i <= numOfColumns; i++) {
					// if first element
					if (i == 1)
						tableBodyHTML += "<td scope'row'>" + table.getString(i) + "</th>";
					else
						tableBodyHTML += "<td>" + table.getString(i) + "</th>";
				}
				tableBodyHTML += "</tr>";
			}

			tableBodyHTML += "</tbody>";

			// closing html
			String tableClosingHTML = "</table></div></div></div>";
			result = tableOpeningHTML + tableColumnsHTML + tableBodyHTML + tableClosingHTML;

			return result;
		
		}
		
	
	private String doUpdateQuery(String sqlCommand) throws SQLException {
		String result = null;
		
		//get number of shipment with quantity >= 100 before update/insert
		String determineNumShipmentsAbove100 = "select * from shipments where quantity >= 100";
        //similar process above
        ResultSet currentShipmentsAbove100 = statement.executeQuery(determineNumShipmentsAbove100);
        int currentNumShipmentsAbove100;
        currentShipmentsAbove100.next();
        currentNumShipmentsAbove100 = currentShipmentsAbove100.getInt(4);
		
		//execute update
		int numOfRowsUpdated1 = statement.executeUpdate(sqlCommand);
		result = "<tr>" +
				"<th style=\"background-color: lime; border: 0px solid black\"> "
				+ "<strong> The statement executed succesfully.<strong><br>"
				+ numOfRowsUpdated1 + " row(s) affected</th></tr>";
		
		
		//update the status of suppliers if shipment quantity is > 100
		if(sqlCommand.contains("quantity") == true || sqlCommand.contains("insert into shipments") == true) {
			if(currentNumShipmentsAbove100 >= 100) {
				//increase suppliers status by 5
				//handle updates into shipments by using a left join with shipments and temp table
				result += 	"<tr>" +
							"<th style=\"background-color: lime; border: 0px solid black\">Business Logic triggered! - Updating Supplier Status</th>"
							+"</tr>";
				 String queryToUpdate =
                         "update suppliers "
                         + "set status = status + 5" +
                                 " where snum in " +
                                     "( select snum from shipments where quantity >= 100)";
				int suppliersStatusUpdate = statement.executeUpdate(queryToUpdate);
	                  
				
				result += "<tr>" +
						"<th style=\"background-color: lime; border: 0px solid black\">Business Logic Updated " + suppliersStatusUpdate + " Supplier(s) status marks</th>"
						+"</tr>";
			}
		}
		else {
			result += "<tr>" +
					"<th style=\"background-color: lime\">Business Logic not triggered!</th>"
					+"</tr>";;
		}
		
		return result;
	}
	


}
