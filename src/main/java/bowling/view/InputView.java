package bowling.view;

import bowling.dto.CountOfPinsDto;
import bowling.dto.NameDto;
import bowling.dto.SizeOfPlayersDto;
import bowling.view.printable.AskCountOfPinsPrintable;
import bowling.view.printable.AskNamePrintable;
import bowling.view.printable.AskSizeOfPlayers;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {}

    public static SizeOfPlayersDto askSizeOfPlayers() {
        new AskSizeOfPlayers().print();
        return new SizeOfPlayersDto(nextInt());
    }

    public static NameDto askName() {
        new AskNamePrintable().print();
        return new NameDto(nextLine());
    }

    public static CountOfPinsDto askCountOfPins(int frameNo) {
        new AskCountOfPinsPrintable(frameNo).print();
        return new CountOfPinsDto(nextInt());
    }


    private static String nextLine() {
        return scanner.nextLine();
    }

    private static int nextInt() {
        return Integer.parseInt(nextLine());
    }
}
