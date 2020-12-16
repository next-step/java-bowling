package bowling.domain;

public class BowlingGameMockFactory {
    public static BowlingGame create(final String username) {
        return BowlingGame.of(User.of(username));
    }
}
