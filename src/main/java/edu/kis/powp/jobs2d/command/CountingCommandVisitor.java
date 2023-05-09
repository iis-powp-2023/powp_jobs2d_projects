package edu.kis.powp.jobs2d.command;

import java.util.Iterator;

public class CountingCommandVisitor implements ICommandVisitor {
	
	private int compoundCommandsCount = 0;
	private int operateToCommandsCount = 0;
	private int setPositionCommandsCount = 0;

	public int getCompoundCommandsCount() {
		return this.compoundCommandsCount;
	}
	
	public int getOperateToCommandsCount() {
		return this.operateToCommandsCount;
	}

	public int getSetPositionCommandsCount() {
		return this.setPositionCommandsCount;
	}
	
	public CountingCommandVisitor() {}
	
	@Override
	public void visit(ICompoundCommand command) {
		Iterator<DriverCommand> iterator = command.iterator();
		
		while(iterator.hasNext()) {
			iterator.next().accept(this);
		}
		
		this.compoundCommandsCount = this.operateToCommandsCount + this.setPositionCommandsCount;
	}

	@Override
	public void visit(OperateToCommand command) {
		this.operateToCommandsCount++;
	}

	@Override
	public void visit(SetPositionCommand command) {
		this.setPositionCommandsCount++;
	}
}
