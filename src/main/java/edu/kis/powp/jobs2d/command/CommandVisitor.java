package edu.kis.powp.jobs2d.command;

import java.util.Iterator;

public class CommandVisitor implements ICommandVisitor {

	@Override
	public void visit(ICompoundCommand command) {
		int commandsCount = 0;

		Iterator<DriverCommand> iter = command.iterator();
		
		while(iter.hasNext()) {
			commandsCount++;
			iter.next().accept(this);
		}
		
		System.out.println("Compound commands count = " + commandsCount);
	}

	@Override
	public void visit(OperateToCommand command) {
		System.out.println("Displaying OperateToCommand");
	}

	@Override
	public void visit(SetPositionCommand command) {
		System.out.println("Displaying SetPositionCommand");
	}
	
}
