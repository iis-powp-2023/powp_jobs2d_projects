package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ImmutableCompoundCommand implements ICompoundCommand {
	private final List<DriverCommand> commands;

	private ImmutableCompoundCommand(List<DriverCommand> commands) {
		this.commands = Collections.unmodifiableList(new ArrayList<>(commands));
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
	public void accept(ICommandVisitor visitor) {
	}

	public static class Builder {
		private final List<DriverCommand> commands;

		public Builder() {
			this.commands = new ArrayList<>();
		}

		public ImmutableCompoundCommand build() {
			return new ImmutableCompoundCommand(commands);
		}

		public void addCommand(DriverCommand command) {
			commands.add(command);
		}

		public void addCommands(List<DriverCommand> commands) {
			this.commands.addAll(commands);
		}

	};
}