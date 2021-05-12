package com.mobiquity.packer;

import com.mobiquity.dto.PackageBox;
import com.mobiquity.dto.PackageItem;

import java.util.Arrays;
import java.util.List;

public class PackageBoxGenerator {

    // valid packages

    /**
     * Returns input package boxes for "example_input" file
     *
     * @return package boxes
     */
    static List<PackageBox> getFakeInputPackageBoxes() {
        List<PackageBox> packageBoxes = Arrays.asList(
                new PackageBox(81, new PackageItem[]{
                        new PackageItem(1, 53.38, 45),
                        new PackageItem(2, 88.62, 98),
                        new PackageItem(3, 78.48, 3),
                        new PackageItem(4, 72.30, 76),
                        new PackageItem(5, 30.18, 9),
                        new PackageItem(6, 46.34, 48)
                }),
                new PackageBox(8, new PackageItem[]{
                        new PackageItem(1, 15.3, 34)
                }),
                new PackageBox(75, new PackageItem[]{
                        new PackageItem(1, 85.31, 29),
                        new PackageItem(2, 14.55, 74),
                        new PackageItem(3, 3.98, 16),
                        new PackageItem(4, 26.24, 55),
                        new PackageItem(5, 63.69, 52),
                        new PackageItem(6, 76.25, 75),
                        new PackageItem(7, 60.02, 74),
                        new PackageItem(8, 93.18, 35),
                        new PackageItem(9, 89.95, 78)
                }),
                new PackageBox(56, new PackageItem[]{
                        new PackageItem(1, 90.72, 13),
                        new PackageItem(2, 33.80, 40),
                        new PackageItem(3, 43.15, 10),
                        new PackageItem(4, 37.97, 16),
                        new PackageItem(5, 46.81, 36),
                        new PackageItem(6, 48.77, 79),
                        new PackageItem(7, 81.80, 45),
                        new PackageItem(8, 19.36, 79),
                        new PackageItem(9, 6.76, 64)
                })
        );
        return packageBoxes;
    }

    /**
     * Returns output package boxes for "example_input" file
     *
     * @return package boxes
     */
    static List<PackageBox> getFakeOutputPackageBoxes() {
        List<PackageBox> packageBoxes = Arrays.asList(
                new PackageBox(81, new PackageItem[]{
                        new PackageItem(4, 72.30, 76),
                }),
                new PackageBox(8, new PackageItem[]{}),
                new PackageBox(75, new PackageItem[]{
                        new PackageItem(2, 14.55, 98),
                        new PackageItem(7, 60.02, 74),
                }),
                new PackageBox(56, new PackageItem[]{
                        new PackageItem(8, 19.36, 79),
                        new PackageItem(9, 6.76, 64)
                })
        );
        return packageBoxes;
    }

    // invalid packages (validation failed)

    /**
     * Returns input package box with max weight greater than 100
     *
     * @return package box
     */
    public static PackageBox getInvalidPackageBoxWithMaxWeightGreaterThan100() {
        return new PackageBox(105, // breaking validation
                new PackageItem[]{
                        new PackageItem(1, 53.38, 45),
                        new PackageItem(2, 88.62, 98),
                        new PackageItem(3, 78.48, 123)
                });
    }
    /**
     * Returns input package box with more than 15 items
     *
     * @return package box
     */
    public static PackageBox getInvalidPackageBoxWithGreaterThan15Items() {
        return new PackageBox(81,
                new PackageItem[]{
                        new PackageItem(1, 53.38, 45),
                        new PackageItem(2, 88.62, 98),
                        new PackageItem(3, 78.48, 3),
                        new PackageItem(4, 72.30, 76),
                        new PackageItem(5, 30.18, 9),
                        new PackageItem(6, 46.34, 48),
                        new PackageItem(7, 85.31, 29),
                        new PackageItem(8, 14.55, 74),
                        new PackageItem(9, 3.98, 16),
                        new PackageItem(10, 26.24, 55),
                        new PackageItem(11, 63.69, 52),
                        new PackageItem(12, 76.25, 75),
                        new PackageItem(13, 60.02, 74),
                        new PackageItem(14, 93.18, 35),
                        new PackageItem(15, 89.95, 78),
                        new PackageItem(16, 90.72, 13)
                });
    }

    /**
     * Returns input package box consists package item which has cost greater than 100
     *
     * @return package box
     */
    public static PackageBox getInvalidPackageBoxConsistsItemWithCostGreaterThan100() {
        return new PackageBox(81,
                new PackageItem[]{
                        new PackageItem(1, 53.38, 45),
                        new PackageItem(2, 88.62, 98),
                        new PackageItem(3, 78.48, 123) // breaking validation
                });
    }

    /**
     * Returns input package box consists package item which has weight greater than 100
     *
     * @return package box
     */
    public static PackageBox getInvalidPackageBoxConsistsItemWithWeightGreaterThan100() {
        return new PackageBox(81,
                new PackageItem[]{
                        new PackageItem(1, 53.38, 45),
                        new PackageItem(2, 88.62, 98),
                        new PackageItem(3, 178.48, 3) // breaking validation
                });
    }

}
