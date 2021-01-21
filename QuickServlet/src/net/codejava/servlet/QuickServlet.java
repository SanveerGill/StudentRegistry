package net.codejava.servlet;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

public class QuickServlet extends HttpServlet {

	/**
	 * this life-cycle method is invoked when this servlet is first accessed
	 * by the client
	 */
	public void init(ServletConfig config) {
		System.out.println("Servlet is being initialized");
	}

	/**
	 * handles HTTP GET request
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		PrintWriter writer = response.getWriter();
		writer.println("<html>Hello, I am a Java servlet!</html>");
		writer.flush();
	}

	/**
	 * handles HTTP POST request
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PrintWriter writer = response.getWriter();
		
		try
		{
			request.getRequestDispatcher("/index.jsp").include(request, response);
		}
		catch (ServletException s)
		{
			writer.println(s);
		}
		
		writer.println("<html><br></html>");
		writer.println("<html><br></html>");
		
		String command = request.getParameter("command");
		Registry registry = null;
		//create registry
	 
		registry = new Registry();
	  
		//create scheduler
		Scheduler scheduler = new Scheduler();
		if (command.equalsIgnoreCase("list"))
		{
			try{
				String code = request.getParameter("code2");
				Class.forName("com.mysql.jdbc.Driver"); 
				Connection con1=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/registry","root","gillman");
				Statement stmt=con1.createStatement();
				ResultSet rs=stmt.executeQuery("select * from students"); 
				registry.printAllStudents(rs, writer);
				}catch(Exception e){ writer.println(e);}
		}
		else if(command.equalsIgnoreCase("reg"))
		{
			  String name = request.getParameter("name");
			  if (isStringOnlyAlphabet(name)) 
			  {  
				String id = request.getParameter("id");
				if (isNumeric(id)) 
				{
					try{
					Class.forName("com.mysql.jdbc.Driver"); 
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/registry","root","gillman");
					String insertStr = "INSERT INTO students(id,name) VALUES(?,?)";
					try (PreparedStatement pst = con.prepareStatement(insertStr);) {
						pst.setString(1, id);
						pst.setString(2, name);
						pst.executeUpdate();
					}catch(Exception e){ System.out.println(e);}
					insertStr = "CREATE TABLE IF NOT EXISTS _" + id + " ( coursecode VARCHAR(100) NOT NULL, status VARCHAR(10), grade INT )";
					try (PreparedStatement pst2 = con.prepareStatement(insertStr);) {
						pst2.executeUpdate();
					}catch(Exception e){ writer.println(e);}
					writer.println("<html><h2 style='text-align:center'>" + name + " was successfully registered!</html></h2>");
					}catch(Exception e){ writer.println(e);}
				}
				else
				{
				 writer.println("<html>Invalid Characters in ID " + id + "</html>");
				}
				  
			  }
			  else
			  {
				  writer.println("<html>Invalid Characters in Name " + name + "</html>");
			  }
			
		    
		}
		else if(command.equalsIgnoreCase("addc"))
		{
			String studentName = "";
			String id = request.getParameter("id2");
			if (isNumeric(id)) 
			{
				String code = request.getParameter("code");
				try{
				Class.forName("com.mysql.jdbc.Driver"); 
				Connection con1=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/registry","root","gillman");
				Statement stmt1=con1.createStatement();  
				ResultSet sn=stmt1.executeQuery("select * from students");
				while(sn.next())
				{
					if(sn.getString("id").equalsIgnoreCase(id))
					{
						studentName = sn.getString("name");
					}
				}
				Statement stmt=con1.createStatement();
				ResultSet rs=stmt.executeQuery("select * from _" + id); 
				if(registry.addCourse(id, code, rs, writer) == true)
				{
					try{
						Class.forName("com.mysql.jdbc.Driver"); 
						Connection con2=DriverManager.getConnection(  
						"jdbc:mysql://localhost:3306/registry","root","gillman");
						
						String insertStr = "INSERT INTO _" + id + "(coursecode, status) VALUES(?,?)";
						try (PreparedStatement pst = con2.prepareStatement(insertStr);) {
							pst.setString(1, code);
							pst.setString(2, "active");
							pst.executeUpdate();
						}catch(Exception e){ writer.println("No student of ID: " + id + " is registered.");}
						insertStr = "CREATE TABLE IF NOT EXISTS " + code + " ( id INT NOT NULL, name VARCHAR(100), grade INT )";
						try (PreparedStatement pst2 = con2.prepareStatement(insertStr);) {
							pst2.executeUpdate();
						}catch(Exception e){ writer.println(e);}
						insertStr = "INSERT INTO " + code + "(id, name) VALUES(?,?)";
						try (PreparedStatement pst3 = con2.prepareStatement(insertStr);) {
							pst3.setString(1, id);
							pst3.setString(2, studentName);
							pst3.executeUpdate();
						}catch(Exception e){ writer.println(e);}
						insertStr = "INSERT INTO courses(code, status, day, startTime, duration) VALUES(?,?,?,?,?)";
						try (PreparedStatement pst4 = con2.prepareStatement(insertStr);) {
							pst4.setString(1, code);
							pst4.setString(2, "active");
							pst4.setString(3, "none");
							pst4.setString(4, "0");
							pst4.setString(5, "0");
							pst4.executeUpdate();
						}catch(Exception e){ writer.println(e);}
						writer.println("<html><h2>Student of ID: " + id + " was successfully added to " + code + "!</h2></html>");
						}catch(Exception e){ writer.println(e);}
			}
			}catch(Exception e){ writer.println(e);}
			}
			else
				{
				 writer.println("<html>Invalid Characters in ID " + id + "</html>");
				}
		}
		else if(command.equalsIgnoreCase("del"))
		{
			String id = request.getParameter("id3");
			if (isNumeric(id)) 
			{
				try{
				String[] array = new String[10];
				int counter = 0;
				Class.forName("com.mysql.jdbc.Driver"); 
				Connection con1=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/registry","root","gillman");
				Statement stmt1=con1.createStatement();  
				ResultSet rs=stmt1.executeQuery("select * from _" + id);
				while(rs.next())
				{
					array[counter] = rs.getString("coursecode");
					counter++;
					
				}  
				for (int i = 0; i < counter; i++)
				{
					stmt1.executeUpdate("DELETE FROM " + array[i] + " WHERE id='" + id + "'");
				}
				stmt1.executeUpdate("DROP TABLE _" + id);
				stmt1.executeUpdate("DELETE FROM students WHERE id='" + id + "'");
				writer.println("<html><h2>Student of ID: " + id + " was successfully removed!</h2></html>");
				}catch(Exception e){ writer.println(e);}
			}
			else
				{
				 writer.println("<html>Invalid Characters in ID " + id + "</html>");
				}
		}
		
		else if(command.equalsIgnoreCase("dropc"))
		{
			String studentName = "";
			String id = request.getParameter("id2");
			if (isNumeric(id)) 
			{
				String code = request.getParameter("code");
				try{
				Class.forName("com.mysql.jdbc.Driver"); 
				Connection con1=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/registry","root","gillman");
				Statement stmt1=con1.createStatement();  
				ResultSet sn=stmt1.executeQuery("select * from students");
				while(sn.next())
				{
					if(sn.getString("id").equalsIgnoreCase(id))
					{
						studentName = sn.getString("name");
					}
				}
				Statement stmt=con1.createStatement();
				ResultSet rs=stmt.executeQuery("select * from _" + id); 
				if(registry.dropCourse(id, code, rs, writer) == true)
				{
					try{
						Class.forName("com.mysql.jdbc.Driver"); 
						Connection con2=DriverManager.getConnection(  
						"jdbc:mysql://localhost:3306/registry","root","gillman");
						Statement stmt2 = con2.createStatement();
						stmt2.executeUpdate("DELETE FROM _" + id + " WHERE coursecode='" + code + "'");
						stmt2.executeUpdate("DELETE FROM " + code + " WHERE id='" + id + "'");
						writer.println("<html><h2>The student with ID: " + id + " has been dropped from " + code + ".</h2></html>");
						}catch(Exception e){ writer.println(e);}
			}
			}catch(Exception e){ writer.println(e);}
			}
			else
				{
				 writer.println("<html><h2>Invalid Characters in ID " + id + "</h2></html>");
				}
		}
		else if(command.equalsIgnoreCase("pac"))
		{
			try{
				Class.forName("com.mysql.jdbc.Driver"); 
				Connection con1=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/registry","root","gillman");
				Statement stmt=con1.createStatement();
				ResultSet rs=stmt.executeQuery("select * from courses"); 
				registry.printActiveCourses(rs, writer);
				}catch(Exception e){ writer.println(e);}
		}
		else if(command.equalsIgnoreCase("pcl"))
		{
			try{
				String code = request.getParameter("code2");
				Class.forName("com.mysql.jdbc.Driver"); 
				Connection con1=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/registry","root","gillman");
				Statement stmt=con1.createStatement();
				ResultSet rs=stmt.executeQuery("select * from " + code); 
				registry.printClassList(rs, writer);
				}catch(Exception e){ writer.println(e);}
		}
		else if(command.equalsIgnoreCase("pgr"))
		{
			try{
				String code = request.getParameter("code2");
				Class.forName("com.mysql.jdbc.Driver"); 
				Connection con1=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/registry","root","gillman");
				Statement stmt=con1.createStatement();
				ResultSet rs=stmt.executeQuery("select * from " + code); 
				registry.printGrades(rs, writer);
				}catch(Exception e){ writer.println(e);}
		}
		else if(command.equalsIgnoreCase("psc"))
		{
			try{
				String id = request.getParameter("id3");
				Class.forName("com.mysql.jdbc.Driver"); 
				Connection con1=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/registry","root","gillman");
				Statement stmt=con1.createStatement();
				ResultSet rs=stmt.executeQuery("select * from _" + id); 
				registry.printStudentCourses(id, rs, writer);
				}catch(Exception e){ writer.println(e);}
		}
		else if(command.equalsIgnoreCase("pst"))
		{
			try{
				String id = request.getParameter("id3");
				Class.forName("com.mysql.jdbc.Driver"); 
				Connection con1=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/registry","root","gillman");
				Statement stmt=con1.createStatement();
				ResultSet rs=stmt.executeQuery("select * from _" + id); 
				registry.printStudentTranscript(id, rs, writer);
				}catch(Exception e){ writer.println(e);}
		}
		else if(command.equalsIgnoreCase("sgs"))
		{
			try{
				String code = request.getParameter("code3");
				String grade = request.getParameter("grade");
				String id = request.getParameter("id4");
				Class.forName("com.mysql.jdbc.Driver"); 
				Connection con1=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/registry","root","gillman");
				Statement stmt=con1.createStatement();
				stmt.executeUpdate("UPDATE _" + id + " SET grade = " + grade + " WHERE coursecode = '" + code + "'"); 
				stmt.executeUpdate("UPDATE " + code + " SET grade = " + grade + " WHERE id = '" + id + "'");
				writer.println("<html><h2>The student with ID: " + id + " now has a grade of " + grade + " in " + code + ".</h2></html>");
				}catch(Exception e){ writer.println(e);}
		}
		else if(command.equalsIgnoreCase("sfg"))
		{
			try{
				String code = request.getParameter("code3");
				String grade = request.getParameter("grade");
				String id = request.getParameter("id4");
				String status = "inactive";
				Class.forName("com.mysql.jdbc.Driver"); 
				Connection con1=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/registry","root","gillman");
				Statement stmt=con1.createStatement();
				stmt.executeUpdate("UPDATE _" + id + " SET grade = " + grade + " WHERE coursecode = '" + code + "'"); 
				stmt.executeUpdate("UPDATE " + code + " SET grade = " + grade + " WHERE id = '" + id + "'");
				stmt.executeUpdate("UPDATE _" + id + " SET status = 'inactive' WHERE coursecode = '" + code + "'"); 
				stmt.executeUpdate("UPDATE courses SET status = 'inactive' WHERE code = '" + code + "'");
				writer.println("<html><h2>The student with ID: " + id + " now has a final grade of " + grade + " in " + code + ".</h2></html>");
				}catch(Exception e){ writer.println(e);}
		}
		else if(command.equalsIgnoreCase("sch"))
		{
			try{
				String code = request.getParameter("code4");
				String day = request.getParameter("day");
				int time = Integer.parseInt(request.getParameter("time"));
				int duration = Integer.parseInt(request.getParameter("duration"));
				Class.forName("com.mysql.jdbc.Driver"); 
				Connection con1=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/registry","root","gillman");
				Statement stmt=con1.createStatement();
				ResultSet rs=stmt.executeQuery("select * from courses");
				scheduler.setDayAndTime(code, day, time, duration, rs, writer, stmt);
				writer.println("<html><h2>" + code + " was successfully scheduled!</h2></html>");
				}catch(Exception e){ writer.println(e);}
		}
		else if(command.equalsIgnoreCase("psch"))
		{
			try{
				writer.println("<html><div class = 'center'></html>");
				Class.forName("com.mysql.jdbc.Driver"); 
				Connection con1=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/registry","root","gillman");
				Statement stmt=con1.createStatement();
				ResultSet rs=stmt.executeQuery("select * from courses");
				scheduler.printSchedule(rs, writer);
				writer.println("<html></div></html>");
				}catch(Exception e){ writer.println(e);}
		}
		else if(command.equalsIgnoreCase("csch"))
		{
			try{
				String code = request.getParameter("code2");
				Class.forName("com.mysql.jdbc.Driver"); 
				Connection con1=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/registry","root","gillman");
				Statement stmt=con1.createStatement();
				ResultSet rs=stmt.executeQuery("select * from courses");
				scheduler.clearSchedule(code, rs, writer, stmt);
				writer.println("<html><h2>" + code + " was successfully removed from the schedule!</h2></html>");
				}catch(Exception e){ writer.println(e);}
		}
		
	}

	/**
	 * this life-cycle method is invoked when the application or the server
	 * is shutting down
	 */
	public void destroy() {
		System.out.println("Servlet is being destroyed");
	}
	
	private static boolean isStringOnlyAlphabet(String str) 
  { 
	  for (int i = 0; i < str.length(); i++)
      {
          if (str.charAt(i) >= 65 && str.charAt(i) <= 90 || str.charAt(i) >= 97 && str.charAt(i) <= 122)
          {
        	  continue;
          }
          else
          {
              return false;
          }
      }
      return true;
  } 
  // method to check if string str contains only numeric characters
  // uses ASCII values to check if character is between 0-9
  //@param str the string being checked
  //@return true if numeric, return false if not
  public static boolean isNumeric(String str)
  {
	  for (int i = 0; i < str.length(); i++)
      {
          if (str.charAt(i) >= 48 && str.charAt(i) <= 57)
          {
        	  continue;
          }
          else
          {
              return false;
          }
      }
      return true;

  }
}