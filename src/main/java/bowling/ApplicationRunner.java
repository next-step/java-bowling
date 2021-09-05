package bowling;

import bowling.view.BowlingConsoleInputView;

import java.util.Scanner;

public class ApplicationRunner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BowlingConsoleInputView inputView = new BowlingConsoleInputView(scanner);
        String playerText = inputView.enterPlayer();
    }
}
