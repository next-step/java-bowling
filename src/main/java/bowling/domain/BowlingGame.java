package bowling.domain;

public class BowlingGame {
    private final User user;
    
    public BowlingGame(final User user) {
        this.user = user;
    }

    public Frames start() {
        return new Frames(user).create();
    }
}
