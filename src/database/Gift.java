package database;

public final class Gift {

  private String productName;
  private Double price;
  private String category;

  public Gift(final String productName, final Double price, final String category) {
    this.productName = productName;
    this.price = price;
    this.category = category;
  }

  public String getProductName() {
    return productName;
  }

  public Double getPrice() {
    return price;
  }

  public String getCategory() {
    return category;
  }

}
