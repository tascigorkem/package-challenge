package com.tascigorkem.packer;

import com.tascigorkem.dto.Input;
import com.tascigorkem.dto.PackageBox;
import com.tascigorkem.exception.APIException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.text.MessageFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputTest {


    /**
     * Test FileTranslator.readInputFile method
     *
     * Given valid filepath, should return a input dto
     */
    @Test
    void readInputFile_GivenFile_ShouldReturnInputDto() throws APIException {
        // GIVEN:

        // generates fake input file
        String fileName = "example_input";
        String filePath = "src/test/resources/" + fileName;
        File inFile = new File(filePath);

        // WHEN:
        Input resultInput = FileTranslator.readInputFile(inFile);

        // THEN:
        Input expectedInput = getFakeInput();

        assertEquals(expectedInput.getPackageBoxes().size(), resultInput.getPackageBoxes().size());
        assertEquals(expectedInput, resultInput);
    }

    /**
     * Test FileTranslator.readInputFile method
     *
     * Given valid filepath, should throw APIException
     */
    @Test
    void readInputFile_GivenWrongFilePath_ShouldThrowAPIException() {
        // GIVEN:
        String fileName = "non_exist_file_name";
        String filePath = "src/test/resources/" + fileName;
        File inFile = new File(filePath);

        // WHEN:
        APIException resultException = Assertions.assertThrows(APIException.class,
                () -> FileTranslator.readInputFile(inFile));

        // THEN:
        APIException expectedException = new APIException(MessageFormat.format("File read error: {0}",
                "src/test/resources/non_exist_file_name (No such file or directory)"));
        Assertions.assertEquals(expectedException.getMessage(), resultException.getMessage());
    }

    /**
     * Build and get expected Input dto for example_input file
     *
     * @return expected Input dto
     */
    static Input getFakeInput() {
        List<PackageBox> packageBoxes = PackageBoxGenerator.getFakeInputPackageBoxes();

        return Input.builder()
                .packageBoxes(packageBoxes)
                .build();
    }


}
