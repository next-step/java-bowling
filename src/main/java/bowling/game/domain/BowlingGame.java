package bowling.domain.game.domain;

import bowling.domain.frame.domain.Frames;
import bowling.domain.player.domain.Player;

import java.util.stream.Stream;

public class BowlingGame {

    private final Frames game;

    private BowlingGame(Frames game) {
        this.game = game;
    }

    public static BowlingGame init(String userName) {
        return Stream.of(userName)
                .map(Player::of)
                .map(Frames::init)
                .map(BowlingGame::new)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public Frames getGame() {
        return game;
    }

    public boolean isDone() {
        return game.isDone();
    }

    public void bowl(int felledPin) {
        game.bowl(felledPin);
    }

}
