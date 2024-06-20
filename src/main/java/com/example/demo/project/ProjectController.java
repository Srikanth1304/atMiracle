package com.example.demo.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {

@Autowired
private ProjectService waiter;
@PostMapping("/addProject")
    public String method(@RequestBody Projects p){
        int res=waiter.addProject(p);
        return res==1 ? "Project added successfully " : "Something went wrong";
    }
}
