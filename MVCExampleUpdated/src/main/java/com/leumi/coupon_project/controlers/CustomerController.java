package com.leumi.coupon_project.controlers;

import com.leumi.coupon_project.data.Coupon;
import com.leumi.coupon_project.data.Customer;
import com.leumi.coupon_project.facade.CustomerFacade;
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
//@RequestMapping("/customer")
public class CustomerController extends ClientController {

    //TODO: add ID as a (private) variable, initialize in login after receiving the id there

    @Autowired
    TestRepository testRepository;

    @Autowired
    SimpleTokenManager simpleTokenManager;
//    @Autowired
    CustomerFacade customerFacade = CustomerFacade.getInstance();

    public CustomerController() throws SQLException, ClassNotFoundException {
        super();
    }

    /**
     * A successful login will return a valid token to the client for future requests.
     * this token will be valid for SimpleTokenManager.EXPIRATION_TIME_PERIOD_IN_MILLIS, after
     * this period passes SimpleTokenManager.removeExpiredSessions() will remove this token and
     * it'll be no longer a valid token.
     *
     * @param cred
     * @return
     */
    @PostMapping("/customer/login")
    public ResponseEntity<?> login(@RequestBody Credentials cred) {
        System.out.println(new Date() + ": Got a new login: " + cred);
        if (customerFacade.login(cred)) {
            //generate token here!
            String token = simpleTokenManager.getNewToken();
            return new ResponseEntity<String>(token, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Login error!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/purchaseCoupon")
    public ResponseEntity<String> purchaseCoupon(@RequestBody Coupon coupon, @RequestHeader("token") String token) {
        if (simpleTokenManager.isTokenExist(token)) {
            try {
                customerFacade.purchaseCoupon(coupon);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ResponseEntity<String>("success", HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getAllCustomerCoupons")
    public ResponseEntity<?> getAllCustomerCoupons(@RequestHeader("token") String token) {
        if (simpleTokenManager.isTokenExist(token)) {
            try {
                return new ResponseEntity<ArrayList<Coupon>>(customerFacade.getAllCustomerCoupons(), HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getCustomerCouponsByCategory")
    public ResponseEntity<?> getCustomerCoupons(@RequestBody Coupon.Category category, @RequestHeader("token") String token) {
        if (simpleTokenManager.isTokenExist(token)) {
            try {
                return new ResponseEntity<ArrayList<Coupon>>(customerFacade.getCustomerCouponsByCategory(category), HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getCustomerCouponsByPrice")
    public ResponseEntity<?> getCustomerCoupons(@RequestBody double maxPrice, @RequestHeader("token") String token) {
        if (simpleTokenManager.isTokenExist(token)) {
            try {
                return new ResponseEntity<ArrayList<Coupon>>(customerFacade.getCustomerCouponsByMaxPrice(maxPrice), HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getCustomerDetails")
    public ResponseEntity<?> getCustomerDetails(@RequestHeader("token") String token) {
        if (simpleTokenManager.isTokenExist(token)) {
            try {
                return new ResponseEntity<Customer>(customerFacade.getCustomerDetails(), HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
    }
}
