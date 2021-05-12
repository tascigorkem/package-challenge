package com.mobiquity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Output {

    private List<PackageBox> packageBoxes;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (PackageBox packageBox : packageBoxes) {
            PackageItem[] packageItems = packageBox.getPackageItems();
            Arrays.sort(packageItems); // sort package items by index

            if (packageItems.length == 0)
                stringBuilder.append("-");

            for (int i = 0; i < packageItems.length; i++) {
                stringBuilder.append(String.format("%d", packageItems[i].getIndex()));
                if (i != packageItems.length - 1)
                    stringBuilder.append(",");
            }

            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}

