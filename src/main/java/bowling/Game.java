package bowling;

import bowling.domain.Frames;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Game {

    public static void main(String[] args) {
        Player player = new Player(InputView.inputNameOfPlayer());
        Frames frames = new Frames();

        OutputView.outputScores(player, frames.results());
        startGame(frames, player);
    }

    private static void startGame(Frames frames, Player player) {

        int frameNumber = 1;
        while (frameNumber <= Frames.TOTAL_FRAME_COUNT) {
            int pitchingCount = InputView.inputPitchingCount(frameNumber);
            frames.throwBalls(pitchingCount);
            frameNumber = frames.nextFrameNumber();
            OutputView.outputScores(player, frames.results());
        }
    }
}
