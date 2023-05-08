package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ImmutableCompoundCommand implements ICompoundCommand {
	private final List<DriverCommand> commands;

	public ImmutableCompoundCommand(List<DriverCommand> commands) {
		this.commands = Collections.unmodifiableList(new ArrayList<>(commands));
	}

	public List<DriverCommand> getCommands() {
		return commands;
	}	

	public ICompoundCommand addCommandToExistingCommandsList(DriverCommand command) {
		List<DriverCommand> newCommands = new ArrayList<>(commands);
		newCommands.add(command);
		return new ImmutableCompoundCommand(newCommands);
	}

	public ICompoundCommand addCommandsToExistingCommandsList(List<DriverCommand> commands) {
		List<DriverCommand> newCommands = new ArrayList<>(this.commands);
		newCommands.addAll(commands);
		return new ImmutableCompoundCommand(newCommands);
	}

	@Override
	public void execute(Job2dDriver driver) {
		commands.forEach(command -> command.execute(driver));
	}

	@Override
	public Iterator<DriverCommand> iterator() {
		return commands.iterator();
	}

	@Override
	public void accept(ICommandVisitor visitor) {}
}