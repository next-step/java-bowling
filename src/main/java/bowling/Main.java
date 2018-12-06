package bowling;

import bowling.domain.Pin;
import bowling.domain.PlayBowling;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Main {

    public static void main(String[] args) {
        Player player = new Player(InputView.inputPlayer());
        ResultView.drawBasicForm(player);

        PlayBowling playBowling = new PlayBowling(player);

        while(!playBowling.isFinished()) {
            int frameNumber = playBowling.playingFrameNumber();
            ResultView.record(playBowling.playBowling(Pin.getInstance(InputView.inputPitch(frameNumber))), frameNumber);
            ResultView.score(playBowling.scores());
        }
    }
}
