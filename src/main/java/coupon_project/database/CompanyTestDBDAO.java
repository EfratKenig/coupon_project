package coupon_project.database;

import coupon_project.DAO.CompanyDAO;

//Only for the presentation - not for use in the project
public class CompanyTestDBDAO implements CompanyDAO {

	public int add(CompanyEntity entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void remove(int id) {
		// TODO Auto-generated method stub

	}

	public CompanyEntity get(int id) {
		CompanyEntity res = new CompanyEntity();
		res.setId(id);
		//Getting from test DB
		if (id == 1) {
			res.setName("Test Company Leumi");
		} else if (id == 2) {
			res.setName("Test Company Johnbryce");
		} else if (id == 3) {
			res.setName("Test Company Mindolife");
		}
		return res;
	}

	public void update(CompanyEntity entity) {
		// TODO Auto-generated method stub

	}

}
