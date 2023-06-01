package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ImmutableCompoundCommand implements ICompoundCommand {
	private final List<DriverCommand> commands;

	private final String name;

	private ImmutableCompoundCommand(List<DriverCommand> commands, String name) {
		this.commands = Collections.unmodifiableList(new ArrayList<>(commands));
		this.name = name;
	}

	public List<DriverCommand> getCommands() {
		return commands;
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
	public ICompoundCommand createDeepCopy() {
		List<DriverCommand> copySubCommands = new ArrayList<>();

		for (DriverCommand subCommand : commands) {
			copySubCommands.add(subCommand.createDeepCopy());
		}

		return new ImmutableCompoundCommand(copySubCommands, name);
	}

	@Override
	public void accept(ICommandVisitor visitor) {
		ICompoundCommand.super.accept(visitor);
	}

	@Override
	public String toString() {
		return name;
	}

	public static class Builder {
		private final List<DriverCommand> commands;
		private final String name;

		public Builder(String name) {
			this.name = name;
			this.commands = new ArrayList<>();
		}

		public ImmutableCompoundCommand build() {
			return new ImmutableCompoundCommand(commands, name);
		}
		public void addCommands(List<DriverCommand> commands) {
			this.commands.addAll(commands);
		}

		public void addCommand(DriverCommand mockCommand) {
			this.commands.addAll(commands);
		}
	}
}