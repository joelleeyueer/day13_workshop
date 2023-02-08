package sg.edu.nus.iss.day13_lecture.repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.ParseException;


import sg.edu.nus.iss.day13_lecture.model.Employee;

@Repository
public class EmployeeRepo {

    private List<Employee> employees;

    public EmployeeRepo() throws ParseException{
        if (employees == null){
            employees = new ArrayList<Employee>();
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = df.parse("1996-10-15");
        Employee emp = new Employee("Joel","Lee","sfmuleaccount@gmail.com", "91183543", 3000, date1, "Blk 684B", 642684);
        employees.add(emp);

        date1 = df.parse("1996-10-15");
        emp = new Employee("Nadia","Atiqah","mrteukshi@gmail.com", "91183543", 3000, date1, "Blk 684B", 642684);
        employees.add(emp);
    }

    public List<Employee> findAll(){
        //return list of all employees
        return employees;
    }

    public Boolean save(Employee employee){

        Boolean result = employees.add(employee);

        return result;
    }

    public Boolean delete(Employee employee){
        //employees.stream().filter(emp -> emp.getEmail().equalsIgnoreCase(employee.getEmail())).findFirst().get();
        //this will remove an employee, cannot check if it exists

        Boolean result = false;
        int employeeIndex = employees.indexOf(employee);

        if (employeeIndex >= 0){
            employees.remove(employeeIndex);
            result = true;
        }

        return result;
    }

    public Employee findByEmail(String email){
        Employee emp = employees.stream().filter(e->e.getEmail().equals(email)).findFirst().get();

        return emp;
    }

    public Boolean updateEmployee(Employee incomingEmp){
        Employee emp = employees.stream().filter(e->e.getEmail().equals(incomingEmp.getEmail())).findFirst().get();

        Integer idx = 0;
        if (emp != null){
            idx = employees.indexOf(emp);
            employees.remove(idx);
            employees.add(incomingEmp);
            return true;
        }
        return false;
    }


    
}
