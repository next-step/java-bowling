package bowling;

import bowling.view.InputView;
import bowling.view.OutputView;
import org.hibernate.type.TrueFalseType;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Username username = InputView.playerName();

        BowlingScoreRecord bowlingScoreRecord = new BowlingScoreRecord();

        int frameNumber = 0;
        while (frameNumber < 10) {
            int count = InputView.hitCount(frameNumber + 1);
            frameNumber = bowlingScoreRecord.hit(frameNumber, count);
            List<Frame> frames = bowlingScoreRecord.getFrames();
            OutputView.printScoreScreen(username, frames);
        }

    }
}
