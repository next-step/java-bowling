package bowling.view;

import bowling.dto.PlayerDto;
import bowling.dto.RollDto;
import bowling.view.printable.AskPlayerPrintable;
import bowling.view.printable.AskRollPrintable;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {}

    public static PlayerDto askPlayer() {
        new AskPlayerPrintable().print();
        return new PlayerDto(nextLine());
    }

    public static RollDto askRoll(int frameNo) {
        new AskRollPrintable(frameNo).print();
        return new RollDto(nextInt());
    }


    private static String nextLine() {
        return scanner.nextLine();
    }

    private static int nextInt() {
        return Integer.parseInt(nextLine());
    }
}
