package com.mobiquity.packer;

import com.mobiquity.dto.Input;
import com.mobiquity.dto.Output;
import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.File;

class PackerTest {

    /**
     * Test Packer.pack method
     *
     * Generates input dto according to example_input file and
     * mocks FileTranslator.readInputFile method for return this input dto.
     *
     * Generates expected output dto according to example_input file
     *
     * Checks whether expected output dto equals to result of Packer.pack method's output dto
     *
     * @throws APIException is thrown if validation fails
     */
    @Test
    void pack_GivenValidFile_ShouldReturnExpectedOutput() throws APIException {
        // GIVEN:

        // generates fake input file
        String fileName = "example_input";
        String filePath = "src/test/resources/" + fileName;
        File inFile = new File(filePath);
        Input fakeInput = InputTest.getFakeInput();

        // mocks FileTranslator.readInputFile method for return fakeInput
        try (MockedStatic<FileTranslator> fileTranslator =
                     Mockito.mockStatic(FileTranslator.class)){

            fileTranslator.when(() -> FileTranslator.readInputFile(inFile))
                    .thenReturn(fakeInput);
        }

        // generates expected output dto according to example_input file
        Output expectedOutput = OutputTest.getFakeOutput();
        String expectedOutputString = expectedOutput.toString();

        // WHEN:
        String resultOutputString = Packer.pack(filePath);

        // THEN:
        Assertions.assertEquals(expectedOutputString, resultOutputString);
    }
}
