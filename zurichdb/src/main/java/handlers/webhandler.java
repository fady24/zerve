package handlers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.sql.*;

public class webhandler extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		
		PrintWriter wr = null;
		try {
			wr = response.getWriter();
			wr.write("Trying to access the database.");
			
			//database value extractions
		String query = "SELECT * FROM SLCKPF FETCH FIRST 1 ROWS ONLY";
		
		String msg = new String();
		ResultSet rs;
	
		try{
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@192.168.2.12:1522:UATDB","vm1dta","vm1dta12#$");
			System.out.println("con created");
			Statement stmt=con.createStatement();
			System.out.println("stmt created");
			msg = "";
			System.out.println("executing query");
			rs=stmt.executeQuery("Select * from zcpnpf");
			while(rs.next()){  
				msg = rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3);  
			System.out.println(msg);
			}
			//JOptionPane.showMessageDialog(null, "hey");
			wr.write(msg);
			con.close(); 	
		}catch(Exception e)
		{
			msg = "Exception caught." +  e.getMessage().toString() +  e.getStackTrace().toString();
			System.out.println(msg);
		}
		
		
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wr.flush();
		wr.close();
	}

}
