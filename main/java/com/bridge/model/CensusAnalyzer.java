package com.bridge.model;

import com.bridge.service.CensusAnalyzerException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyzer {

    public int loadIndiaCensusData(String csvFilePath) throws IOException, CensusAnalyzerException {
        Reader reader = null;
        try {
            if (csvFilePath.contains("txt")) {
                throw new CensusAnalyzerException("File must be in CSV Format", CensusAnalyzerException.ExceptionType.CENSUS_INCORRECT_FILE_FORMAT);
            }
            reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBean<IndiaCensusCSV> csvToBean = new CsvToBeanBuilder<IndiaCensusCSV>(reader)
                    .withType(IndiaCensusCSV.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            // iterator doesn't consume memory
            Iterator<IndiaCensusCSV> iterator = csvToBean.iterator();
            Iterable<IndiaCensusCSV> csvIterable = () -> iterator;
            int count = (int) StreamSupport.stream(csvIterable.spliterator(), true).count();
            return count;
        } catch (RuntimeException e) {
            throw new CensusAnalyzerException("CSV File Must Have Comma As Delimiter Or Has Incorrect Header", CensusAnalyzerException.ExceptionType.CENSUS_WRONG_DELIMITER_OR_WRONG_HEADER);
        }catch (IOException e) {
            throw new CensusAnalyzerException(e.getMessage(), CensusAnalyzerException.ExceptionType.CENSUS_FILE_INCORRECT);
        }

    }
 //IndianStateCodeCsv-Read
    public int loadIndianStateCodeData(String csvFilePath) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
        CsvToBean<IndianStateCodeCSV> csvToBean = new CsvToBeanBuilder<IndianStateCodeCSV>(reader)
                .withType(IndianStateCodeCSV.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        Iterator<IndianStateCodeCSV> iterator = csvToBean.iterator();
        // iterator doesn't consume memory
        Iterable<IndianStateCodeCSV> csvIterable = () -> iterator;
        int count = (int) StreamSupport.stream(csvIterable.spliterator(), true).count();
        return count;
    }

}
