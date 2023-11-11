package commands;

import interfaces.Command;

public class CommandInvoker {
    private Command command;
    private final CommandHistory history;

    public CommandInvoker(CommandHistory history) {
        this.history = history;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        if (command != null) {
            command.execute();
            history.push(command);
        }
    }
}

