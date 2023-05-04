package edu.kis.powp.deepCopy;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeepCopyTest {
    public static void main(String[] args) {


        DriverCommand driverCommand1 = new DriverCommand() {
            @Override
            public void execute(Job2dDriver driver) {
                System.out.println("Shouldn't appear in deep copy");
            }

            @Override
            public DriverCommand createDeepCopy() {
                return null;
            }
        };

        DriverCommand driverCommand2 = new DriverCommand() {
            @Override
            public void execute(Job2dDriver driver) {
                System.out.println("Shouldn't appear in deep copy");
            }

            @Override
            public DriverCommand createDeepCopy() {
                return null;
            }
        };


        DriverCommand driverCommand3 = new DriverCommand() {
            @Override
            public void execute(Job2dDriver driver) {
                System.out.println("Shouldn't appear in deep copy");
            }

            @Override
            public DriverCommand createDeepCopy() {
                return null;
            }
        };


        DriverCommand driverCommand4 = new DriverCommand() {
            @Override
            public void execute(Job2dDriver driver) {
                System.out.println("Shouldn't appear in deep copy");
            }

            @Override
            public DriverCommand createDeepCopy() {
                return null;
            }
        };

        CompoundCommand originalCommand = new CompoundCommand(
                new ArrayList<>(List.of(
                        driverCommand1,
                        driverCommand2,
                        driverCommand3,
                        driverCommand4
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
