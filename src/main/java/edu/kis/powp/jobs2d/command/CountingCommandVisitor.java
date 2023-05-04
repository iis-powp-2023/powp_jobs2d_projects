package edu.kis.powp.jobs2d.command;

import java.util.Iterator;

public class CountingCommandVisitor implements ICommandVisitor {
	
	private int compoundCommandsCount;
	private int operateToCommandsCount;
	private int setPositionCommandsCount;

	public int getCompoundCommandsCount() {
		return this.compoundCommandsCount;
	}
	
	public int getOperateToCommandsCount() {
		return this.operateToCommandsCount;
	}

	public int getSetPositionCommandsCount() {
		return this.setPositionCommandsCount;
	}
	
	public CountingCommandVisitor() {
		this.compoundCommandsCount = 0;
		this.operateToCommandsCount = 0;
		this.setPositionCommandsCount = 0;
	}
	
	@Override
	public void visit(ICompoundCommand command) {
		this.operateToCommandsCount = 0;
		this.setPositionCommandsCount = 0;
		
		Iterator<DriverCommand> iter = command.iterator();
		
		while(iter.hasNext()) {
			iter.next().accept(this);
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
