package com.tascigorkem.packer;

import com.tascigorkem.dto.PackageBox;
import com.tascigorkem.dto.PackageItem;
import com.tascigorkem.exception.APIException;

import java.text.MessageFormat;

public class Validator {

  /**
   * Validation of PackageBox
   *
   * @param packageBox to be validated
   * @throws APIException is thrown if validation fails
   */
  public static void validateInputPackageBox(PackageBox packageBox) throws APIException {
    if (packageBox.getWeightLimit() > 100)
      throw invalidMaxWeight(packageBox);

    if (packageBox.getPackageItems().length > 15)
      throw invalidMaxItemSize(packageBox);

    for (PackageItem packageItem : packageBox.getPackageItems()) {
      if (packageItem.getCost() > 100)
        throw invalidCostOfItem(packageBox, packageItem);

      if (packageItem.getWeight() > 100)
        throw invalidWeightOfItem(packageBox, packageItem);
    }
  }

  /**
   * Given input package box with max weight greater than 100
   *
   * @param packageBox is invalid
   * @return APIException is thrown
   */
  private static APIException invalidMaxWeight(PackageBox packageBox) {
    return new APIException(MessageFormat.format(
            "Max weight that a package can take is ≤ 100. But it is {0}. \n\n For this {1}.\n", packageBox.getWeightLimit(), packageBox));

  }

  /**
   * Given input package box with more than 15 items
   *
   * @param packageBox is invalid
   * @return APIException is thrown
   */
  private static APIException invalidMaxItemSize(PackageBox packageBox) {
    return new APIException(MessageFormat.format(
            "There might be up to 15 items you need to choose from. But it is {0}. \n\n For this {1}.\n", packageBox.getPackageItems().length, packageBox));
  }

  /**
   * Given input package box consists package item which has cost greater than 100
   *
   * @param packageBox is invalid
   * @param packageItem is invalid
   * @return APIException is thrown
   */
  private static APIException invalidCostOfItem(PackageBox packageBox, PackageItem packageItem) {
    return new APIException(MessageFormat.format(
            "Max cost of an item should be ≤ 100. But it is {0}. \n\n For this {1}.\n", packageItem.getCost(), packageBox));
  }

  /**
   * Given input package box consists package item which has weight greater than 100
   *
   * @param packageBox is invalid
   * @param packageItem is invalid
   * @return APIException is thrown
   */
  private static APIException invalidWeightOfItem(PackageBox packageBox, PackageItem packageItem) {
    return new APIException(MessageFormat.format(
            "Max weight of an item should be ≤ 100. But it is {0}. \n\n For this {1}.\n", packageItem.getWeight(), packageBox));
  }

}
