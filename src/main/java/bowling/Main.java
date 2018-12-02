package bowling;

import bowling.domain.PlayBowling;
import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.view.InputView;
import bowling.view.ResultView;

import static bowling.utils.BowlingConstants.ONE;

public class Main {

    public static void main(String[] args) {
        Player player = new Player(InputView.inputPlayer());
        ResultView.drawBasicForm(player);

        PlayBowling playBowling = new PlayBowling(player);

        while(!playBowling.isFinished()) {
            int frameNumber = playBowling.playingFrameNumber();
            ResultView.record(playBowling.playBowling(InputView.inputPitch(frameNumber)), frameNumber);
        }
    }
}
