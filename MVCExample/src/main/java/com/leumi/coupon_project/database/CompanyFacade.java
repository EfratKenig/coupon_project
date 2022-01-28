package com.leumi.coupon_project.database;

import com.leumi.coupon_project.DAO.CompanyDAO;
import com.leumi.coupon_project.DAO.CompanyDBDAO;

import java.sql.SQLException;

public class CompanyFacade {

	private static CompanyDAO companyDAO;

	static {
		try {
			companyDAO = CompanyDBDAO.getInstance();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//CouponDAO couponDAO = CouponDBDAO.getInstance();

	public static void deleteCompany(int id) throws SQLException {
		//Example only: not the final solution!
		companyDAO.remove(id);
		//use couponDAO to delete all the coupons that belongs to the above company
		//couponDAO.deleteRelatedCoupons(id) 
		//...
	}

    public void deleteCoupon(int i) {
    }
}
