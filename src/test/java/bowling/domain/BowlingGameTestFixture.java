package bowling.domain;

import java.util.Arrays;
import java.util.stream.IntStream;

public class BowlingGameTestFixture {

    private BowlingGameTestFixture() {
    }

    public static BowlingGame createBowlingGame(int... hits) {
        BowlingGame game = new BowlingGame();
        Arrays.stream(hits)
                .forEach(hit -> {
                    game.hit(hit);
                    if (game.getCurrentFrame().isEnded()) {
                        game.moveNextFrame();
                    }
                });
        return game;
    }

    public static BowlingGame createEndedBowlingGame() {
        BowlingGame game = new BowlingGame();
        IntStream.range(0, 12)
                .forEach(i -> {
                    game.hit(10);
                    if (game.getCurrentFrame().isEnded()) {
                        game.moveNextFrame();
                    }
                });
        return game;
    }

}
