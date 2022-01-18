package coupon_project.database;

import coupon_project.DAO.CompanyDAO;
import coupon_project.DAO.CompanyDBDAO;

public class CompanyFacade {

	private static CompanyDAO companyDAO = CompanyDBDAO.getInstance();
	//CouponDAO couponDAO = CouponDBDAO.getInstance();

	public static void deleteCompany(int id)
	{
		//Example only: not the final solution!
		companyDAO.remove(id);
		//use couponDAO to delete all the coupons that belongs to the above company
		//couponDAO.deleteRelatedCoupons(id) 
		//...
	}
}
