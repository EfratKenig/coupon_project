package com.leumi.coupon_project.DAO;

import com.leumi.coupon_project.data.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CouponDAO {

    //CRUD functions

    int add(Coupon coupon) throws SQLException;
    void remove(int id) throws SQLException;
    Coupon getOneCoupon(int id);
    void update(Coupon coupon) throws SQLException;

    boolean isCouponWithCompanyIdAndTitleExist(int companyID, String title) throws SQLException;

    //yaels func for company facade:
    ArrayList<Coupon> getCompanyCouponsByCompanyIDPrice(int companyId, Double price);
    ArrayList<Coupon> getCompanyCouponsByCompanyIDCategory(int companyId, Coupon.Category category);
    ArrayList<Coupon> getCompanyCouponsByCompanyID(int companyId);

    ArrayList<Integer> getCompanyCouponsIDs(int companyId);

    int deleteOldCoupons() throws SQLException;
}
