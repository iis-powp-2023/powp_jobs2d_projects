package edu.kis.powp.jobs2d.command.commandsFromFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFromTxtFile implements IFileReader{

    @Override
    public String readFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            Stream<String> stringStream = br.lines();
            return stringStream
                    .map(String::toLowerCase)
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
