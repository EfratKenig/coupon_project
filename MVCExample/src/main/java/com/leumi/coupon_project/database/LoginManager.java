package com.leumi.coupon_project.database;
import com.leumi.coupon_project.facade.AdminFacade;
import com.leumi.coupon_project.facade.ClientFacade;

import java.sql.SQLException;

public class LoginManager {
    public enum ClientType{
        Administrator,
        Company,
        Customer;
    }
    public LoginManager instance;

    public LoginManager getInstance(){
        if (instance == null) {
            try {
                instance = new LoginManager();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public ClientFacade Login(String email, String password, ClientType clientType) throws SQLException, ClassNotFoundException {
        switch (clientType){
            case Company:
                // get the company id
                // but if I have to check it here what is the login() in facade for??

//                return new CompanyFacade();
            case Customer:
//                return new CustomerFacade();
            case Administrator:
                return new AdminFacade();
        }
        return null;
    }
}
