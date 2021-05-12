package com.mobiquity;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;

import java.io.IOException;

public class PackageChallengeApplication {
    public static void main(String[] args) throws APIException, IOException {
        args = new String[]{"src/main/test/resources/example_input_invalid_max_weight_of_item"};

        String filePath = args[0];
        String outputString = Packer.pack(filePath);

        System.out.println(outputString);
    }
}
