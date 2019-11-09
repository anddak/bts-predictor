package com.btsbetting.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class ApiCallCountUtil {

    private static final String API_COUNT_FILE_PATH = "C:/IntelliJ Projects/bts-betting/src/main/resources/static/apiCalls.txt";

    private int totalApiCallsMade = 0;
    private int apiCallsMade = 0;
    private LocalDateTime localDateTime = LocalDateTime.now();
    private File file = new File(API_COUNT_FILE_PATH);
    private LocalDateTime fileLastModified;

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiCallCountUtil.class);

    public Integer readFile() {

        String line = "";


        try

                (FileReader fileReader = new FileReader(file);
                 BufferedReader br = new BufferedReader(fileReader)
                ) {
            fileLastModified = Date.from(Instant.ofEpochMilli(file.lastModified())).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            line = br.readLine();

        } catch (IOException ex) {
            LOGGER.error("Issue when reading the api counter file ", ex);
        }

        return Integer.parseInt(line);
    }

    private void updateCallCount(Integer line) {

        if ((localDateTime.getDayOfWeek() == fileLastModified.getDayOfWeek()) && (localDateTime.getMonth() == fileLastModified.getMonth())) {

            totalApiCallsMade = line + apiCallsMade;

        } else {

            totalApiCallsMade = apiCallsMade;

        }

    }


    private void writeFile() {

        try
                (FileWriter fileWriter = new FileWriter(file)) {

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("");
            bufferedWriter.flush();
            bufferedWriter.write(Integer.toString(totalApiCallsMade));
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException e) {
            LOGGER.error("Issue when writing the api counter file ", e);
        }

        LOGGER.info("Number of calls within today's quota quota: {}", totalApiCallsMade);

        totalApiCallsMade = 0;
        apiCallsMade = 0;

    }

    public void countCalls() {
        updateCallCount(readFile());
        writeFile();
    }

    public int getApiCallsMade() {
        return apiCallsMade;
    }

    public void setApiCallsMade(int apiCallsMade) {
        this.apiCallsMade = apiCallsMade;
    }
}
