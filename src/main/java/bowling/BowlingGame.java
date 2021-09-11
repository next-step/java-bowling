package bowling;

import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.stream.IntStream;

import static bowling.CommonConstans.LAST_FRAME_NUMBER;

public class BowlingGame {

    private InputView inputView;
    private ResultView resultView;

    public BowlingGame() {
        this.inputView = new InputView();
        this.resultView = new ResultView();
    }

    public void run() {

        Players players = inputView.players();

        resultView.bowlingBoard(players);

        IntStream.range(0, LAST_FRAME_NUMBER)
                .forEach(frameIndex -> players.forEach(player -> bowlingPlay(frameIndex, players, player)));
    }

    private void bowlingPlay(int frameIndex, Players players, Player player) {
        while (!player.isFrameCompleted(frameIndex)) {
            player.play(inputView.score(player));
            resultView.bowlingBoard(players);
        }
    }
}

