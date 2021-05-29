package com.tascigorkem.packer;

import com.tascigorkem.dto.Output;
import com.tascigorkem.dto.PackageBox;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class OutputTest {

    /**
     * Test Output.toString method
     */
    @Test
    void toString_GetStringOfOutput_ShouldReturnString () {
        // GIVEN:
        Output output = getFakeOutput();

        // WHEN:
        String resultOutputString = output.toString();

        // THEN:
        String expectedOutputString = "4\n" +
                "-\n" +
                "2,7\n" +
                "8,9\n";

        Assertions.assertEquals(expectedOutputString, resultOutputString);
    }

    @Test
    void constructor_GivenPackageBoxes_ShouldReturnOutput() {
        // GIVEN:
        List<PackageBox> fakeOutputPackageBoxes = PackageBoxGenerator.getFakeOutputPackageBoxes();

        // WHEN:
        Output resultOutput = new Output(fakeOutputPackageBoxes);

        // THEN:
        Output expectedOutput = getFakeOutput();
        Assertions.assertEquals(expectedOutput, resultOutput);
    }



    /**
     * Build and get expected Output dto for example_input file
     *
     * @return expected Output dto
     */
    static Output getFakeOutput() {
        List<PackageBox> packageBoxes = PackageBoxGenerator.getFakeOutputPackageBoxes();

        return Output.builder()
                .packageBoxes(packageBoxes)
                .build();
    }


}
