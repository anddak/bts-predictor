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

    private int totalApiCallsMade = 0;
    public static int apiCallsMade = 0;
    LocalDateTime localDateTime = LocalDateTime.now();
    private File file = new File("C:/IntelliJ Projects/bts-betting/src/main/resources/static/apiCalls.txt");
    private LocalDateTime fileLastModified;

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiCallCountUtil.class);

    public Integer readFile() {

        String line = null;

        try {

            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);

            fileLastModified = Date.from(Instant.ofEpochMilli(file.lastModified())).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            line = br.readLine();

            fileReader.close();
            br.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return Integer.parseInt(line);
    }

    public void updateCallCount(Integer line) {

        if ((localDateTime.getDayOfWeek() == fileLastModified.getDayOfWeek()) && (localDateTime.getMonth() == fileLastModified.getMonth())) {

            totalApiCallsMade = line + apiCallsMade;

        } else {

            totalApiCallsMade = apiCallsMade;

        }

    }


    public void writeFile() {

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("");
        bufferedWriter.flush();
        bufferedWriter.write(Integer.toString(totalApiCallsMade));
        bufferedWriter.flush();
        bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.info("Number of calls within the last quota: " + totalApiCallsMade);

        totalApiCallsMade = 0;
        apiCallsMade = 0;

    }

    public void countCalls() {
        updateCallCount(readFile());
        writeFile();
    }

}
