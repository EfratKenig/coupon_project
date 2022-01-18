package coupon_project.database;

import coupon_project.DAO.CompanyDAO;
import coupon_project.DAO.CompanyDBDAO;

public class CouponDemoMain {

	public static void main(String[] args) {
		//Encapsulation
		CompanyDAO companyDAO = CompanyDBDAO.getInstance();
		
		//... Deeply in my code....
		CompanyEntity companyEntity = companyDAO.get(1);
		System.out.println(companyEntity);
		companyEntity = companyDAO.get(2);
		System.out.println(companyEntity);
		companyEntity = companyDAO.get(3);
		System.out.println(companyEntity);
		
		//Update
		CompanyEntity entityToAdd = new CompanyEntity();
		entityToAdd.setId(1);
		entityToAdd.setName("Leumi++");
		companyDAO.update(entityToAdd);
		
		//Delete
		companyDAO.remove(3);	
		
		
		//From the Main/App we use only the FACADE in order to update the database
		CompanyFacade.deleteCompany(3);
	}

}
