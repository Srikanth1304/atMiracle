package com.example.demo.say;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.project.ProjectService;
import com.example.demo.project.Projects;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/employ")

public class EmpController {

    @Autowired
    EmpService waiter;

    @PostMapping("/add")
    public String postMethodName(@RequestBody Employee e) {
            int res=waiter.add(e);
            return res==1 ? "Employee added successfully":"Some thing went wrong";
    }
    @GetMapping("/select")
        public List<Employee>select(@RequestBody Employee es){
            return waiter.select(es);
        }
   
}


