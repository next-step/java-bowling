package bowling;

import bowling.domain.HitNumber;
import bowling.domain.Player;
import bowling.domain.frame.Frames;

import static bowling.view.InputView.*;
import static bowling.view.ResultView.*;

public class BowlingGame {
    public static void main(String[] args) {
        Player player = Player.of(inputName());
        Frames frames = Frames.of();
        while (!frames.isFinished()) {
            HitNumber hit = HitNumber.of(inputHitNumber(frames.getLastIndex()));
            frames.play(hit);
            showFrames(player, frames);
            showScores(frames.totalScores());
        }
        printEnd();
        close();
    }
}
