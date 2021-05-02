package bowling.view;

import bowling.domain.*;

import java.util.List;
import java.util.Scanner;

public class View {
    private static final Scanner scanner = new Scanner(System.in);

    public static String playerName() {
        System.out.print("플레이어 이름은(3 english letters)?:");
        return scanner.nextLine();
    }

    public static int pinfall(FrameNumber frameNumber) {
        System.out.printf("%d프레임 투구 : ", frameNumber.number());
        int fallenPin = scanner.nextInt();
        scanner.nextLine();
        return fallenPin;
    }

    public static void printBowlingResult(BowlingResult bowlingResult) {
        for (FrameResult frameResult : bowlingResult.results()) {
            printFrameResult(frameResult);
        }
        System.out.println();

        System.out.print("|      |");
        for (FrameResult frameResult : bowlingResult.results()) {
            printFrameScore(frameResult);
        }
        System.out.println();
    }

    private static void printFrameScore(FrameResult frameResult) {
        System.out.printf("  %-3s |", frameResult.aggregatedScore().toString());
    }

    private static void printFrameResult(FrameResult frameResult) {
        System.out.printf("  %-3s |", pointSymbolsString(frameResult.pointSymbols()));
    }

    private static String pointSymbolsString(PointSymbols pointSymbols) {
        int index = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (PointSymbol pointSymbol : pointSymbols.symbols()) {
            stringBuilder.append(pointSymbol.symbol());
            if (++index != pointSymbols.length()) {
                stringBuilder.append('|');
            }
        }
        return stringBuilder.toString();
    }

    public static void printScoreBoardHeader() {
        System.out.print("| NAME |");
        for (int i = 1; i <= 10; i++) {
            System.out.printf("  %02d  |", i);
        }
        System.out.println();
    }

    public static void printPlayer(Player player) {
        System.out.printf("|  %s |", player.name());
    }
}

