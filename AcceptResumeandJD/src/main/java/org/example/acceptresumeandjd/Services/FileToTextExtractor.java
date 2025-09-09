package org.example.acceptresumeandjd.Services;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class FileToTextExtractor {

    private static final Tika tika = new Tika();

    public String extractText(byte[] fileContent) {
        try {
            return tika.parseToString(new ByteArrayInputStream(fileContent));
        } catch (TikaException | IOException e) {
            throw new RuntimeException(e);

        }
    }
}
