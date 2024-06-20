package com.example.demo.empApi;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class EmpDetailsController<detailsservice> {
@Autowired
EmpDetailsService ds;
@GetMapping("/select/{pid}")

public List<Map<String,Object>> select(@PathVariable String pid) throws UserException
{
    List<Map<String,Object>> li = ds.select(pid);
    return li;
}
}
