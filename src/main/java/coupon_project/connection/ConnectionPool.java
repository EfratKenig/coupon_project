package coupon_project.connection;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.*;

import static java.nio.file.Files.readString;

public class ConnectionPool {
    private static ConnectionPool single_instance = null;
    private static final String DOMAIN_STRING = "localhost";// 127.0.0.1
    private static final String DB_NAME = "coupon_management";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "1234";
    private static final String CONNECTION_STRING = "jdbc:mysql://" + DOMAIN_STRING + "/" + DB_NAME + "?user="
            + USER_NAME + "&password=" + PASSWORD;
    private static Connection connection;

    public ConnectionPool() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("JDBC driver loaded successfully!");
        System.out.println("Connecting to database: " + CONNECTION_STRING);
        connection = DriverManager.getConnection(CONNECTION_STRING);
        System.out.println("Connected to database " + DB_NAME);
    }

    ConnectionPool getConnection() throws SQLException, ClassNotFoundException {
        if (single_instance == null)
            single_instance = new ConnectionPool();

        return single_instance;
    }

    static void closeAllConnections() throws SQLException {
        connection.close();
    }

//    private static void createDatabase() throws IOException {
//        //Sari's code
//        String createDBStr = readString(Path.of("coupon_project/data/createDB.txt"));
//    }
    public ResultSet runGetQuarry(String sql) throws SQLException {
        System.out.println("runGetQuarry()= " + sql);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultset = preparedStatement.executeQuery();
        System.out.println("runGetQuarry() done!");
        return resultset;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionPool newDB = new ConnectionPool();
        System.out.println("Loading MySQL server...");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from companies");
        ResultSet resultset = preparedStatement.executeQuery();
        while (resultset.next()) {
            String name = resultset.getString("name");
            System.out.println(name);
        }
        try {
                closeAllConnections();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}




        //From outside
//		while (resultset.next()) {
//			EmploeeEntity emp = new EmploeeEntity();
//			int id = resultset.getInt("id");
//			emp.setId(id);
//			String name = resultset.getString("name");
//			emp.setName(name);
//			int salary = resultset.getInt("salary");
//			emp.setSalary(salary);
//			resList.add(emp);
//		}
