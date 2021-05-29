package com.tascigorkem;

import com.tascigorkem.exception.APIException;
import com.tascigorkem.packer.Packer;

public class PackageChallengeApplication {
    public static void main(String[] args) throws APIException {
//        args = new String[]{"src/test/resources/example_input"};

        String filePath = args[0];
        String outputString = Packer.pack(filePath);

        System.out.println(outputString);
    }
}
