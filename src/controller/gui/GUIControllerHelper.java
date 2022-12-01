package controller.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.FlexiblePortfolio;
import model.Model;
import model.utils.StatusObject;

public class GUIControllerHelper {

  static void processDCACreationHelper(FlexiblePortfolio portfolio, Map<String,
          String> dcaSetting, List<List<String>> tableData, Model model) {
    try {
      if (dcaSetting.get("startDate").equals("")) {
        throw new IllegalArgumentException("Start date cannot be empty");
      }
      if (dcaSetting.get("endDate").equals("")) {
        dcaSetting.put("endDate", "3000-01-01");
      }
      if (dcaSetting.get("startDate").equals("")) {
        throw new IllegalArgumentException("Start date cannot be empty");
      }
      if (dcaSetting.get("interval").equals("")
              | Integer.parseInt(dcaSetting.get("interval")) < 1) {
        throw new IllegalArgumentException("Interval has to be a positive integer");
      }
      if (dcaSetting.get("commission").equals("")
              | Double.parseDouble(dcaSetting.get("commission")) < 0) {
        throw new IllegalArgumentException("Commission has to be greater/equal to 0");
      }
      if (dcaSetting.get("dollarAmount").equals("")
              | Double.parseDouble(dcaSetting.get("dollarAmount")) < 0) {
        throw new IllegalArgumentException("Dollar amount has to be greater/equal to 0");
      }
      List<List<String>> filteredTable = new ArrayList<>();
      for (List<String> row : tableData) {
        if (Double.parseDouble(row.get(3)) != 0) {
          filteredTable.add(row);
        }
      }
      StatusObject<String> status = model.createDCAPlan(portfolio,
              dcaSetting.get("startDate"), dcaSetting.get("endDate"), dcaSetting.get("interval"),
              dcaSetting.get("dollarAmount"), dcaSetting.get("commission"), filteredTable);
      if (status.statusCode < 0) {
        throw new RuntimeException(status.statusMessage);
      }
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Entered number does not follow the correct format\n"
              + "Interval -> Positive integer\n"
              + "Dollar amount -> Positive number\n"
              + "Commission -> Positive number");
    }
  }
}
