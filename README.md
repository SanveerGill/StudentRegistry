# StudentRegistry
Web application of a basic student registry similar to what a school would use. Constructed using a Java servlet, JSP, JavaScript, HTML, CSS, and mySQL for the database. 
Apache Tomcat 9.0 was used to host the server and deploy the web application locally. It currently is not being hosted publicly.

The user interface is as seen below:

![Interface](https://i.gyazo.com/8a2c9e2c7f8d1258bb93fa39d04c2e5e.png)

The commands available to the users:

![Interface](https://i.gyazo.com/eb9ec4b85a913eff7cc416eb0736278c.png)

Some examples of commands being used:

![Interface](https://i.gyazo.com/6bfd70050f8c5c7e6238d3471a29c517.png)
![Interface](https://i.gyazo.com/af0651a9d54d1bacb9a24adb43294dba.png)
![Interface](https://i.gyazo.com/61d470819c4a307270d0f58f745b8c76.png)

When the user submits information through the HTML form, the Java servlet's doPost method is invoked and the appropriate action is carried out. 
For the command above, the user inputs the student's name and ID to register them into the system. The servlet receives this information from the JSP
and stores the name and ID in my mySQL database. The application uses the Java Database Connectivity API to connect and communicate with the mySQL database. It utilizes the statement interface to execute SQL queries to both store and receieve data when necessary. 
The database is initially constructed with two tables, one for courses and one for students as seen below:

![Interface](https://i.gyazo.com/7e6e6109d684b329168714c1b822b37b.png)
![Interface](https://i.gyazo.com/3cf55b3db67bb8c1548cdf4f871b9c1b.png)

When a student is registered, the application stores their information in the students table, and when a new course is added, it stores its information in the courses table.
When the user reschedules a course for a different time, this information is updated in the course table in that course's row, etc. 
Also, additional tables are added as new students/courses are registered. Each student gets their own table to hold a list of their courses along with their grades, to ensure that this information can be accessed using the student's ID by the user if needed. Each course also gets its own table to act as a classlist. These tables are dynamically added to the database when the user adds students/courses and is accessed using the student/course IDs. This setup allows for easy access of student/course information requiring the user to provide minimal input to locate information and relay it to them. After a few courses/students have been registered, the database looks like this:      

![Interface](https://i.gyazo.com/7b9128ade0144b0c9a47453abfccc1d4.png)

