package com.leumi.coupon_project.facade;

import com.leumi.coupon_project.DAO.*;
import com.leumi.coupon_project.helpers.Credentials;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service

public abstract class ClientFacade {

    CompanyDAO companyDAO;
    CustomerDAO customerDAO;
    CouponDAO couponDAO;

    protected ClientFacade() throws SQLException, ClassNotFoundException {
        companyDAO = CompanyDBDAO.getInstance();
        customerDAO = CustomerDBDAO.getInstance();
        couponDAO = CouponDBDAO.getInstance();
    }

    public boolean login(Credentials cred) {
        //instead of switch do override in inheriting classes
//        switch (cred.getRole()){
//            case "Administrator":
//                return Objects.equals(cred.getEmail(), "admin@admin.com") && Objects.equals(cred.getPassword(), "admin");
//            case "Company":
//                return companyDAO.isCompanyExists(cred.getEmail(), cred.getPassword());
//            case "Customer":
//                return customerDAO.isCustomerExists(cred.getEmail(), cred.getPassword());
//        }
        return false;
    }
}
