package com.mobiquity;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;

public class PackageChallengeApplication {
    public static void main(String[] args) throws APIException {
//        args = new String[]{"src/test/resources/example_input"};

        String filePath = args[0];
        String outputString = Packer.pack(filePath);

        System.out.println(outputString);
    }
}
