package bowling.domain;

public class Game {

    private Table table;

    public Game(String name) {
        this.table = new Table(new User(name));
    }

    public Table getTable() {
        return this.table;
    }

    public boolean hasNext() {
        return table.hasNext();
    }

    public int getNext() {
        return table.getNext();
    }

    public void bowl(int pin) {
        table.bowl(pin);
    }
}
