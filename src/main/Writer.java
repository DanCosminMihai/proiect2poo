package main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import common.Constants;
import database.Child;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public final class Writer {

  private JSONArray output;

  /**
   * Adds current database status to an output json object.
   *
   * @param children
   */
  public void addToOutput(final ArrayList<Child> children) {
    ObjectMapper objectMapper = new ObjectMapper();
    ArrayNode arrayNode = objectMapper.valueToTree(children);
    JsonNode jsonNode = objectMapper.createObjectNode().set("children", arrayNode);
    output.add(jsonNode);
  }

  public Writer() {
    this.output = new JSONArray();
  }

  /**
   * Writes the output json object to the file corresponding to the current test.
   *
   * @param test
   */
  public void writeJSON(final String test) {
    ObjectMapper objectMapper = new ObjectMapper();

    File dir = new File("output");
    if (!dir.exists()) {
      dir.mkdir();
    }
    JSONObject object = new JSONObject();
    object.put("annualChildren", output);
    File out = new File(Constants.OUTPUT_PATH + test + Constants.FILE_EXTENSION);
    try {
      objectMapper.writerWithDefaultPrettyPrinter().writeValue(out, object);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
