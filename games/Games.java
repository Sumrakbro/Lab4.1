package games;

import characters.Characters;
import exeption.NotEnoughItemsExeption;

import java.util.ArrayList;
import java.util.List;

public abstract class Games {
    protected String title;
    protected ArrayList<Characters> command = new ArrayList<>();
    protected Characters winner;
    protected String rules;

    public Characters getWinner() {
        return winner;
    }

    public void setCommand(characters.Characters... command) {
        for (characters.Characters player : command) {
            this.command.add(player);
        }
    }

    public String getTitle() {
        return title;
    }

    abstract public void start(characters.Characters player) throws InterruptedException, NotEnoughItemsExeption;

    abstract protected void end() throws InterruptedException;

    public List<Characters> getCommand() {
        return command;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Games game = (Games) obj;
        return this.title == game.title;
    }

    @Override
    public String toString() {
        return this.title;
    }

    private interface RulesContainer {
    }

    public RulesContainer getRulesСontainer() {
        class RuleContainer implements RulesContainer {
            final String rules = Games.this.rules;

            @Override
            public String toString() {
                return this.rules;
            }
        }

        RulesContainer rules = new RuleContainer();
        return rules;
    }
}
