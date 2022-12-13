import java.sql.*;
import java.util.*;
class  DBConnector
{
		public static String username=null,password=null;
	DBConnector()
		{

			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Username of Oracle Database");
                	username=sc.nextLine();
			System.out.println("Enter Password of Oracle Database");
                	password=sc.nextLine();
		}

	public static Connection getDBConnection() throws Exception
	{     
		DBConnector db=new DBConnector();
            System.out.println("You Entered the username="+db.username);
            System.out.println("You Entered the password="+db.password);
		Class.forName("oracle.jdbc.driver.OracleDriver");
        	return DriverManager.getConnection("jdbc:oracle:thin:@172.16.0.23:1521:RDBMS",db.username,db.password);
        
	}
      public static void main(String args[])throws Exception, SQLException
           { 
		//String username,password;     
  		Connection conn = null;
        	//PreparedStatement ps = null;
		//DBConnector db=new DBConnector();


		try{
            conn=DBConnector.getDBConnection();
			//Class.forName("oracle.jdbc.driver.OracleDriver");
		//conn=DriverManager.getConnection("jdbc:oracle:thin:@172.16.0.23:1521:RDBMS","sridhar","sridhar");
 		if(conn != null)
				{
			   System.out.println("Successfully connected.");
				}
			else
				{
			   System.out.println("username/password mismatch or no such user So Failed to connect.");
				}
			
 		System.out.println("Connection established......");
      	//Creating the Statement
      	Statement stmt1 = conn.createStatement();
               DatabaseMetaData dbm = conn.getMetaData();
               ResultSet tables = dbm.getTables(null, null, "ACCOUNT", null);
                   if (tables.next()) 
				{
                         // Table exists
                       //Query to drop a table
                    System.out.println(" DUPLICATION ERROR OCCURED");
                    System.out.println("TABLE ACCOUNT ALREADY EXISTS DROPPED IT TO CREATE NEW TABLE ACCOUNT");
		        String query = "DROP TABLE ACCOUNT";
                   //ps = conn.prepareStatement(sql);
		            stmt1.execute(query);

                          }
                       
                            // Table does not exist
                            

            
            
 		//Query to create a table
      	String query1 = "CREATE TABLE ACCOUNT("+ "ACCNO number(5) NOT NULL," + "BAL NUMBER(7,2) NOT NULL)";
		//ps = conn.prepareStatement(sql);
      	stmt1.executeUpdate(query1);
      	System.out.println("ACCOUNT Table Created...... WITH ATTRIBUTES ACCNO AND BAL");
		//Query to Insert values into table
		String query2="insert into ACCOUNT(ACCNO,BAL) values(1,5000)";
		String query3="insert into ACCOUNT(ACCNO,BAL) values(2,4560)";
		String query4="insert into ACCOUNT(ACCNO,BAL) values(3,3000)";
		stmt1.executeUpdate(query2);
		System.out.println("THREE ROW OF VALUES INSERTED/UPDATED ARE");
		System.out.println("ACCNO |  Bal");
		System.out.println(" 1    |  5000");
		stmt1.executeUpdate(query3);
		System.out.println(" 2    |  4650");
		stmt1.executeUpdate(query4);
		System.out.println(" 3    |  3000");

			}
				catch(Exception e)
					{
			         e.printStackTrace();
		            }
	}	
	}


