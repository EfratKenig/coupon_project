package com.leumi.coupon_project.facade;

import com.leumi.coupon_project.data.Company;
import com.leumi.coupon_project.data.Coupon;
import com.leumi.coupon_project.helpers.Credentials;

import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyFacade extends ClientFacade {

    private static CompanyFacade instance;

    public CompanyFacade() throws SQLException, ClassNotFoundException {
        super();
    }

    public static CompanyFacade getInstance() throws SQLException, ClassNotFoundException {
        if (instance==null) {
            instance = new CompanyFacade();
        }
        return instance;
    }

    public boolean login(Credentials cred) {
        System.out.println("in company login");
        return companyDAO.isCompanyExists(cred.getEmail(), cred.getPassword());
    }

    public int addCoupon(Coupon coupon) throws SQLException {
        System.out.println("\n\n"+couponDAO.isCouponWithCompanyIdAndTitleExist(coupon.getCompanyID(), coupon.getTitle())+"\n\n");
        if (!couponDAO.isCouponWithCompanyIdAndTitleExist(coupon.getCompanyID(), coupon.getTitle())) {
            System.out.println("adding coupon id = "+coupon.getID());
            return couponDAO.add(coupon);
        }
        return -1;
    }

    public void updateCoupon(Coupon coupon) throws SQLException {
        couponDAO.update(coupon);
    }

    public void deleteCoupon(int id) throws SQLException {
        couponDAO.remove(id);
    }

    public ArrayList<Coupon> getCompanyCouponsByCompanyID(int companyID) {
        return couponDAO.getCompanyCouponsByCompanyID(companyID);
    }

    public ArrayList<Coupon> getCompanyCouponsByCompanyIDCategory(int companyID, Coupon.Category category) {
        return couponDAO.getCompanyCouponsByCompanyIDCategory(companyID, category);
    }


    public ArrayList<Coupon> getCompanyCouponsByCompanyIDPrice(int companyID, Double maxPrice) {
        return couponDAO.getCompanyCouponsByCompanyIDPrice(companyID, maxPrice);
    }

    public Company getCompanyDetails(int companyID) {
        return companyDAO.getCompanyDetails(companyID);
    }


}








//package com.leumi.coupon_project.facade;
//
//import com.leumi.coupon_project.data.Coupon;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class CompanyFacade extends ClientFacade {
//
//    int companyID;
//
//    public CompanyFacade(int companyID) throws SQLException, ClassNotFoundException {
//        super();
//        this.companyID=companyID;
//    }
//
//    @Override
//    public boolean login(String email, String password) {
//        return companyDAO.validateLoginInfo(companyID, email, password);
//    }
//
//    public int addCoupon(Coupon coupon) throws SQLException {
//        if (couponDAO.isCouponWithCompanyIdAndTitleExist(coupon.getCompanyID(), coupon.getTitle())) {
//            couponDAO.add(coupon);
//        }
//        return 0;
//    }
//
//    public void updateCoupon(Coupon coupon) throws SQLException {
//        couponDAO.update(coupon);
//    }
//
//    public void deleteCoupon(int id) throws SQLException {
//        //TODO: delete only if coupon belongs to this company?
//        couponDAO.remove(id);
//    }
//
//    public ArrayList<Coupon> getCompanyCoupons() {
//        return couponDAO.getCompanyCouponsByCompanyID(companyID);
//    }
//
//    public ArrayList<Coupon> getCompanyCoupons(Coupon.Category category) {
//       return couponDAO.getCompanyCouponsByCompanyIDCategory(companyID, category);
//    }
//
//
//    public ArrayList<Coupon> getCompanyCoupons(Double maxPrice) {
//        return couponDAO.getCompanyCouponsByCompanyIDPrice(companyID, maxPrice);
//    }
//
//    //TODO: Yael needs to implement
////    public Company getCompanyDetails() {
////        return companyDAO.getCompanyDetails(companyID);
////    }
//
//
//}
