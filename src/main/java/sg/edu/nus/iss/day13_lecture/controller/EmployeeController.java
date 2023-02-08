package sg.edu.nus.iss.day13_lecture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import sg.edu.nus.iss.day13_lecture.model.Employee;
import sg.edu.nus.iss.day13_lecture.repository.EmployeeRepo;

@Controller
@RequestMapping(path = "/employees")
public class EmployeeController {

    @Autowired //copy one bean to another bean (bean alr done on EmployeeRepo)
    EmployeeRepo empRepo;

    @GetMapping("/home") //url page
    public String employeeListPage(Model model){
        List<Employee> employees = empRepo.findAll();
        model.addAttribute("employees", employees);
        return "employeeslist"; //name of html page
    }

    @GetMapping("/addnew")
    public String addPage(Model model){
        Employee emp = new Employee();
        model.addAttribute("employee", emp);
        return "employeeadd";
    }

    @PostMapping("/addnew")
    public String addEmployee(@Valid @ModelAttribute("employee") Employee employeeForm, BindingResult result, Model mode){
        
        if (result.hasErrors()){
            return "employeeadd";
        }

        Boolean bresult = false;
        bresult = empRepo.save(employeeForm);

        return "redirect:/home";
    }

    @GetMapping("/deleteEmployee/{email}")
    public String deleteEmployee(@PathVariable("email") String email){

        Employee emp = empRepo.findByEmail(email);

        Boolean bResult = empRepo.delete(emp);

        return "redirect:/employees/home";
    }

    @GetMapping("/updateEmployee/{email}")
    public String updateEmployee(@PathVariable("email") String email, Model model){

        Employee emp = empRepo.findByEmail(email);
        model.addAttribute("employee", emp);
        Boolean bResult = empRepo.delete(emp);
        return "employeeupdate";
    }

    public String updateEmployeeProcess(@ModelAttribute("employee") Employee emp, BindingResult result, Model model) {


        if (result.hasErrors()){
            return "employeeupdate";
        }
        
        empRepo.updateEmployee(emp);

        return "redirect:/employees/home";

    }

}

    

