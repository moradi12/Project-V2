package beans;


import java.util.Date;

public class Coupon {


    private final int id;
    private final int companyId;
    private final Category category;
    private String title;

    private String description;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private Integer amount;
    private Double price;
    private String image;

    /**
     * Constructs a coupon with specified attributes.
     *
     * @param id          the ID of the coupon
     * @param companyId   the ID of the company associated with the coupon
     * @param category    the category of the coupon
     * @param title       the title of the coupon
     * @param description the description of the coupon
     * @param startDate   the start date of the coupon validity
     * @param endDate     the end date of the coupon validity
     * @param amount      the amount of the coupon available
     * @param price       the price of the coupon
     * @param image       the image URL associated with the coupon
     */
    public Coupon(int id, int companyId, Category category, String title, String description, java.sql.Date startDate, java.sql.Date endDate, Integer amount, Double price, String image) {
        this.id = id;
        this.companyId = companyId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public Category getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(java.sql.Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(java.sql.Date endDate) {
        this.endDate = endDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", companyId=" + companyId +
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

