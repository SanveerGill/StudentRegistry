package net.codejava.servlet;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.io.PrintWriter;
import java.sql.*;

//Name: Sanveer Gill
//Student Number: 500953525

public class Scheduler extends RuntimeException
{
	
	//method to schedule a course
	//first checks for any exceptions involving the inputs
	public void setDayAndTime(String courseCode, String day, int startTime, int duration, ResultSet rs, PrintWriter writer, Statement stmt)
    {
		boolean containsCourse = false;
        int endTime = startTime + (duration * 100);
        //Set<String> keySet = schedule.keySet();
      
        if (!day.equalsIgnoreCase("Mon") && !day.equalsIgnoreCase("Tue") && !day.equalsIgnoreCase("Wed") && !day.equalsIgnoreCase("Thur") && !day.equalsIgnoreCase("Fri"))
        {
        	throw new InvalidDayException(writer);
        }
        if (!(startTime >= 800 && endTime <= 1700))
        {
        	throw new InvalidTimeException(writer);
        }
        if (duration != 1 && duration != 2 && duration != 3)
        {
        	throw new InvalidDurationException(writer);
        }
        //loop through schedule treeMap which contains courses
		try{
	    while(rs.next())
	    {
			if (rs.getString("day").equalsIgnoreCase(day))
        	{
        		int keyEndTime = rs.getInt("startTime") + (rs.getInt("duration") * 100);
        		
        		//this if statement checks if the course received by the method(parameter) overlaps with the course from the schedule treeMap (the key) in this iteration of the loop
        		//the method I used was to check if the parameter course starts or ends during the key course's time slot, or if it starts before and ends after the key course
        		//if any one of these three checks are true, the exception is thrown.
        		//these are the only three possible scenarios for two courses to overlap
        		if ((startTime >= rs.getInt("startTime") && startTime < keyEndTime) 
        		     || (endTime > rs.getInt("startTime") && endTime <= keyEndTime)
        		     || (rs.getInt("startTime") > startTime && keyEndTime < endTime))
        		{
        			throw new LectureTimeCollisionException(writer);
        		}
        	}
        	//set day, time, duration for course
            
		}
		try{
		stmt.executeUpdate("UPDATE courses SET day = '" + day + "' WHERE code = '" + courseCode + "'");
		stmt.executeUpdate("UPDATE courses SET startTime = " + startTime + " WHERE code = '" + courseCode + "'");
		stmt.executeUpdate("UPDATE courses SET duration = " + duration + " WHERE code = '" + courseCode + "'");
        containsCourse = true;
	    }catch(Exception e){ writer.println(e);}
		}catch(Exception e){ writer.println(e);}
        //if course cannot be found, throw exception
        if (containsCourse == false)
        {
        	throw new UnknownCourseException(writer);
        }
    }
//clears schedule of given course 
//loops through schedule treeMap, if course is found, schedule is reset, if not, exception is thrown
	public void clearSchedule(String courseCode, ResultSet rs, PrintWriter writer, Statement stmt)
	{
		boolean containsCourse = false;
        
		try{
		stmt.executeUpdate("UPDATE courses SET day = '' WHERE code = '" + courseCode + "'");
		stmt.executeUpdate("UPDATE courses SET startTime = 0 WHERE code = '" + courseCode + "'");
		stmt.executeUpdate("UPDATE courses SET duration = 0 WHERE code = '" + courseCode + "'");
        containsCourse = true;
	    }catch(Exception e){ writer.println(e);}
            
        if (containsCourse = false)
        {
        	throw new UnknownCourseException(writer);
        }
    }
        
	
	//prints schedule
	//uses 2d array to correspond each day/time position with an array position
	public void printSchedule(ResultSet rs, PrintWriter writer)
	{
		String[][] scheduleArray = new String[9][5];
		//fills array with blank strings
		for (int a = 0 ; a <scheduleArray.length; a++)
		{
			for (int b = 0; b <scheduleArray[a].length; b++)
			{
				scheduleArray[a][b] = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			}
		}
		//loop through schedule treeMap which contains courses
		try{
		while(rs.next())
		{
			//get day, startTime, duration of current key in this iteration of the loop
			String day = rs.getString("day");
			int startTime = rs.getInt("startTime");
			int duration = rs.getInt("duration");
			
			//these helper methods assign an index value for each day/time it is given
			//methods are explained below
			int dayIndex = getArrayIndexOfDay(day);
			int timeIndex = getArrayIndexOfTime(startTime);
			
			//loop through the array
			//fills in the array positions that have the same index position as the day/time indexes of the current course in the loop
			//if the specific array location with the corresponding time and day indexes is found, the course code is added to that position
			for (int i = 0 ; i <scheduleArray.length; i++)
			{
				for (int j = 0; j <scheduleArray[i].length; j++)
				{
					if (i == timeIndex && j == dayIndex)
					{
						//this loop is used to make sure courses that span over multiple hours are filled in correctly on the schedule
						for (int x = 0; x < duration ; x++)
						{
							//i+x is used so that hour slot is incremented by one in each iteration of the loop. This only affects courses with >1 duration.
							scheduleArray[i+x][j] = rs.getString("code");
						}
					}
				}
			}
		}
		}catch(Exception e){ writer.println(e);}
		//this portion prints the schedule
		//a loop is used and each individual array position is printed individually
		writer.println("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mon&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tue&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Wed&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Thu&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Fri<br></html>");
		int printTime = 800;
		for (int y = 0; y < scheduleArray.length; y++)
		{
			//the time slot is printed at the beginning of the line
			writer.print(Integer.toString(printTime) + "&nbsp;");
			for (int z = 0; z < scheduleArray[y].length; z++)
			{
				writer.print(scheduleArray[y][z] + "&nbsp;");
			}
			writer.print("<html><br></html>");
			//increments the time so that the next iteration of the loop prints the correct hour in the next row of the schedule
			printTime += 100;
		}
	}
	//this helper methods assigns a unique index value for each of the 5 possible day inputs

