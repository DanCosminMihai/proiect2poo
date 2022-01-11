package database;

import com.fasterxml.jackson.annotation.JsonIgnore;

public final class Gift {

  private String productName;
  private Double price;
  private String category;
  @JsonIgnore
  private Integer quantity;

  public Gift(final String productName, final Double price, final String category,
      final Integer quantity) {
    this.productName = productName;
    this.price = price;
    this.category = category;
    this.quantity = quantity;
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

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(final Integer quantity) {
    this.quantity = quantity;
  }

}
