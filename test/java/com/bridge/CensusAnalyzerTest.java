package com.bridge;

import com.bridge.model.CensusAnalyzer;
import com.bridge.service.CensusAnalyzerException;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static com.bridge.service.CensusAnalyzerException.ExceptionType.CENSUS_INCORRECT_FILE_FORMAT;

public class CensusAnalyzerTest {
    private String INDIAN_CENSUS_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    //wrong path
    private String INDIAN_CENSUS_WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    //Wrong Filetype
    private String INDIAN_CENSUS_INCORRECT_FILE_FORMAT = "./src/main/resources/IndiaStateCensusData.txt";

    //TC-1.1
    @Test
    public void givenIndianCensusCSVFile_WhenLoad_ShouldReturnCorrectRecords() throws CensusAnalyzerException, IOException {

        CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
        int count = censusAnalyzer.loadIndiaCensusData(INDIAN_CENSUS_CSV_FILE_PATH);
        Assert.assertEquals(29,count);
    }

    // TC1.2
    @Test
    public void givenIndianCensusWrongCSVFile_WhenLoad_ShouldReturnException() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyzerException.class);
            censusAnalyzer.loadIndiaCensusData(INDIAN_CENSUS_WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(e.type, CensusAnalyzerException.ExceptionType.CENSUS_FILE_INCORRECT);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //TC1.3
    @Test
    public void givenIndianCensusCSVFile_WhenCorrectPathButWrongFileFormat_ShouldThrowException() {

        try {
            CensusAnalyzer censusAnalyser = new CensusAnalyzer();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyzerException.class);
            censusAnalyser.loadIndiaCensusData(INDIAN_CENSUS_INCORRECT_FILE_FORMAT);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CENSUS_INCORRECT_FILE_FORMAT, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
