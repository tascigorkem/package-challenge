package com.tascigorkem.packer;

import com.tascigorkem.dto.Input;
import com.tascigorkem.dto.PackageBox;
import com.tascigorkem.dto.PackageItem;
import com.tascigorkem.exception.APIException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Translate files into beans and reverse.
 */
public class FileTranslator {

    /**
     * Obtain Input Bean from Input File
     *
     * @param file input file
     * @return input bean
     * @throws APIException is thrown if validation fails
     */
    public static Input readInputFile(File file) throws APIException {
        Input input = new Input();
        List<PackageBox> packageBoxes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (String line; (line = br.readLine()) != null; ) {
                PackageBox packageBox = new PackageBox();
                String[] partsOfPackageBox = line.split(" : ");

                // parse package box's max weight
                int weightLimit = Integer.parseInt(partsOfPackageBox[0]);
                packageBox.setWeightLimit(weightLimit);

                // parse package items from "(1,85.31,€29) (2,14.55,€74) ..."
                PackageItem[] packageItems = parsePackageItems(partsOfPackageBox[1]);
                packageBox.setPackageItems(packageItems);

                packageBoxes.add(packageBox);
            }
        } catch (IOException ex) {
            throw new APIException(MessageFormat.format("File read error: {0}", ex.getMessage()), ex);
        }

        input.setPackageBoxes(packageBoxes);
        return input;
    }

    /**
     * Parse package items from "(1,85.31,€29) (2,14.55,€74) ..."
     *
     * @param packageItemListString string of package items
     * @return package items array
     */
    private static PackageItem[] parsePackageItems(String packageItemListString) {

        String[] packageItemsString = packageItemListString.split(" ");
        PackageItem[] packageItems = new PackageItem[packageItemsString.length];
        for (int i = 0; i < packageItemsString.length; i++) {
            String pItemWithParentheses = packageItemsString[i];

            // remove parentheses
            String packageItemString = pItemWithParentheses.substring(1, pItemWithParentheses.length() - 1);
            String[] partsOfItem = packageItemString.split(",");

            PackageItem packageItem = new PackageItem();
            packageItem.setIndex(Integer.parseInt(partsOfItem[0]));
            packageItem.setWeight(Double.parseDouble(partsOfItem[1]));
            packageItem.setCost(Integer.parseInt(partsOfItem[2].substring(1)));
            packageItems[i] = packageItem;
        }
        return packageItems;
    }
}
