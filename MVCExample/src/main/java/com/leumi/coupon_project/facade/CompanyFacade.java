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
