//package com.leumi.coupon_project.database;
//
//import com.example.coupon_system.DAO.CouponDAO;
//import com.example.coupon_system.DAO.CouponDBDAO;
//
//import java.sql.SQLException;
//
////TODO: run in main and stop before the program ends
//public class CouponExpirationDailyJob implements Runnable{
//    private CouponDAO couponDAO;
//    private boolean quit;
//
//    public CouponExpirationDailyJob() throws SQLException, ClassNotFoundException {
//        this.couponDAO = new CouponDBDAO().getInstance();
//        this.quit = false;
//    }
//
//    @Override
//    public void run() {
//        //go to coupons table, delete expired coupons+ delete from history
//        //for now the deleted coupons will automatically be deleted from
//        //history because it is a cascade foreign key
//
//        //
//    }
//
//    public void stop(){
//
//    }
//}
