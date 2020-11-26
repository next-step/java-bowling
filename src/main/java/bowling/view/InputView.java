package bowling.view;

import bowling.dto.CountOfPinsDto;
import bowling.dto.PlayerDto;
import bowling.exception.NanException;
import bowling.view.printable.AskCountOfPinsPrintable;
import bowling.view.printable.AskNamePrintable;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {}

    public static PlayerDto askName() {
        new AskNamePrintable().print();
        return new PlayerDto(nextLine());
    }

    public static CountOfPinsDto askCountOfPins(int frameNo) {
        new AskCountOfPinsPrintable(frameNo).print();
        return new CountOfPinsDto(nextInt());
    }


    private static String nextLine() {
        return scanner.nextLine();
    }

    private static int nextInt() {
        String numStr = nextLine();
        try {
            return Integer.parseInt(numStr);
        } catch (Exception e) {
            throw NanException.getInstance();
        }
    }
}
