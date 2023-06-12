//package edu.kis.powp.jobs2d.command;
//
//import java.util.Iterator;
//
//public class CommandVisitorCommander implements CommandVisitor{
//
//    private int operateToCount = 0;
//    private int setPositionCount = 0;
//    private int compoundCommandCount = 0;
//
//    @Override
//    public void visitICompoundCommand(ICompoundCommand compoundCommand) {
//        compoundCommandCount++;
//        Iterator<DriverCommand> iterator = compoundCommand.iterator();
//        while (iterator.hasNext()) {
//            DriverCommand command = iterator.next();
//            command.accept(this);
//        }
//    }
//
//    @Override
//    public void visitOperateToCommand(OperateToCommand operateToCommand) {
//        operateToCount++;
//    }
//
//    @Override
//    public void visitSetPositionCommand(SetPositionCommand setPositionCommand) {
//        setPositionCount++;
//    }
//
//    public int getOperateToCount() {
//        return operateToCount;
//    }
//
//    public int getSetPositionCount() {
//        return setPositionCount;
//    }
//
//    public int getCompoundCommandCount() {
//        return compoundCommandCount;
//    }
//
//}
