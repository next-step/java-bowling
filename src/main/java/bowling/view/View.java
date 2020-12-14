package bowling.view;

import bowling.dto.*;

import java.util.Scanner;

public class View {
    private static final Scanner scanner = new Scanner(System.in);

    private View() {}

    public static void printPlayers(PlayersDto playersDto) {
        new PlayersPrintable(playersDto).print();
    }

    public static SizeOfPlayersDto askSizeOfPlayers() {
        new AskSizeOfPlayersPrintable().print();
        return new SizeOfPlayersDto(nextInt());
    }

    public static NameDto askName() {
        new AskNamePrintable().print();
        return new NameDto(nextLine());
    }

    public static PinDto askPin(AskPinDto askPinDto) {
        new AskPinPrintable(askPinDto).print();
        return new PinDto(nextInt());
    }

    private static String nextLine() {
        return scanner.nextLine();
    }

    private static int nextInt() {
        return Integer.parseInt(nextLine());
    }
}
