package edu.kis.powp.jobs2d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;

public class CompoundCommandDeepCopyTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        List<DriverCommand> list = new ArrayList<DriverCommand>();
        list.add(new SetPositionCommand(0, 0));
        list.add(new OperateToCommand(123, 123));

        ICompoundCommand cmd = new ICompoundCommand() {
            List<DriverCommand> driverCommands = list;
            @Override
            public List<DriverCommand> getDriverCommands() {
                return driverCommands;
            }

            @Override
            public void execute(Job2dDriver driver) {
                driverCommands.forEach((c) -> c.execute(driver));
            }

            @Override
            public Iterator<DriverCommand> iterator() {
                return driverCommands.iterator();
            }

            @Override
            public void setDriverCommands(List<DriverCommand> driver) {
                this.driverCommands = driver;
            }

            @Override
            public Object clone() throws CloneNotSupportedException {
                ICompoundCommand cpy = (ICompoundCommand) super.clone();
                cpy.setDriverCommands(List.copyOf(driverCommands));
                return cpy;
            }

        };

        ICompoundCommand cmd2 = (ICompoundCommand) cmd.clone();
        System.out.println(cmd2 == cmd);
        cmd.getDriverCommands().remove(1);
        System.out.println(cmd.getDriverCommands());
        System.out.println(cmd2.getDriverCommands());

    }
}
