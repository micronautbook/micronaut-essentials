package example.micronaut;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Named("csv")
@Singleton
public class CsvContactParser implements ContactParser {
    private static final Logger LOG = LoggerFactory.getLogger(CsvContactParser.class);
    @Override
    public Optional<Contact> parse(InputStream inputStream) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String[] line = reader.readNext();
            return Optional.of(new Contact(line[0], line[1], line[2]));
        } catch (IOException | CsvException e) {
            LOG.error("Error parsing CSV file", e);
        }
        return Optional.empty();
    }
}
