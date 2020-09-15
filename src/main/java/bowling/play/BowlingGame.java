package bowling.play;

import bowling.domain.Player;
import bowling.domain.Frame;
import bowling.domain.Frames;

import java.util.function.Consumer;
import java.util.function.Function;

public class BowlingGame {

    private Frames frames;

    public BowlingGame(Player player) {
        frames = new Frames(player);
    }

    public void round(Function<String, String> rollingBall, Consumer<Frames> showResult) {

        while (!frames.allFrameEnd()) {
            int countOfPins = Integer.parseInt(rollingBall.apply(frames.currentFrame()));
            frames.play(countOfPins);

            showResult.accept(frames);
        }
    }
}
