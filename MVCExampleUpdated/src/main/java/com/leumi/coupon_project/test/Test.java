package com.leumi.coupon_project.test;

import com.leumi.coupon_project.database.CouponExpirationDailyJob;

import java.sql.SQLException;

public class Test {
    public static void testAll() throws SQLException, ClassNotFoundException {
        //run daily job:
        CouponExpirationDailyJob job = new CouponExpirationDailyJob();
        job.run();

        //TODO: other tests use LoginManager. finish LoginManager first.
        //log in as an admin

    }

}
