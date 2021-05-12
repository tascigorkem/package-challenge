package com.mobiquity.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PackageItem implements Comparable<PackageItem> {
    private int index;
    private double weight;
    private int cost;

    @Override
    public int compareTo(PackageItem packageItem) {
        return this.index - packageItem.index;
    }
}
