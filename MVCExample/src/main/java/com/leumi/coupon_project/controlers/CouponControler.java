package com.leumi.coupon_project.controlers;

import com.leumi.coupon_project.data.Coupon;
import com.leumi.coupon_project.helpers.Credentials;
import com.leumi.coupon_project.helpers.SimpleTokenManager;
import com.leumi.coupon_project.helpers.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/api")
public class CouponControler {

	@Autowired
	SimpleTokenManager simpleTokenManager;
	@Autowired
	TestRepository testRepository;

	/**
	 * returns all the available coupons in the system
	 * Note: this is how you pass a token as a URL parameter
	 * @param token
	 * @return
	 */
	@GetMapping("all/{token}")
	@ResponseBody
	public ResponseEntity<?> all(@PathVariable String token) {
		System.out.println("Got a request (all) from client!");
//		if (simpleTokenManager.isTokenExist(token)) {
//			List<Coupon> res = testRepository.getCoupons();
//			return new ResponseEntity<List<com.leumi.coupon_project.data.Coupon>>(res, HttpStatus.OK);
//		}
		return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
	}

	/**
	 * Adding a coupon to the system.
	 * Note: this is how you pass the token in the header (this way is preferred)
	 * @param coupon
	 * @param token
	 * @return
	 */
	@PostMapping("/addCoupon")
	ResponseEntity<?> addCoupon(@RequestBody Coupon coupon, @RequestHeader("token") String token) {
		System.out.println("Got a new coupon: "+coupon+", token="+token);
//		if (simpleTokenManager.isTokenExist(token)) {
//			Integer id = testRepository.addCoupon(coupon);//Here you need to use the appropriate FACADE to add coupon
//			return new ResponseEntity<Integer>(id, HttpStatus.OK);
//		}
		return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
	}

	/**
	 * A successful login will return a valid token to the client for future requests.
	 * this token will be valid for SimpleTokenManager.EXPIRATION_TIME_PERIOD_IN_MILLIS, after
	 * this period passes SimpleTokenManager.removeExpiredSessions() will remove this token and
	 * it'll be no longer a valid token.
	 * @param cred
	 * @return
	 */
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody Credentials cred)
	{
		System.out.println(new Date()+": Got a new login: "+cred);
		if (testRepository.isValidCredentials(cred)) {//Here you need to put your own login
			//generate token here!
			String token = simpleTokenManager.getNewToken();
			return new ResponseEntity<String>(token, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Login error!", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/a")
	void a() {
		System.out.println("Got a new coupon: ");
	}

}
