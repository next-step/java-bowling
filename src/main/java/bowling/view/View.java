package bowling.view;

import bowling.domain.*;

import java.util.Scanner;
import java.util.stream.Collectors;

public class View {
    private static final Scanner scanner = new Scanner(System.in);

    public static String playerName() {
        System.out.print("플레이어 이름은(3 english letters)?:");
        return scanner.nextLine();
    }

    public static int pinfall(int frameNumber) {
        System.out.printf("%d프레임 투구 : ", frameNumber);
        int fallenPin = scanner.nextInt();
        scanner.nextLine();
        return fallenPin;
    }

    public static void printBowlingResult(BowlingResult bowlingResult) {
        printFramePinfalls(bowlingResult);
        printFrameScore(bowlingResult);
    }

    private static void printFrameScore(BowlingResult bowlingResult) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("|      |");
        for (SingleFrameResult singleFrameResult : bowlingResult.results()) {
            stringBuilder.append(printFrameScore(singleFrameResult));
        }
        System.out.println(stringBuilder);
    }

    private static void printFramePinfalls(BowlingResult bowlingResult) {
        StringBuilder stringBuilder = new StringBuilder();
        for (SingleFrameResult singleFrameResult : bowlingResult.results()) {
            stringBuilder.append(printFrameResult(singleFrameResult));
        }
        System.out.println(stringBuilder);
    }

    private static StringBuilder printFrameScore(SingleFrameResult singleFrameResult) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("  %-3s |", singleFrameResult.aggregatedScore().toString()));
        return stringBuilder;
    }

    private static StringBuilder printFrameResult(SingleFrameResult singleFrameResult) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("  %-3s |", pointSymbolsString(singleFrameResult.pointSymbols())));
        return stringBuilder;
    }

    private static String pointSymbolsString(PointSymbols pointSymbols) {
        return pointSymbols.symbols().stream()
                .map(PointSymbol::symbol)
                .map(Object::toString)
                .collect(Collectors.joining("|"));
    }

    public static void printScoreBoardHeader() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("| NAME |");

        for (int i = 1; i <= 10; i++) {
            stringBuilder.append(String.format("  %02d  |", i));
        }
        System.out.println(stringBuilder);
    }

    public static void printPlayer(Player player) {
        System.out.printf("|  %s |", player.name());
    }
}