	public int getArrayIndexOfDay(String day)
	{
		if (day.equalsIgnoreCase("Mon")) { return 0;}
		if (day.equalsIgnoreCase("Tue")) { return 1;}
		if (day.equalsIgnoreCase("Wed")) { return 2;}
		if (day.equalsIgnoreCase("Thur")) { return 3;}
		if (day.equalsIgnoreCase("Fri")) { return 4;}
		return 0;
	}
	
	//this helper method assigns a unique index value for each of the 9 possible startTime inputs
	public int getArrayIndexOfTime(int startTime)
	{
		if (startTime == 800) {return 0;}
		if (startTime == 900) {return 1;}
		if (startTime == 1000) {return 2;}
		if (startTime == 1100) {return 3;}
		if (startTime == 1200) {return 4;}
		if (startTime == 1300) {return 5;}
		if (startTime == 1400) {return 6;}
		if (startTime == 1500) {return 7;}
		if (startTime == 1600) {return 8;}
		return 0;

	}
}
//Exception for when course cannot be found
class UnknownCourseException extends RuntimeException
{
	public UnknownCourseException(PrintWriter writer)
	{
		writer.println("The given course cannot be found.");
	}
}
//Exception for when the day input is invalid
class InvalidDayException extends RuntimeException
{
    public InvalidDayException(PrintWriter writer)
    {
        writer.println("The day input is invalid. Should be one of “Mon”, “Tue”, “Wed”, “Thur”, “Fri”");
    }
}
//Exception for when the time input is invalid
class InvalidTimeException extends RuntimeException
{
    public InvalidTimeException(PrintWriter writer)
    {
        writer.println("The time input is invalid. Must be between 8am (0800) and 5pm (1700)");
    }
}
//Exception for when the duration is not 1,2, or 3 hours
class InvalidDurationException extends RuntimeException
{
    public InvalidDurationException(PrintWriter writer)
    {
        writer.println("The lecture duration is an invalid input (can only be 1, 2, or 3 hours)");
    }
}
//Exception for when there is multiple lectures at one time slot
class LectureTimeCollisionException extends RuntimeException
{
    public LectureTimeCollisionException(PrintWriter writer)
    {
        writer.println("Lecture Time Collision");
    }
}

