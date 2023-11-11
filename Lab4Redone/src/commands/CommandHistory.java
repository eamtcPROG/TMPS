package commands;
import interfaces.Command;

import java.util.ArrayList;

public class CommandHistory {
    private final ArrayList<Command> history;

    public CommandHistory(){
        this.history = new ArrayList<>();
    }

    public void push(Command command) {
        history.add(command);
    }

    public void displayHistory() {
        for (int i = 0; i < history.size(); i++) {
            System.out.println((i + 1) + ". " + history.get(i).getClass().getSimpleName());
        }
    }
}
