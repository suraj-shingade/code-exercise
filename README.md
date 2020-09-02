# CodeExerciseSuraj

Postman Collection is also available

Swagger  : 

http://localhost:8080/v2/api-docs



PUT 
	/api​/employee
	updateEmployee
	

GET
	​/api​/employees​/all
	fetchAllEmployees


GET
​	/api​/employees​/by​/place​/{place}
	findByPlace

GET
​	/api​/employees​/by​/supervisor​/{id}
	fetchAllSupervisor


PUT
​	/api​/employees​/place​/{place}​/salary​/{percentage}
	updateEmployeeSalary

GET
​	/api​/employees​/salary​/range​/by​/title​/{title}
	totalSalaryBySupervisor
	
GET
​	/api​/employees​/salary​/total​/by​/bu​/{bu}
	totalSalaryByBusinessUnit

GET
​	/api​/employees​/salary​/total​/by​/place​/{place}
	totalSalaryByPlace

GET
​	/api​/employees​/salary​/total​/by​/supervisor​/{supervisor}
	totalSalaryBySupervisor
