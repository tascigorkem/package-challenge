package com.mobiquity.packer;

import com.mobiquity.dto.Input;
import com.mobiquity.dto.Output;
import com.mobiquity.dto.PackageBox;
import com.mobiquity.dto.PackageItem;
import com.mobiquity.exception.APIException;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Packer {

    private static final Logger logger = Logger.getLogger(Packer.class.getName());

    /**
     * @param filePath is file path of the input file
     * @return output string of the result
     * @throws APIException is thrown if the input file doesn't exist or validation fails
     */
    public static String pack(String filePath) throws APIException {
        long start = System.currentTimeMillis();

        // read file and convert into bean
        File inFile = new File(filePath);
        Input input = FileTranslator.readInputFile(inFile);

        // validate input
        for (PackageBox packageBox : input.getPackageBoxes()) {
            Validator.validateInputPackageBox(packageBox);
        }

        // calculate and retrieve output packages
        List<PackageBox> resultPackageBoxes = input.getPackageBoxes().stream()
                .map(Packer::calculatePackageBox)
                .collect(Collectors.toList());

        // build output
        Output output = new Output(resultPackageBoxes);

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        logger.info(MessageFormat.format("Time: {0} ms.", timeElapsed));

        return output.toString();
    }


    /**
     * Calculates packageBox and return packageBox with selected package itemse
     *
     * @param packageBox to be calculated
     * @return PackageBox is calculated with selected package items
     */
    private static PackageBox calculatePackageBox(PackageBox packageBox) {
        int weightLimit = packageBox.getWeightLimit();
        // sort items according to weights to choose minimum weight package
        // in case there is more than one package with the same price.
        PackageItem[] sortedInitialPackageItems = Arrays.stream(packageBox.getPackageItems())
                .sorted(Comparator.comparingDouble(PackageItem::getWeight)).toArray(PackageItem[]::new);

        int[] weightMatrix = new int[sortedInitialPackageItems.length];
        int[] valueMatrix = new int[sortedInitialPackageItems.length];

        for (int i = 0; i < sortedInitialPackageItems.length; i++) {
            weightMatrix[i] = (int) sortedInitialPackageItems[i].getWeight();
            valueMatrix[i] = sortedInitialPackageItems[i].getCost();
        }

        // construct 2D cost matrix for weights [itemSize+1][weightLimit+1]
        int[][] costMatrix = new int[sortedInitialPackageItems.length + 1][weightLimit + 1];
        // fill 2D cost matrix with dynamic programming
        knapsack(sortedInitialPackageItems.length, weightLimit, weightMatrix, valueMatrix, costMatrix);

        // iterate 2D cost matrix to find selected item indexes
        List<Integer> selectedItemIndexes = findAndGetSelectedItemIndexes(
                sortedInitialPackageItems.length, weightLimit, weightMatrix, costMatrix);

        // convert indexes to package items
        PackageItem[] selectedPackageItems = new PackageItem[selectedItemIndexes.size()];
        for (int i = 0; i < selectedItemIndexes.size(); i++) {
            selectedPackageItems[i] = sortedInitialPackageItems[selectedItemIndexes.get(i)];
        }

        // return package box with selected package items
        return new PackageBox(weightLimit, selectedPackageItems);
    }

    /**
     * 0-1 Knapsack implementation with dynamic programming
     * Fill the 2D cost matrix with dynamic programming
     *
     * if w[i] > maxWeight
     * then F(i, maxWeight) = F(i-1, maxWeight)
     * <p>
     * else if w[i] < maxWeight
     * then F(i, maxWeight) = max{ F(i-1, maxWeight), F(i-1, maxWeight-w[i]) + v[i] }
     */
    static int[][] knapsack(int itemsLength, int maxWeight, int[] wm, int[] vm, int[][] cost) {
        for (int i = 0; i <= itemsLength; i++) {
            for (int w = 0; w <= maxWeight; w++) {
                if (i == 0 || w == 0)
                    cost[i][w] = 0;
                else if (wm[i - 1] > w) {
                    cost[i][w] = cost[i - 1][w];
                } else {
                    cost[i][w] = Math.max(
                            vm[i - 1] + cost[i - 1][w - wm[i - 1]],
                            cost[i - 1][w]);
                }
            }
        }
        return cost;
    }

    /**
     * Iterate 2D cost matrix to find selected item indexes
     */
    public static List<Integer> findAndGetSelectedItemIndexes(
            int itemsLength, int maxWeight, int[] wm, int[][] cost) {

        int i = itemsLength;
        int j = maxWeight;

        List<Integer> itemIndexes = new ArrayList<>();
        while (i > 0 && j > 0) {
            if (cost[i][j] != cost[i - 1][j]) {
                itemIndexes.add(i - 1);
                j = j - wm[i - 1];
            }
            i--;
        }
        return itemIndexes;
    }
}
