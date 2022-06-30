package com.springboot.springboot.controller;

import com.springboot.springboot.repository.EmployeeRepository;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    
        @Autowired
        private EmployeeRepository employeeRepository;

        //get all employee
        @GetMapping("/employees")
        public List<Employee> getAllEmployee(){
            return employeeRepository.findAll();
        }

        //post
        @PostMapping("/employees")
        public Employee createEmployee(@RequestBody Employee employee){
            return employeeRepository.save(employee);
        }

        //get by id
        @GetMapping("/employee/{id}")
        public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
            Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: "+ id));
            return ResponseEntity.ok(employee);
        }

        //update
        @PutMapping("/employee/{id}")
        public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
            Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: "+id));
            employee.setFirstName(employeeDetails.getFirstName());
            employee.setLastName(employeeDetails.getLastName());
            employee.setEmailId(employeeDetails.getEmailId());
            
            Employee updateEmployee = employeeRepository.save(employee);
            return ResponseEntity.ok(updateEmployee);
        }

        //delete
        @DeleteMapping("/employees/{id}")
        public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
            Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: "+id));

                employeeRepository.delete(employee);
                Map<String, Boolean> response = new HashMap<>();
                response.put("deleted", Boolean.TRUE);
                return ResponseEntity.ok(response);
        }


}
