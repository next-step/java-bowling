package bowling.view;

import bowling.dto.CountOfPinsDto;
import bowling.dto.GameDto;
import bowling.dto.NameDto;
import bowling.dto.SizeOfPlayersDto;

import java.util.Scanner;

public class View {
    private static final Scanner scanner = new Scanner(System.in);

    private View() {}

    public static void printGame(GameDto gameDto) {
        new GamePrintable(gameDto).print();
    }

    public static SizeOfPlayersDto askSizeOfPlayers() {
        new AskSizeOfPlayersPrintable().print();
        return new SizeOfPlayersDto(nextInt());
    }

    public static NameDto askName() {
        new AskNamePrintable().print();
        return new NameDto(nextLine());
    }

    public static CountOfPinsDto askCountOfPins(String prefix) {
        new AskCountOfPinsPrintable(prefix).print();
        return new CountOfPinsDto(nextInt());
    }


    private static String nextLine() {
        return scanner.nextLine();
    }

    private static int nextInt() {
        return Integer.parseInt(nextLine());
    }
}
