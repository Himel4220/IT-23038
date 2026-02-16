<html>
<body>
<h2>Enter Department Details</h2>
<form method="get" action="TestServlet">
    <label for="departmentName">Department Name:</label>
    <input type="text" id="departmentName" name="departmentName" required><br><br>
    <label for="numberOfStudents">Number of Students:</label>
    <input type="number" id="numberOfStudents" name="numberOfStudents" required><br><br>
    <input type="submit" name="action" value="Insert">
    <input type="submit" name="action" value="View">
    <input type="submit" name="action" value="Update">
    <input type="submit" name="action" value="Delete">
</form>
</body>
</html>