package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.ImmutableCompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;

import org.mockito.Mock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

public class ImmutableCompoundCommandTest {

    @Mock
    private DriverCommand mockCommand;

    /**
     * Test whether the constructor of ImmutableCompoundCommand creates an object
     * with the expected properties.
     */
    @Test
    public void testImmutableCompoundCommandCreation() {
        List<DriverCommand> commands = new ArrayList<>();
        commands.add(mockCommand);
        commands.add(mockCommand);

        ImmutableCompoundCommand.Builder builder = new ImmutableCompoundCommand.Builder("test");
        builder.addCommand(mockCommand);
        builder.addCommand(mockCommand);
        ImmutableCompoundCommand compoundCommand = builder.build();

        assertEquals(commands, compoundCommand.getCommands());
    }

    /**
     * Test whether an attempt to add a new operation to an ImmutableCompoundCommand
     * object after it has been created
     * results in an UnsupportedOperationException.
     */
    @Test
    public void testImmutableCompoundCommandImmutable() {
        List<DriverCommand> commands = new ArrayList<>();
        commands.add(mockCommand);
        ImmutableCompoundCommand.Builder builder = new ImmutableCompoundCommand.Builder("test");
        builder.addCommand(mockCommand);
        ImmutableCompoundCommand compoundCommand = builder.build();

        assertThrows(UnsupportedOperationException.class, () -> compoundCommand.getCommands().add(mockCommand));
    }

    /**
     * Test whether adding an empty list of commands to an ImmutableCompoundCommand
     * object results in an object is null.
     */
    @Test
    public void testImmutableCompoundCommandAddingEmptyList() {
        List<DriverCommand> commands = new ArrayList<>();
        ImmutableCompoundCommand.Builder builder = new ImmutableCompoundCommand.Builder("test");
        builder.addCommands(commands);
        ImmutableCompoundCommand compoundCommand = builder.build();

        assertEquals(0, compoundCommand.getCommands().size());
    }

    /**
     * Test whether the iterator() method of ImmutableCompoundCommand works as
     * expected
     */
    @Test
    public void testImmutableCompoundCommandIterator() {
        List<DriverCommand> commands = new ArrayList<>();
        commands.add(mockCommand);

        ImmutableCompoundCommand.Builder builder = new ImmutableCompoundCommand.Builder("test");
        builder.addCommand(mockCommand);
        ImmutableCompoundCommand compoundCommand = builder.build();

        List<DriverCommand> resultCommands = new ArrayList<>();
        compoundCommand.iterator().forEachRemaining(resultCommands::add);

        assertEquals(commands, resultCommands);
    }

    /**
     * Test whether the addCommand() method of ImmutableCompoundCommand works as
     * expected
     */
    @Test
    public void testImmutableCompoundCommandAddCommand() {
        List<DriverCommand> commands = new ArrayList<>();
        commands.add(mockCommand);

        ImmutableCompoundCommand.Builder builder = new ImmutableCompoundCommand.Builder("test");
        builder.addCommand(mockCommand);
        ImmutableCompoundCommand compoundCommand = builder.build();

        DriverCommand newCommand = mock(DriverCommand.class);

        builder.addCommand(newCommand);
        ImmutableCompoundCommand newCompoundCommand = builder.build();

        assertEquals(commands.size(), 1);
        assertEquals(newCompoundCommand.getCommands().size(), 2);
        assertEquals(newCompoundCommand.getCommands().get(0), mockCommand);
        assertEquals(newCompoundCommand.getCommands().get(1), newCommand);
    }

}
