package edu.kis.powp.deepCopy;

import edu.kis.powp.jobs2d.command.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class DeepCopyTest {
    public static void main(String[] args) {
        LinkedList<DriverCommand> list1 = new LinkedList<>();
        list1.add(new SetPositionCommand(3, 1));
        list1.add(new OperateToCommand(1, 1));

        LinkedList<DriverCommand> list2 = new LinkedList<>();
        list2.add(new OperateToCommand(1, 1));
        list2.add(new SetPositionCommand(15, 1));

        LinkedList<DriverCommand> list3 = new LinkedList<>();
        list3.add(new OperateToCommand(1, 1));
        list3.add(new SetPositionCommand(15, 1));

        DriverCommand driverCommand1 = new CompoundCommand(list1);
        DriverCommand driverCommand2 = new CompoundCommand(list2);
        DriverCommand driverCommand3 = new CompoundCommand(list3);

        ArrayList<DriverCommand> iCompoundCommands = new ArrayList<>();
        iCompoundCommands.add(driverCommand1);
        iCompoundCommands.add(driverCommand2);
        iCompoundCommands.add(driverCommand3);

        CompoundCommand originalCommand = new CompoundCommand(iCompoundCommands);

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
