package bowling.view;

import bowling.dto.*;

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

    public static PlayerDto askPlayer() {
        new AskPlayerPrintable().print();
        return new PlayerDto(nextLine());
    }

    public static RollDto askRoll(FrameNoDto frameNoDto) {
        new AskRollPrintable(frameNoDto).print();
        return new RollDto(nextInt());
    }


    private static String nextLine() {
        return scanner.nextLine();
    }

    private static int nextInt() {
        return Integer.parseInt(nextLine());
    }
}
