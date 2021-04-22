package bowling;

import bowling.controller.BowlingController;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.Scanner;

public class BowlingApplication {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputView(scanner);
        ResultView resultView = new ResultView();
        BowlingController bowlingController = new BowlingController(inputView, resultView);
        bowlingController.run();

        scanner.close();
    }
}
