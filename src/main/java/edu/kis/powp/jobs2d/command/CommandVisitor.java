package edu.kis.powp.jobs2d.command;

public class CommandVisitor implements ICommandVisitor {

	@Override
	public void visit(ICompoundCommand command) {
		int commandsCount = 0;
		
		while(command.iterator().hasNext()) {
			commandsCount++;
			command.iterator().next();
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
