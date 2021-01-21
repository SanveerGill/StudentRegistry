<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<title>Quick Servlet Demo</title>
	<style>
	body {
	height: 100%;
	}

	body {
		margin: 100;
		background: linear-gradient(90deg, rgb(100, 149, 237), rgb(0, 139, 139));
		font-family: sans-serif;
		font-weight: 100;
		color: white;
	}
	table, th, td {
	  border: 1px solid black;
	  border-collapse: collapse;
	}
	.center {
	  margin-left: auto;
	  margin-right: auto;
	}

	table {
		width: 800px;
		overflow: hidden;
		box-shadow: 0 0 20px rgba(0,0,0,0.1);
	}

	th,
	td {
		padding: 15px;
		background-color: rgba(255,255,255,0.2);
		color: #fff;
	}

	th {
		text-align: left;
	}

	thead {
		th {
			background-color: #55608f;
		}
	}

	tbody {
		tr {
			&:hover {
				background-color: rgba(255,255,255,0.3);
			}
		}
		td {
			position: relative;
			&:hover {
				&:before {
					content: "";
					position: absolute;
					left: 0;
					right: 0;
					top: -9999px;
					bottom: -9999px;
					background-color: rgba(255,255,255,0.2);
					z-index: -1;
				}
			}
		}
	}
	
	.center {
	  margin: auto;
	  width: 50%;
	  padding: 10px;
	  text-align: left;
	  position: absolute;
	  left: 725px;
	}

	</style>
	<script>
		$(document).ready(function(){
			$("select").change(function(){
				if ($(this).val() == "list" || $(this).val() == "psch" || $(this).val() == "pac") {
					var x = document.getElementsByClassName("reg");
					for (var i = 0; i < x.length; i++) {
						x[i].style.display = "none";
					}
					var y = document.getElementsByClassName("addc");
					for (var i = 0; i < y.length; i++) {
						y[i].style.display = "none";
					}
					var z = document.getElementsByClassName("pcl");
					for (var i = 0; i < z.length; i++) {
						z[i].style.display = "none";
					}
					var a = document.getElementsByClassName("psc");
					for (var i = 0; i < a.length; i++) {
						a[i].style.display = "none";
					}
					var b = document.getElementsByClassName("sfg");
					for (var i = 0; i < b.length; i++) {
						b[i].style.display = "none";
					}
					var c = document.getElementsByClassName("sch");
					for (var i = 0; i < c.length; i++) {
						c[i].style.display = "none";
					}
				}
				if ($(this).val() == "reg") {
					var x = document.getElementsByClassName("reg");
					for (var i = 0; i < x.length; i++) {
						x[i].style.display = "inline-block";
					}
					var y = document.getElementsByClassName("addc");
					for (var i = 0; i < y.length; i++) {
						y[i].style.display = "none";
					}
					var z = document.getElementsByClassName("pcl");
					for (var i = 0; i < z.length; i++) {
						z[i].style.display = "none";
					}
					var a = document.getElementsByClassName("psc");
					for (var i = 0; i < a.length; i++) {
						a[i].style.display = "none";
					}
					var b = document.getElementsByClassName("sfg");
					for (var i = 0; i < b.length; i++) {
						b[i].style.display = "none";
					}
					var c = document.getElementsByClassName("sch");
					for (var i = 0; i < c.length; i++) {
						c[i].style.display = "none";
					}
				}
				if ($(this).val() == "addc" || $(this).val() == "dropc") {
					var x = document.getElementsByClassName("reg");
					for (var i = 0; i < x.length; i++) {
						x[i].style.display = "none";
					}
					var y = document.getElementsByClassName("addc");
					for (var i = 0; i < y.length; i++) {
						y[i].style.display = "inline-block";
					}
					var z = document.getElementsByClassName("pcl");
					for (var i = 0; i < z.length; i++) {
						z[i].style.display = "none";
					}
					var a = document.getElementsByClassName("psc");
					for (var i = 0; i < a.length; i++) {
						a[i].style.display = "none";
					}
					var b = document.getElementsByClassName("sfg");
					for (var i = 0; i < b.length; i++) {
						b[i].style.display = "none";
					}
					var c = document.getElementsByClassName("sch");
					for (var i = 0; i < c.length; i++) {
						c[i].style.display = "none";
					}
				}
				if ($(this).val() == "pcl" || $(this).val() == "pgr" || $(this).val() == "csch") {
					var x = document.getElementsByClassName("reg");
					for (var i = 0; i < x.length; i++) {
						x[i].style.display = "none";
					}
					var y = document.getElementsByClassName("addc");
					for (var i = 0; i < y.length; i++) {
						y[i].style.display = "none";
					}
					var z = document.getElementsByClassName("pcl");
					for (var i = 0; i < z.length; i++) {
						z[i].style.display = "inline-block";
					}
					var a = document.getElementsByClassName("psc");
					for (var i = 0; i < a.length; i++) {
						a[i].style.display = "none";
					}
					var b = document.getElementsByClassName("sfg");
					for (var i = 0; i < b.length; i++) {
						b[i].style.display = "none";
					}
					var c = document.getElementsByClassName("sch");
					for (var i = 0; i < c.length; i++) {
						c[i].style.display = "none";
					}
				}
				if ($(this).val() == "psc" || $(this).val() == "pst" || $(this).val() == "del") {
					var x = document.getElementsByClassName("reg");
					for (var i = 0; i < x.length; i++) {
						x[i].style.display = "none";
					}
					var y = document.getElementsByClassName("addc");
					for (var i = 0; i < y.length; i++) {
						y[i].style.display = "none";
					}
					var z = document.getElementsByClassName("pcl");
					for (var i = 0; i < z.length; i++) {
						z[i].style.display = "none";
					}
					var a = document.getElementsByClassName("psc");
					for (var i = 0; i < a.length; i++) {
						a[i].style.display = "inline-block";
					}
					var b = document.getElementsByClassName("sfg");
					for (var i = 0; i < b.length; i++) {
						b[i].style.display = "none";
					}
					var c = document.getElementsByClassName("sch");
					for (var i = 0; i < c.length; i++) {
						c[i].style.display = "none";
					}
				}
				if ($(this).val() == "sfg" || $(this).val() == "sgs") {
					var x = document.getElementsByClassName("reg");
					for (var i = 0; i < x.length; i++) {
						x[i].style.display = "none";
					}
					var y = document.getElementsByClassName("addc");
					for (var i = 0; i < y.length; i++) {
						y[i].style.display = "none";
					}
					var z = document.getElementsByClassName("pcl");
					for (var i = 0; i < z.length; i++) {
						z[i].style.display = "none";
					}
					var a = document.getElementsByClassName("psc");
					for (var i = 0; i < a.length; i++) {
						a[i].style.display = "none";
					}
					var b = document.getElementsByClassName("sfg");
					for (var i = 0; i < b.length; i++) {
						b[i].style.display = "inline-block";
					}
					var c = document.getElementsByClassName("sch");
					for (var i = 0; i < c.length; i++) {
						c[i].style.display = "none";
					}
				}
				if ($(this).val() == "sch") {
					var x = document.getElementsByClassName("reg");
					for (var i = 0; i < x.length; i++) {
						x[i].style.display = "none";
					}
					var y = document.getElementsByClassName("addc");
					for (var i = 0; i < y.length; i++) {
						y[i].style.display = "none";
					}
					var z = document.getElementsByClassName("pcl");
					for (var i = 0; i < z.length; i++) {
						z[i].style.display = "none";
					}
					var a = document.getElementsByClassName("psc");
					for (var i = 0; i < a.length; i++) {
						a[i].style.display = "none";
					}
					var b = document.getElementsByClassName("sfg");
					for (var i = 0; i < b.length; i++) {
						b[i].style.display = "none";
					}
					var c = document.getElementsByClassName("sch");
					for (var i = 0; i < c.length; i++) {
						c[i].style.display = "inline-block";
					}
				}
			});
		});
	</script>
