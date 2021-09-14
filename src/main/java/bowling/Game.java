package bowling;

import bowling.domain.Frames;
import bowling.domain.Player;
import bowling.domain.Score;
import bowling.domain.TotalScore;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static void main(String[] args) {
        Player player = new Player(InputView.inputNameOfPlayer());
        Frames frames = new Frames();

        OutputView.outputScores(player, frames.results());
        OutputView.outputCalculatedScores(new ArrayList<>());

        startGame(frames, player);
    }

    private static void startGame(Frames frames, Player player) {
        List<Integer> calculatedScores = new ArrayList<>();
        TotalScore totalScore = TotalScore.from(frames, calculatedScores);

        int frameNumber = 1;
        while (frameNumber <= Frames.TOTAL_FRAME_COUNT) {
            int pitchingCount = InputView.inputPitchingCount(frameNumber);
            frames.throwBalls(pitchingCount);
            totalScore.addScore(Score.valueOf(pitchingCount));
            frameNumber = frames.nextFrameNumber();
            OutputView.outputScores(player, frames.results());
            calculatedScores = totalScore.getTotalScores(frames, calculatedScores);
            OutputView.outputCalculatedScores(calculatedScores);
        }
    }
}
