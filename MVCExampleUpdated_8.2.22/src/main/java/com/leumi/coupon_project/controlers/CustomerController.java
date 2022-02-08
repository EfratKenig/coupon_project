package com.leumi.coupon_project.controlers;

import com.leumi.coupon_project.data.Coupon;
import com.leumi.coupon_project.data.Customer;
import com.leumi.coupon_project.database.LoginManager;
import com.leumi.coupon_project.facade.CustomerFacade;
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
@RequestMapping("/customer")
public class CustomerController extends ClientController {

    @Autowired
    SimpleTokenManager simpleTokenManager;
    CustomerFacade customerFacade;

    public CustomerController() {
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
    @Override
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody Credentials cred) {
        System.out.println(new Date() + ": Got a new login: " + cred);
        try {
            customerFacade = (CustomerFacade) LoginManager.getInstance().Login(cred);
        } catch (SQLException | ClassNotFoundException e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
        if (customerFacade != null) {
            String token = simpleTokenManager.getNewToken();
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>("Login error!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/purchaseCoupon")
    public ResponseEntity<String> purchaseCoupon(@RequestBody Coupon coupon, @RequestHeader("token") String token) {
        if (simpleTokenManager.isTokenExist(token)) {
            try {
                customerFacade.purchaseCoupon(coupon);
            } catch (Exception e) {
                return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<String>("success", HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getCustomerCoupons")
    public ResponseEntity<?> getCustomerCoupons(@RequestHeader("token") String token) {
        if (simpleTokenManager.isTokenExist(token)) {
            try {
                return new ResponseEntity<ArrayList<Coupon>>(customerFacade.getCustomerCoupons(), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
            }

        }
        return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getCustomerCouponsByCategory")
    public ResponseEntity<?> getCustomerCoupons(@RequestParam Coupon.Category category, @RequestHeader("token") String token) {
        if (simpleTokenManager.isTokenExist(token)) {
            try {
                return new ResponseEntity<ArrayList<Coupon>>(customerFacade.getCustomerCouponsByCategory(category), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
            }

        }
        return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getCustomerCouponsByMaxPrice")
    public ResponseEntity<?> getCustomerCoupons(@RequestParam double maxPrice, @RequestHeader("token") String token) {
        if (simpleTokenManager.isTokenExist(token)) {
            try {
                return new ResponseEntity<ArrayList<Coupon>>(customerFacade.getCustomerCouponsByMaxPrice(maxPrice), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
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
                return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
            }

        }
        return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
    }
}
