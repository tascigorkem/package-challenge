package com.tascigorkem.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Input {
    private List<PackageBox> packageBoxes;

}
