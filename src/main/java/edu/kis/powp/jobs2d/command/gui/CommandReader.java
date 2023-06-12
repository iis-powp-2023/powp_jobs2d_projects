package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;

import java.util.List;

public interface CommandReader {
    public ICompoundCommand readCommandFromFile(String filePath);
}
