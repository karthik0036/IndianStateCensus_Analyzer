package com.bridge;

import com.bridge.model.CensusAnalyzer;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CensusAnalyzerTest {
    private String INDIAN_CENSUS_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";

    //TC-1.1
    @Test
    public void givenIndianCensusCSVFile_WhenLoad_ShouldReturnCorrectRecords() throws IOException {

        CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
        int count = censusAnalyzer.loadIndiaCensusData(INDIAN_CENSUS_CSV_FILE_PATH);
        Assert.assertEquals(29,count);
    }
}
