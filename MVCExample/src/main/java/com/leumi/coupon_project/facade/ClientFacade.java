package com.leumi.coupon_project.facade;

import com.leumi.coupon_project.DAO.*;

import java.sql.SQLException;

public abstract class ClientFacade {

    CompanyDAO companyDAO;
    CustomerDAO customerDAO;
    CouponDAO couponDAO;

    protected ClientFacade() throws SQLException, ClassNotFoundException {
        companyDAO = CompanyDBDAO.getInstance();
        customerDAO = CustomerDBDAO.getInstance();
        couponDAO = CouponDBDAO.getInstance();
    }

    public boolean login(String email, String password) {
        return customerDAO.isCustomerExists(email, password);
    }
}
