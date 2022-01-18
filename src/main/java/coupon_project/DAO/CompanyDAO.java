package coupon_project.DAO;

import coupon_project.database.CompanyEntity;

public interface CompanyDAO {

	//CRUD functions

	int add(CompanyEntity entity);
	void remove(int id);
	CompanyEntity get(int id);
	void update(CompanyEntity entity);
}
