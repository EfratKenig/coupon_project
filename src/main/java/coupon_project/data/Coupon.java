package coupon_project.data;

import jdk.jfr.Category;

import java.util.Date;

public class Coupon {
    public enum Category{
        Food,
        Electricity,
        Restaurant,
        Vacation;
    }

    public int ID;
    public int companyID;
    public Category category;
    public String title;
    public String description;
    public Date startDate;
    public Date endDate;
    public int amount;
    public double price;
    public String image;

    public Coupon(int ID, int companyID, Category category, String title,
                  String description, Date startDate, Date endDate,
                  int amount, double price, String image) {
        this.ID = ID;
        this.companyID = companyID;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

    public Coupon(int ID, int companyID, Category category, String title, double price) {
        this.ID = ID;
        this.companyID = companyID;
        this.category = category;
        this.title = title;
        this.description = "";
        this.amount = 0;
        this.price = price;
        this.image = "";
        this.startDate = new Date(System.currentTimeMillis());
        this.endDate = new Date(System.currentTimeMillis());
    }

    public int getID() {
        return ID;
    }

    public int getCompanyID() {
        return companyID;
    }

    public Category getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String
    toString() {
        return "Coupon{" +
                "ID=" + ID +
                ", companyID=" + companyID +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
