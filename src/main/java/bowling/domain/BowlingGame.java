package bowling.domain;

public class BowlingGame {

    private Name name;
    private Frames frames;

    public BowlingGame(String name) {
        this.name = new Name(name);
    }
}
