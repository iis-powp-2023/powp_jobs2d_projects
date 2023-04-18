package edu.kis.powp.jobs2d.command;

public interface CommandVisitor {
    void visitICompoundCommand(ICompoundCommand driverCommand);
    void visitOperateToCommand(OperateToCommand operateToCommand);
    void visitSetPositionCommand(SetPositionCommand setPositionCommand);
}
