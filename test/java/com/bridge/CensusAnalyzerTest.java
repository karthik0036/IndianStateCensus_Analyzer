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

    //IndiaStateCensusData
    private String INDIAN_CENSUS_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    //wrong path
    private String INDIAN_CENSUS_WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    //Wrong Filetype
    private String INDIAN_CENSUS_INCORRECT_FILE_FORMAT = "./src/main/resources/IndiaStateCensusData.txt";
    //Wrong Delimeter
    private String INDIAN_CENSUS_WRONG_DELIMITER = "./src/main/resources/IndiaStateCensusDataWithWrongDelimetre.csv";
    //wrongHeader
    private String INDIAN_CENSUS_WITHWRONG_HEADER = "src/main/resources/IndiaStateCensusDataWithWrongHeader.csv";

    //IndiaStateCodeCsv
    private String INDIAN_STATE_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";
    private String INIDAN_STATE__CODE_WRONGCSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";



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

    // TC1.4
    @Test
    public void givenIndianCensusCSVFile_WhenWrongDelimiter_ShouldThrowException() {

        try {
            CensusAnalyzer censusAnalyser = new CensusAnalyzer();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyzerException.class);
            censusAnalyser.loadIndiaCensusData(INDIAN_CENSUS_WRONG_DELIMITER);
        }
        catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CENSUS_WRONG_DELIMITER, e.type);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // TC1.5
    @Test
    public void givenIndianCensusCSVFile_WhenWrongHeader_ShouldThrowException() {

        try {
            CensusAnalyzer censusAnalyser = new CensusAnalyzer();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyzerException.class);
            censusAnalyser.loadIndiaCensusData(INDIAN_CENSUS_WITHWRONG_HEADER);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.CENSUS_WRONG_DELIMITER_OR_WRONG_HEADER, e.type);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // UC2 -  TC2.1
    @Test
    public void givenIndianStateCodeCSVFile_WhenLoad_ShouldReturnCorrectRecords() throws Exception {
        CensusAnalyzer censusAnalyser = new CensusAnalyzer();
        int count = censusAnalyser.loadIndianStateCodeData(INDIAN_STATE_CSV_FILE_PATH);
        Assert.assertEquals(37, count);
    }

    // TC2.2
    @Test
    public void givenIndianStateCode_WrongCSVFile_WhenLoad_ShouldReturnException() {
        try {
            CensusAnalyzer censusAnalyser = new CensusAnalyzer();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyzerException.class);
            censusAnalyser.loadIndianStateCodeData(INIDAN_STATE__CODE_WRONGCSV_FILE_PATH);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(e.type, CensusAnalyzerException.ExceptionType.CENSUS_FILE_INCORRECT);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
