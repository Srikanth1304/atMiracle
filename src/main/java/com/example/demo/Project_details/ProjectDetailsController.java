package com.example.demo.Project_details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/pDetails")
public class ProjectDetailsController {
    @Autowired
    private ProjectDetailsService ps;

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody ProjectDetails pd){
        int res = ps.add(pd);
        if (res == 1) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Details added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        }
    }
}