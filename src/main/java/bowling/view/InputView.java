package bowling.view;

import bowling.frame.Round;

import java.util.Scanner;

public class InputView {

    public static final int ONE_INDEX = 1;

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String inputPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return inputScannerString();
    }

    private String inputScannerString() {
        return scanner.nextLine();
    }

    public int inputShootScore(Round round) {
        System.out.print(round.getRound() + ONE_INDEX + " 프레임 투구 : ");
        return Integer.parseInt(inputScannerString());
    }

}
