package net.codejava.servlet;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.io.PrintWriter;
import java.sql.*;

//Name: Sanveer Gill
//Student Number: 500953525

public class Registry
{
   
   // Print all registered students
   public void printAllStudents(ResultSet rs, PrintWriter writer)
   {
	   String table = "<table class = \"center\" style=\"width:25%\">"+
	    " <thead>"+
		"  <tr>"+
		"    <th>ID</th>"+
		"    <th>Name</th> "+
		"  </tr> "+
		" </thead> "+
		" <tbody> ";
	   try{
	   while(rs.next())
	   {
		   table += "<tr>"+
		"    <td>" + rs.getString("id") + "</td>"+
		"    <td>" + rs.getString("name") + "</td>"+
		"  </tr>";
	   }
	   table += "</tbody> ";
	   table += "</table> ";
	   writer.println("<html>" + table + "</html>");
	   }catch(Exception e){ System.out.println(e);}
	   
   }
   
   // Add student to the active course
   //@param studentId
   //@param courseCode, course's course code
   public boolean addCourse(String studentId, String courseCode, ResultSet rs, PrintWriter writer)
   {
	   // Find student object in students treemap
	   // Check if student has already taken this course in the past
	   // If not, then find the active course in courses array list using course code
	   // If active course found then check to see if student already enrolled in this course
	   // If not already enrolled
	   //   add student to the active course
	   //   add course to student list of credit courses with initial grade of 0
	   
	 
	   try{
	   while(rs.next())
	   {
		   if (rs.getString("coursecode").equalsIgnoreCase(courseCode))
		    {
			 writer.println("<html><h2>The student with ID: " + studentId + " has already taken " + courseCode + ".</h2></html>");
			 return false;
			}
		}
		return true;
	   }catch(Exception e){ System.out.println(e);}
	   return true;
	}
		
   
   // drop student from the active course
   //@param studentId
   //@param courseCode
   public boolean dropCourse(String studentId, String courseCode, ResultSet rs, PrintWriter writer)
   {
	   // Find the active course
	   // Find the student in the list of students for this course
	   // If student found:
	   //   remove the student from the active course
	   //   remove the credit course from the student's list of credit courses
	   try{
	   while(rs.next())
	   {
		   if (rs.getString("coursecode").equalsIgnoreCase(courseCode))
		    {
			 return true;
			}
		}
		writer.println("<html><h2>The student with ID: " + studentId + " is not currently enrolled in " + courseCode + ".</h2></html>");
		return false;
	   }catch(Exception e){ System.out.println(e);}
	   return false;
   }
   
   // Print all active courses
   public void printActiveCourses(ResultSet rs, PrintWriter writer)
   {
	   String table = "<table class = \"center\" style=\"width:25%\">"+
		"  <tr>"+
		"    <th>Active Courses</th>"+
		"  </tr>";
	   try{
	   while(rs.next())
	   {
		   table += "<tr>"+
		"    <td>" + rs.getString("code") + "</td>"+
		"  </tr>";
	   }
	   writer.println("<html>" + table + "</html>");
	   }catch(Exception e){ System.out.println(e);}
   }
   
   // Print the list of students in an active course
   public void printClassList(ResultSet rs, PrintWriter writer)
   {
	   String table = "<table class = \"center\" style=\"width:25%\">"+
		"  <tr>"+
		"    <th>ID</th>"+
		"    <th>Name</th> "+
		"  </tr>";
	   try{
	   while(rs.next())
	   {
		   table += "<tr>"+
		"    <td>" + rs.getString("id") + "</td>"+
		"    <td>" + rs.getString("name") + "</td>"+
		"  </tr>";
	   }
	   writer.println("<html>" + table + "</html>");
	   }catch(Exception e){ System.out.println(e);}
   }
   
   
   // Given a course code, find course and print student names and grades
   public void printGrades(ResultSet rs, PrintWriter writer)
   {
	   String table = "<table class = \"center\" style=\"width:25%\">"+
		"  <tr>"+
		"    <th>ID</th>"+
		"    <th>Name</th>"+
		"    <th>Grade</th> "+
		"  </tr>";
	   try{
	   while(rs.next())
	   {
		   table += "<tr>"+
		"    <td>" + rs.getString("id") + "</td>"+
		"    <td>" + rs.getString("name") + "</td>"+
		"    <td>" + rs.getString("grade") + "</td>"+
		"  </tr>";
	   }
	   writer.println("<html>" + table + "</html>");
	   }catch(Exception e){ System.out.println(e);}
   }
   
   // Given a studentId, print all active courses of student
   public void printStudentCourses(String studentId, ResultSet rs, PrintWriter writer)
   {
	   boolean course = false;
	   String table = "<table class = \"center\" style=\"width:25%\">"+
		"  <tr>"+
		"    <th>Completed Courses</th>"+
		"  </tr>";
	   try{
	   while(rs.next())
	   {
		   if (rs.getString("status").equals("inactive"))
		   {
			   course = true;
			   table += "<tr>"+
			"    <td>" + rs.getString("coursecode") + "</td>"+
			"  </tr>";
		   }
	   }
	   if (course == false)
	   {
		   table += "<tr>"+
			"    <td> 0 Results </td>"+
			"  </tr>";
	   }
	   writer.println("<html>" + table + "</html>");
	   }catch(Exception e){ System.out.println(e);}
   }
   
   // Given a studentId, print all completed courses and grades of student
   public void printStudentTranscript(String studentId, ResultSet rs, PrintWriter writer)
   {
	   String table = "<table class = \"center\" style=\"width:25%\">"+
		"  <tr>"+
		"    <th>Course</th>"+
		"    <th>Grade</th> "+
		"  </tr>";
	   try{
	   while(rs.next())
	   {
		   table += "<tr>"+
		"    <td>" + rs.getString("coursecode") + "</td>"+
		"    <td>" + rs.getString("grade") + "</td>"+
		"  </tr>";
	   }
	   writer.println("<html>" + table + "</html>");
	   }catch(Exception e){ System.out.println(e);}
   }
   
  
}
