package edu.kis.powp.deepCopy;

import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DeepCopyTest {
    public static void main(String[] args) {


        DriverCommand driverCommand1 = new CompoundCommand(new LinkedList<>());
        DriverCommand driverCommand2 = new CompoundCommand(new LinkedList<>());
        DriverCommand driverCommand3 = new CompoundCommand(new LinkedList<>());

        CompoundCommand originalCommand = new CompoundCommand(
                new ArrayList<>(List.of(
                        driverCommand1,
                        driverCommand2,
                        driverCommand3
                ))
        );

        ICompoundCommand copiedCommand = originalCommand.createDeepCopy();

        Iterator<DriverCommand> originalIterator = originalCommand.iterator();
        Iterator<DriverCommand> copiedIterator = copiedCommand.iterator();

        Object originalElement;
        Object copiedElement;

        while (originalIterator.hasNext() && copiedIterator.hasNext()) {
            originalElement = originalIterator.next();
            copiedElement = copiedIterator.next();

            assert !originalElement.equals(copiedElement);
        }
    }
}
