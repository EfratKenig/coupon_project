package com.leumi.coupon_project.controlers;

import com.leumi.coupon_project.helpers.Credentials;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

//@Component
//@RequestMapping("/api")
public abstract class ClientController {

    //TODO: HOW TO MAKE IT IN THE ABSTRACT CLASS ClientController?
//    @PostMapping("/login")
    public abstract ResponseEntity<?> login(@RequestBody Credentials cred);
}