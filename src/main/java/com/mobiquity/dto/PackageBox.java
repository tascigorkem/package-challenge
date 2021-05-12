package com.mobiquity.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PackageBox {
    private int weightLimit;
    private PackageItem[] packageItems;

}
