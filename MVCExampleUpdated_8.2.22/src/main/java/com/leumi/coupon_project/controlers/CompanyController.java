package com.leumi.coupon_project.controlers;

import com.leumi.coupon_project.data.Company;
import com.leumi.coupon_project.data.Coupon;
import com.leumi.coupon_project.facade.CompanyFacade;
import com.leumi.coupon_project.helpers.Credentials;
import com.leumi.coupon_project.helpers.SimpleTokenManager;
import com.leumi.coupon_project.helpers.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("company")
public class CompanyController extends ClientController {

    //TODO: add ID as a (private) variable, initialize in login after receiving the id there

    CompanyFacade companyFacade = CompanyFacade.getInstance();
    @Autowired
    SimpleTokenManager simpleTokenManager;
    @Autowired
    TestRepository testRepository;

    public CompanyController() throws SQLException, ClassNotFoundException {
    }

    @Override
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody Credentials cred) {
        System.out.println(new Date()+": Got a new login: "+cred);
        if (companyFacade.login(cred)) {
            String token = simpleTokenManager.getNewToken();
            return new ResponseEntity<String>(token, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Login error!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("addCoupon")
    public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon, @RequestHeader("token") String token) {
        System.out.println("Got coupon " + coupon);
        if (simpleTokenManager.isTokenExist(token)) {
            try {
                companyFacade.addCoupon(coupon);
                int id = coupon.getID();
                System.out.println("adding coupon id: "+ id);
                return new ResponseEntity<Integer>(id, HttpStatus.OK);
            } catch (SQLException e) {
                return new ResponseEntity<String>("Error add coupon!!", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<String>("Token is not valid!!", HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/updateCoupon")
    public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon, @RequestHeader("token") String token) {
        System.out.println("Got coupon to update:" + coupon);
        if (simpleTokenManager.isTokenExist(token)) {
            try {
                companyFacade.updateCoupon(coupon);
                return new ResponseEntity<Integer>(HttpStatus.OK);
            } catch (SQLException e) {
                return new ResponseEntity<String>("Error update coupon!!", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<String>("Token is not valid!!", HttpStatus.BAD_GATEWAY);
        }
    }

    @DeleteMapping("deleteCoupon")
    public ResponseEntity<?> deleteCoupon(@RequestParam int id, @RequestHeader("token") String token) {
        System.out.println("Got company id to delete: " + id);
        if (simpleTokenManager.isTokenExist(token)) {
            try {
                companyFacade.deleteCoupon(id);
                return new ResponseEntity<Integer>(HttpStatus.OK);
            } catch (SQLException e) {
                return new ResponseEntity<String>("Error delete coupon!!", HttpStatus.BAD_REQUEST);
            }
        }
        else {
            return new ResponseEntity<String>("Token is not valid!!", HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("getCompanyCouponsByCompanyID")
    @ResponseBody
    public ResponseEntity<?> getCompanyCouponsByCompanyID(@RequestParam int id,  @RequestHeader("token") String token) {
        System.out.println("Got a request getCompanyCoupons from client!");
        if (simpleTokenManager.isTokenExist(token)) {
            ArrayList<Coupon> res = companyFacade.getCompanyCouponsByCompanyID(id);
            return new ResponseEntity<ArrayList<Coupon>>(res, HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getCompanyCouponsByCompanyIDCategory/{category}")
    @ResponseBody
    public ResponseEntity<?> getCompanyCouponsByCompanyIDCategory(@RequestParam int id, @PathVariable Coupon.Category category, @RequestHeader("token") String token) {
        System.out.println("Got a request getCompanyCoupons by category from client!");
        if (simpleTokenManager.isTokenExist(token)) {
            ArrayList<Coupon> res = companyFacade.getCompanyCouponsByCompanyIDCategory(id, category);
            return new ResponseEntity<ArrayList<Coupon>>(res, HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getCompanyCouponsByCompanyIDPrice/{maxPrice}")
    @ResponseBody
    public ResponseEntity<?> getCompanyCouponsByCompanyIDPrice(@RequestParam int id, @PathVariable Double maxPrice, @RequestHeader("token") String token) {
        System.out.println("Got a request getCompanyCoupons by maxPrice from client!");
        if (simpleTokenManager.isTokenExist(token)) {
            ArrayList<Coupon> res = companyFacade.getCompanyCouponsByCompanyIDPrice(id, maxPrice);
            return new ResponseEntity<ArrayList<Coupon>>(res, HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getCompanyDetails/{token}")
    @ResponseBody
    public ResponseEntity<?> getCompanyDetails(@RequestParam int id, @RequestHeader("token") String token) {
        System.out.println("Got a request getCompanyCoupons by category from client!");
        if (simpleTokenManager.isTokenExist(token)) {
            Company company = companyFacade.getCompanyDetails(id);
            return new ResponseEntity<Company>(company, HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
    }

}
