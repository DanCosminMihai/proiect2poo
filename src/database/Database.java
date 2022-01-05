package database;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Constants;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class Database {

  private Integer numberOfYears;
  private Double santaBudget;
  private ArrayList<Child> children;
  private ArrayList<Gift> santaGiftsList;
  private ArrayList<AnnualChange> annualChanges;

  /**
   * Returns a child object with the given corresponding id.
   *
   * @param id
   * @param database
   * @return Child object
   */
  public static Child getChildById(final int id, final Database database) {
    for (Child child : database.getChildren()) {
      if (child.getId() == id) {
        return child;
      }
    }
    return null;
  }

  /**
   * Reads the input from the json file and creates database for the given test.
   *
   * @param test
   */
  public void readInput(final String test) {
    ObjectMapper objectMapper = new ObjectMapper();
    File file = new File(Constants.TESTS_PATH + test + Constants.FILE_EXTENSION);
    //read data from json file
    try {
      JsonNode jsonNode = objectMapper.readTree(file);
      numberOfYears = jsonNode.get("numberOfYears").asInt();
      santaBudget = jsonNode.get("santaBudget").asDouble();

      JsonNode kids = jsonNode.get("initialData").get("children");
      children = new ArrayList<Child>();
      for (JsonNode kid : kids) {
        ArrayList<String> prefs = new ArrayList<String>();
        kid.get("giftsPreferences").forEach((e) -> prefs.add(e.asText()));
        children.add(new Child(kid.get("id").asInt(),
            kid.get("lastName").asText(), kid.get("firstName").asText(),
            kid.get("age").asInt(), kid.get("city").asText(),
            kid.get("niceScore").asDouble(), prefs));
      }

      JsonNode gifts = jsonNode.get("initialData").get("santaGiftsList");
      santaGiftsList = new ArrayList<Gift>();
      for (JsonNode gift : gifts) {
        santaGiftsList.add(new Gift(gift.get("productName").asText(), gift.get("price").asDouble(),
            gift.get("category").asText()));
      }

      annualChanges = new ArrayList<AnnualChange>();
      JsonNode changes = jsonNode.get("annualChanges");
      for (JsonNode change : changes) {
        JsonNode newGifts = change.get("newGifts");
        ArrayList<Gift> ng = new ArrayList<Gift>();
        for (JsonNode gift : newGifts) {
          ng.add(new Gift(gift.get("productName").asText(), gift.get("price").asDouble(),
              gift.get("category").asText()));
        }
        JsonNode newKids = change.get("newChildren");
        ArrayList<Child> nc = new ArrayList<Child>();
        for (JsonNode kid : newKids) {
          ArrayList<String> prefs = new ArrayList<String>();
          kid.get("giftsPreferences").forEach((e) -> prefs.add(e.asText()));
          nc.add(new Child(kid.get("id").asInt(),
              kid.get("lastName").asText(), kid.get("firstName").asText(),
              kid.get("age").asInt(), kid.get("city").asText(),
              kid.get("niceScore").asDouble(), prefs));
        }

        JsonNode kidUpdate = change.get("childrenUpdates");
        ArrayList<ChildUpdate> cu = new ArrayList<ChildUpdate>();
        for (JsonNode update : kidUpdate) {
          ArrayList<String> prefs = new ArrayList<String>();
          update.get("giftsPreferences").forEach((e) -> prefs.add(e.asText()));
          cu.add(new ChildUpdate(update.get("id").asInt(),
              update.get("niceScore").asText(), prefs));
        }
        annualChanges.add(new AnnualChange(change.get("newSantaBudget").asDouble(), ng, nc, cu));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Integer getNumberOfYears() {
    return numberOfYears;
  }

  public void setNumberOfYears(final Integer numberOfYears) {
    this.numberOfYears = numberOfYears;
  }

  public Double getSantaBudget() {
    return santaBudget;
  }

  public void setSantaBudget(final Double santaBudget) {
    this.santaBudget = santaBudget;
  }

  public ArrayList<Child> getChildren() {
    return children;
  }

  public void setChildren(final ArrayList<Child> children) {
    this.children = children;
  }

  public ArrayList<Gift> getSantaGiftsList() {
    return santaGiftsList;
  }

  public void setSantaGiftsList(final ArrayList<Gift> santaGiftsList) {
    this.santaGiftsList = santaGiftsList;
  }

  public ArrayList<AnnualChange> getAnnualChanges() {
    return annualChanges;
  }

  public void setAnnualChanges(final ArrayList<AnnualChange> annualChanges) {
    this.annualChanges = annualChanges;
  }
}
