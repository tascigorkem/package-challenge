package com.tascigorkem.packer;

import com.tascigorkem.dto.PackageBox;
import com.tascigorkem.exception.APIException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;

class ValidatorTest {


    /**
     * Test Validator.validateInputPackageBox method
     *
     * Given input package box with max weight greater than 100
     */
    @Test
    void validateInputPackageBox_GivenMaxWeightGreaterThan_100_OfPackageBox_ShouldThrowAPIExceptionWithRightMessage() {
        // GIVEN:
        PackageBox packageBox = PackageBoxGenerator.getInvalidPackageBoxWithMaxWeightGreaterThan100();

        // WHEN:
        APIException resultException = Assertions.assertThrows(APIException.class,
                () -> Validator.validateInputPackageBox(packageBox));

        // THEN:
        APIException expectedException = new APIException(MessageFormat.format(
                "Max weight that a package can take is ≤ 100. But it is {0}. \n\n For this {1}.\n", packageBox.getWeightLimit(), packageBox));

        Assertions.assertEquals(expectedException.getMessage(), resultException.getMessage());
    }

    /**
     * Test Validator.validateInputPackageBox method
     *
     * Given input package box with more than 15 items
     */
    @Test
    void validateInputPackageBox_GivenGreaterThan_15_ItemsInPackageBox_ShouldThrowAPIExceptionWithRightMessage() {
        // GIVEN:
        PackageBox packageBox = PackageBoxGenerator.getInvalidPackageBoxWithGreaterThan15Items();

        // WHEN:
        APIException resultException = Assertions.assertThrows(APIException.class,
                () -> Validator.validateInputPackageBox(packageBox));

        // THEN:
        APIException expectedException = new APIException(MessageFormat.format(
                "There might be up to 15 items you need to choose from. But it is {0}. \n\n For this {1}.\n", packageBox.getPackageItems().length, packageBox));

        Assertions.assertEquals(expectedException.getMessage(), resultException.getMessage());

    }


    /**
     * Test Validator.validateInputPackageBox method
     *
     * Given input package box consists package item which has cost greater than 100
     */
    @Test
    void validateInputPackageBox_GivenCostGreaterThan_100_OfPackageItemInPackageBox_ShouldThrowAPIExceptionWithRightMessage() {
        // GIVEN:
        PackageBox packageBox = PackageBoxGenerator.getInvalidPackageBoxConsistsItemWithCostGreaterThan100();

        // WHEN:
        APIException resultException = Assertions.assertThrows(APIException.class,
                () -> Validator.validateInputPackageBox(packageBox));

        // THEN:
        APIException expectedException = new APIException(MessageFormat.format(
                "Max cost of an item should be ≤ 100. But it is {0}. \n\n For this {1}.\n", 123, packageBox));

        Assertions.assertEquals(expectedException.getMessage(), resultException.getMessage());
    }


    /**
     * Test Validator.validateInputPackageBox method
     *
     * Given input package box consists package item which has weight greater than 100
     */
    @Test
    void validateInputPackageBox_GivenWeightGreaterThan_100_OfPackageItemInPackageBox_ShouldThrowAPIExceptionWithRightMessage() {
        // GIVEN:
        PackageBox packageBox = PackageBoxGenerator.getInvalidPackageBoxConsistsItemWithWeightGreaterThan100();

        // WHEN:
        APIException resultException = Assertions.assertThrows(APIException.class,
                () -> Validator.validateInputPackageBox(packageBox));

        // THEN:
        APIException expectedException = new APIException(MessageFormat.format(
                "Max weight of an item should be ≤ 100. But it is {0}. \n\n For this {1}.\n", 178.48, packageBox));

        Assertions.assertEquals(expectedException.getMessage(), resultException.getMessage());
    }
}
