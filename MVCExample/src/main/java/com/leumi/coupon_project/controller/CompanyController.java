package com.leumi.coupon_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company") // www.leumi.co.il/company
public class CompanyController {
	
	@GetMapping("/hello/{name}") // localhost:8080/company/hello/John
	@ResponseBody
	public String hello(@PathVariable String name)
	{
		System.out.println("Send "+name+" to the database, using FACADE!");
		//CompanyFacade.sendName(name);
		return "Hello "+name+" from company controller!";
	}
	
	@GetMapping("/plus/{x}/{y}") // localhost:8080/company/plus/8/7 => Sum=15
	@ResponseBody
	public String plus(@PathVariable int x, @PathVariable int y)
	{
		return "Sum= "+(x+y);
	}	

	@GetMapping("/add") // localhost:8080/company/add?id=JohnBrice
	@ResponseBody
	public String add1(@RequestParam(name = "id") String name)
	{
		return "Hello "+name+" from company controller!";
	}
}
