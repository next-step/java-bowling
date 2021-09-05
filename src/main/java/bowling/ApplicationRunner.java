package bowling;

import bowling.view.BowlingConsoleInputView;
import bowling.view.BowlingConsoleOutputView;

import java.util.Scanner;

public class ApplicationRunner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BowlingConsoleInputView inputView = new BowlingConsoleInputView(scanner);
        BowlingConsoleOutputView outputView = new BowlingConsoleOutputView();

        String playerText = inputView.enterPlayer();

        for (int i = 1; i <= 10; i++) {
            String scoreText = inputView.enterScore(i);
            outputView.print(playerText);
        }
    }
}
