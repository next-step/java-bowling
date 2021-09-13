package bowling;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Player;
import bowling.domain.Scores;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingMain {

    public static void main(String[] args) {
        Player player = new Player(InputView.playerName());
        Frames frames = new Frames();

        while (!frames.isEnd()) {
            Frame currentFrame = frames.current();
            int currentFrameNumber = currentFrame.number();
            currentFrame.bowl(InputView.fallenPins(currentFrameNumber));
            ResultView.header();
            ResultView.frames(player, frames);
            ResultView.scores(Scores.from(frames));
        }
    }

}
