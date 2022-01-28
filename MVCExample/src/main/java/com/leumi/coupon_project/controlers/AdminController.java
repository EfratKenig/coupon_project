package com.leumi.coupon_project.controlers;//package com.example.coupon_system.controlers;

import com.leumi.coupon_project.data.Company;
import com.leumi.coupon_project.data.Customer;
import com.leumi.coupon_project.facade.AdminFacade;
import com.leumi.coupon_project.helpers.Credentials;
import com.leumi.coupon_project.helpers.SimpleTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


@RestController
@RequestMapping("/admin") // www.leumi.co.il/company
public class AdminController extends ClientController{


    @Autowired
    SimpleTokenManager simpleTokenManager;

    AdminFacade adminFacade = new AdminFacade();

    public AdminController() throws SQLException, ClassNotFoundException {
        super();
    }

    //TODO: HOW TO MAKE IT IN THE ABSTRACT CLASS ClientController?
    @Override
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody Credentials cred)
    {
        System.out.println(new Date()+": Got a new login: " + cred);
        if (adminFacade.login(cred.getEmail(), cred.getPassword())) {
            String token = simpleTokenManager.getNewToken();
            return new ResponseEntity<String>(token, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Login error!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping ("/addCompany")
    public ResponseEntity<?> addCompany(@RequestBody Company company, @RequestHeader("token") String token) throws SQLException {
        try{
            adminFacade.addCompany(company);
        }catch (Exception e){
            return new ResponseEntity<String>("Error adding company", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("company added successfully", HttpStatus.OK);
    }

    @PutMapping ("/updateCompany")
    public ResponseEntity<?> updateCustomer(@RequestBody Company company, @RequestHeader("token") String token) throws SQLException {
        try{
            adminFacade.updateCompany(company);
        }catch (Exception e){
            return new ResponseEntity<String>("Error updating company", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("company updated successfully", HttpStatus.OK);
    }

    @DeleteMapping ("/deleteCompany")
    public ResponseEntity<?> deleteCompany(@RequestParam int companyID, @RequestHeader("token") String token) throws SQLException {
        try{
            adminFacade.deleteCompany(companyID);
        }catch (Exception e){
            return new ResponseEntity<String>("error deleting company", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("company was deleted successfully", HttpStatus.OK);
    }

    @GetMapping ("/getAllCompanies")
    public ResponseEntity<?> getAllCompanies(@RequestHeader("token") String token) throws SQLException {
        try{
            return new ResponseEntity<ArrayList<Company>>(adminFacade.getAllCompanies(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("Error getting all companies", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping ("/getOneCompany")
    public ResponseEntity<?> getOneCompany(@RequestParam int companyID, @RequestHeader("token") String token) throws SQLException {
        try{
            return new ResponseEntity<Company>(adminFacade.getOneCompany(companyID), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("Error getting one company", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping ("/addCustomer")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer, @RequestHeader("token") String token) throws SQLException {
        try{
            adminFacade.addCustomer(customer);
        }catch (Exception e){
            return new ResponseEntity<String>("Error adding customer", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("customer added successfully", HttpStatus.OK);
    }

    @PutMapping ("/updateCustomer")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @RequestHeader("token") String token) throws SQLException {
        try{
            adminFacade.updateCustomer(customer);
        }catch (Exception e){
            return new ResponseEntity<String>("Error updating customer", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("customer updated successfully", HttpStatus.OK);
    }

    @DeleteMapping ("/deleteCustomer")
    public ResponseEntity<?> deleteCustomer(@RequestParam int customerID, @RequestHeader("token") String token) throws SQLException {
        try{
            adminFacade.deleteCustomer(customerID);
        }catch (Exception e){
            return new ResponseEntity<String>("error deleting customer", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("customer was deleted successfully", HttpStatus.OK);
    }



    @GetMapping ("/getAllCustomers")
    public ResponseEntity<?> getAllCustomers(@RequestHeader("token") String token) throws SQLException {
        try{
            return new ResponseEntity<ArrayList<Customer>>(adminFacade.getAllCustomers(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("Error getting all customers", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping ("/getOneCustomer")
    public ResponseEntity<?> getOneCustomer(@RequestParam int customerID, @RequestHeader("token") String token) throws SQLException {
        try{
            return new ResponseEntity<Customer>(adminFacade.getOneCustomer(customerID), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("Error getting one customer", HttpStatus.BAD_REQUEST);
        }
    }

}