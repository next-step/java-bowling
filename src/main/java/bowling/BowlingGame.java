package bowling;

import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static bowling.CommonConstans.LAST_FRAME_NUMBER;
import static bowling.CommonConstans.ZERO;

public class BowlingGame {

    private List<Frames> frames = new ArrayList<>();

    public void run() {

        Players players = InputView.players();

        ResultView.bowlingBoard(players, Frames.init());

        IntStream.range(ZERO, players.countOfPlayers())
                .forEach(countOfFrames -> frames.add(Frames.init()));

        IntStream.range(0, LAST_FRAME_NUMBER)
                .forEach(frameIndex -> players.forEach(player -> bowlingPlay(frameIndex, players, player)));
    }

    private void bowlingPlay(int frameIndex, Players players, Player player) {

        int playerIndex = players.index(player);
        while (!frames.get(playerIndex).isFrameCompleted(frameIndex)) {
            frames.get(playerIndex).play(InputView.score(player, frames.get(playerIndex)));
            ResultView.bowlingBoard(players, frames.get(playerIndex));
        }
    }
}

