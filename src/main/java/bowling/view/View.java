package bowling.view;

import java.util.List;
import java.util.Scanner;

public class View {
    private static final int MIN_FRAME_NUMBER = 1;
    private static final int MAX_FRAME_NUMBER = 10;

    private static final Scanner scanner = new Scanner(System.in);

    public static int numberOfPlayers() {
        System.out.print("How many people?");
        int numberOfPlayers = scanner.nextInt();
        scanner.nextLine();
        return numberOfPlayers;
    }

    public static String playerName() {
        System.out.print("플레이어 이름은(3 english letters)?");
        return scanner.nextLine();
    }

    public static int pinfall(int frameNumber) {
        System.out.printf("%d프레임 투구 : ", frameNumber);
        int fallenPin = scanner.nextInt();
        scanner.nextLine();
        return fallenPin;
    }

    public static int pinfall(String playerName) {
        System.out.printf("%s's turn : ", playerName);
        int fallenPin = scanner.nextInt();
        scanner.nextLine();
        return fallenPin;
    }

    public static void printScoreBoardHeader() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("| NAME |");

        for (int i = MIN_FRAME_NUMBER; i <= MAX_FRAME_NUMBER; i++) {
            stringBuilder.append(String.format("  %02d  |", i));
        }
        System.out.println(stringBuilder);
    }

    public static void printBowlingResult(ViewFrameResult viewFrameResult) {
        printFramePinfalls(viewFrameResult);
        printFrameScore(viewFrameResult);
    }

    private static String printPlayerName(String name) {
        return String.format("|  %s |", name);
    }

    private static void printFramePinfalls(ViewFrameResult viewFrameResult) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(printPlayerName(viewFrameResult.playerName()));
        for (int frameNumber = MIN_FRAME_NUMBER; frameNumber <= MAX_FRAME_NUMBER; frameNumber++) {
            List<String> pinfallSymbols = viewFrameResult.symbols(frameNumber);
            stringBuilder.append(String.format("  %-3s |", stringSymbols(pinfallSymbols)));
        }
        System.out.println(stringBuilder);
    }

    private static void printFrameScore(ViewFrameResult viewFrameResult) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("|      |");
        for (int frameNumber = MIN_FRAME_NUMBER; frameNumber <= MAX_FRAME_NUMBER; frameNumber++) {
            String score = viewFrameResult.score(frameNumber);
            stringBuilder.append(String.format("  %-3s |", score));
        }
        System.out.println(stringBuilder);
    }

    private static String stringSymbols(List<String> symbols) {
        return String.join("|", symbols);
    }
}
