package edu.kis.powp.jobs2d.command.gui;

public interface CommandReader {
    CommandReaderManager readCommandFromFile(String command, String name);
}
