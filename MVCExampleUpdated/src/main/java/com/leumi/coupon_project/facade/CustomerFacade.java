package com.leumi.coupon_project.facade;

import com.leumi.coupon_project.data.Coupon;
import com.leumi.coupon_project.data.Customer;
import com.leumi.coupon_project.helpers.Credentials;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CustomerFacade extends ClientFacade {

    private int customerID;
    private static CustomerFacade instance = null;

    public CustomerFacade() throws SQLException, ClassNotFoundException {
        super();
    }

    public static CustomerFacade getInstance() throws SQLException, ClassNotFoundException {
        if (instance==null) {
            instance = new CustomerFacade();
        }
        return instance;
    }

    public CustomerFacade(int customerID) throws SQLException, ClassNotFoundException {
        super();
        this.customerID = customerID;
    }

    @Override
    public boolean login(Credentials cred) {
        return customerDAO.isCustomerExists(cred.getEmail(), cred.getPassword());

    }

    public void purchaseCoupon(Coupon coupon) throws Exception {

        for (Coupon c : customerDAO.getCouponsOfCustomer(customerID)) {
            if (c.getID() == coupon.getID()) {
                throw new Exception("Cannot buy coupon more than one time");
            }
        }
        if (coupon.getAmount() == 0) {
            throw new Exception("Out of stock");
        }
        if (coupon.getEndDate().after(new Date())) {
            throw new Exception("Expiration date of the coupon has passed");
        }
        customerDAO.updateCouponOfCustomers(customerID, coupon.getID());

        coupon.setAmount(coupon.getAmount() - 1);
        couponDAO.update(coupon);
    }

    public ArrayList<Coupon> getAllCustomerCoupons() throws SQLException, ClassNotFoundException {
        return customerDAO.getCouponsOfCustomer(customerID);
    }

    public ArrayList<Coupon> getCustomerCouponsByCategory(Coupon.Category category) throws SQLException, ClassNotFoundException {
        return customerDAO.getCouponsByCategory(customerID, category.ordinal());
    }

    public ArrayList<Coupon> getCustomerCouponsByMaxPrice(double maxPrice) throws SQLException, ClassNotFoundException {
        return customerDAO.getCouponsByMaxPrice(customerID, maxPrice);
    }

    public Customer getCustomerDetails() {
        return customerDAO.getOneCustomer(customerID);
    }
}
