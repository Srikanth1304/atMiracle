package com.example.demo.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/task")

public class TaskController {
    @Autowired
    private TaskService waiter;

    @PostMapping("/add")
    public String postMethodName(@RequestBody Task t) {
        int res=waiter.add(t);
        
        return res==1? "Task added successfuly": "Something went wrong";
    }
    

}
