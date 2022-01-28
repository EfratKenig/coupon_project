package com.leumi.coupon_project.DAO;

import com.leumi.coupon_project.data.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO {
    int add(Customer customer) throws SQLException;
    void remove(int id) throws SQLException;
    Customer getOneCustomer(int id);
    void update(Customer customer) throws SQLException;
    ArrayList<Customer> getAllCustomers();
    boolean isCustomerExists(String email, String password);

    void deleteCouponPurchaseHistory(int customerID) throws SQLException;
}














//package coupon_project.DAO;
//
//import com.leumi.coupon_project.data.Customer;
//import coupon_project.data.Customer;
//
//import java.sql.SQLException;
//
//
//public interface CustomerDAO {
//    //CRUD functions
//
//    int add(Customer entity) throws SQLException;
//    void remove(int id) throws SQLException;
//    Customer get(int id);
//    void update(Customer entity) throws SQLException;
//
//}
