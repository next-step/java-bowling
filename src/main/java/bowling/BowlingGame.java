package bowling;

import bowling.domain.HitNumber;
import bowling.domain.Name;
import bowling.domain.frame.Frames;

import static bowling.view.InputView.*;
import static bowling.view.ResultView.*;

public class BowlingGame {
    public static void main(String[] args) {
        Name player = Name.of(inputName());
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
