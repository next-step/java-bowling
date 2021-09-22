package bowling;

import bowling.domain.Bowling;
import bowling.view.BowlingConsoleInputView;
import bowling.view.BowlingConsoleOutputView;

import java.util.Scanner;

public class ApplicationRunner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BowlingConsoleInputView inputView = new BowlingConsoleInputView(scanner);
        BowlingConsoleOutputView outputView = new BowlingConsoleOutputView();

        String playerText = inputView.enterPlayer();

        Bowling bowling = new Bowling(playerText);

        while (!bowling.isFinish()) {
            int index = bowling.lastFinishedFrameIndex();
            String scoreText = inputView.enterScore(index + 1);
            bowling.roll(scoreText);
            outputView.print(bowling);
        }
    }
}
