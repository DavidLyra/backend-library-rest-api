package com.library.api.util;

import com.library.api.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class BookCsvExporter {

    public static ByteArrayInputStream booksToCSV(List<Book> books) {
        char delimiter = ';';
        final CSVFormat format = CSVFormat.DEFAULT.withDelimiter(delimiter);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
            for (Book book : books) {
                List<String> data = Arrays.asList(
                        String.valueOf(book.getId()),
                        book.getTitle(),
                        book.getBookFamily().getName()
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            log.error("fail to import data to CSV file: ", e.getMessage());
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}