package edu.kis.powp.deepCopy;

import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;

import java.util.ArrayList;

public class DeepCopy {
    public static void main(String[] args) {
        CompoundCommand originalCommand = new CompoundCommand(
                new ArrayList<>()
        );

        originalCommand.commands.add(driver -> System.out.println("Only In Original"));

        ICompoundCommand copiedCommand = originalCommand.deepCopy();

        originalCommand.commands.clear();
        assert copiedCommand.iterator().hasNext();

    }
}
