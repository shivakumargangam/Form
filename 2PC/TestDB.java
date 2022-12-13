import java.sql.*;
import java.util.*;
class  TestDB
{
public static void main(String args[])
{
String username,password;     
  Scanner sc=new Scanner(System.in);
System.out.println("Enter Username of Oracle Database");
                username=sc.nextLine();
System.out.println("Enter Password of Oracle Database");
                password=sc.nextLine(); 

		Connection conn = null;
        //PreparedStatement ps = null;

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		conn=DriverManager.getConnection("jdbc:oracle:thin:@172.16.0.23:1521:RDBMS",username,password);			
 System.out.println("Connection established......");
      //Creating the Statement
      Statement stmt1 = conn.createStatement();
ResultSet rs=stmt1.executeQuery("select * from ACCOUNT");
System.out.println("THREE ROW OF VALUES INSERTED/UPDATED ARE");
while(rs.next())  
System.out.println(rs.getInt(1)+"  "+rs.getInt(2));  

conn.close();  


//String query = "DROP TABLE ACCOUNT";

//stmt1.executeUpdate(query);
 
}
				catch(Exception e)
					{
			         e.printStackTrace();
		            }
}	
}

