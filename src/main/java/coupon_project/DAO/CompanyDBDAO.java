package coupon_project.DAO;

import coupon_project.database.CompanyEntity;
import coupon_project.data.DataBaseManager;

import java.sql.ResultSet;

public class CompanyDBDAO implements CompanyDAO {

	private static CompanyDBDAO instance;
	
	private CompanyDBDAO()
	{
		super();
	}
	
	public static CompanyDBDAO getInstance()
	{
		if (instance==null) {
			instance = new CompanyDBDAO();
		}
		return instance;
	}
	DataBaseManager dbm = DataBaseManager.getInstance();

	public int add(CompanyEntity entity) {
		System.out.println("TBD - add is not implemented!");
		System.out.println("ADD: "+entity);
		return 0;
	}

	public void remove(int id) {
		System.out.println("TBD - remove is not implemented!");
		System.out.println("ID: "+id);
	}

	public void update(CompanyEntity entity) {
		System.out.println("TBD - update is not implemented!");
		System.out.println("UPDATE: "+entity);
	}

	public CompanyEntity get(int id) {
		try {
			String sql = "SELECT * FROM company where id="+id;
			ResultSet resultset = dbm.runGetQuarry(sql);
			while (resultset.next()) {
				CompanyEntity entity = new CompanyEntity();
				int idDB = resultset.getInt("id");
				entity.setId(idDB);
				String name = resultset.getString("name");
				entity.setName(name);
				return entity;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