</head>
<body style="text-align:center">
	<h1 style="text-align:center">Student Registry</h1>
	<hr style="width: 25%">
	<h2 style="text-align:center">Select Operation Below</h2>
	<form action="QuickServlet" method="post" style="display:inline-block">
		<div style="width:200px;">
			<select name="command">
				<option value="list">List All Students</option>
				<option value="reg">Register Student</option>
				<option value="del">Remove Student</option>
				<option value="addc">Add Student to Course</option>
				<option value="dropc">Drop Student from Course</option>
				<option value="pac">View All Active Courses</option>
				<option value="pcl">View Classlist</option>
				<option value="pgr">View Information of Students</option>
				<option value="psc">View Completed Courses of Student</option>
				<option value="pst">View Student Transcript</option>
				<option value="sgs">Set Grade of Student</option>
				<option value="sfg">Set Final Grade of Student</option>
				<option value="sch">Schedule a Course</option>
				<option value="csch">Remove a Course from Schedule</option>
				<option value="psch">View Schedule</option>
			</select>
			<p class="reg" style="display:none">Student Name: <input type="text" name="name"/> &nbsp; Student ID: <input type="text" name="id"/></p>
			<p class="addc" style="display:none">Student ID: <input type="text" name="id2"/> &nbsp; Course Code: <input type="text" name="code" /></p>
			<p class="pcl" style="display:none">Course Code: <input type="text" name="code2"/></p>
			<p class="psc" style="display:none">Student ID: <input type="text" name="id3"/></p>
			<p class="sfg" style="display:none">Course Code: <input type="text" name="code3"/> &nbsp; Student ID: <input type="text" name="id4"/> &nbsp; Numeric Grade: <input type="text" name="grade"/></p>
			<p class="sch" style="display:none">Course Code: <input type="text" name="code4"/> &nbsp; <br><br> Day: <select name="day"><option value="Mon">Monday</option><option value="Tue">Tuesday</option><option value="Wed">Wednesday</option><option value="Thur">Thursday</option><option value="Fri">Friday</option></select> 
			<br><br>Start Time: <select name="time"><option value="800">8:00 AM</option><option value="900">9:00 AM</option><option value="1000">10:00 AM</option><option value="1100">11:00 AM</option><option value="1200">12:00 PM</option><option value="1300">1:00 PM</option><option value="1400">2:00 PM</option><option value="1500">3:00 PM</option><option value="1600">4:00 PM</option></select>
			<br><br>Duration: <select name="duration"><option value="1">1 Hour</option><option value="2">2 Hours</option><option value="3">3 Hours</option></select></p>
			<br>
			<br>
			<input type="submit" value="Execute"/>
			<br>
			<br>
		</div>
	</form>
</body>
</html>