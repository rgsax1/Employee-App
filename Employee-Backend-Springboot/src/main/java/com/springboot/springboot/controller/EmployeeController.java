package com.springboot.springboot.controller;

import com.springboot.springboot.repository.EmployeeRepository;
import com.springboot.springboot.service.EmployeeService;
import com.springboot.springboot.exception.ResourceNotFoundException;
import com.springboot.springboot.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    
        @Autowired
        private EmployeeService employeeService;

         //get all employee
        @GetMapping("/employees")
        public List<Employee> getAllEmployee(){
            return employeeService.getAllEmployee();
        }

        //post
        @PostMapping("/employees")
        public Employee createEmployee(@RequestBody Employee employee){
            return employeeService.createEmployee(employee);
        }

        //get by id
        @GetMapping("/employee/{id}")
        public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
            Employee employee = employeeService.getEmployeeById(id);
            return ResponseEntity.ok(employee);
        }

        //update
        @PutMapping("/employee/{id}")
        public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
            Employee updateEmployee = employeeService.updateEmployee(id, employeeDetails);
            return ResponseEntity.ok(updateEmployee);
        }

        //delete
        @DeleteMapping("/employee/{id}")
        public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
            String message = employeeService.deleteEmployee(id);
                return ResponseEntity.ok(message);
        }


        
}
