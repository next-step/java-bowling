package bowling.view;

import bowling.domain.BowlingRole;
import bowling.domain.ThrowsState;
import bowling.dto.BowlingBoardDto;
import bowling.dto.PlayerDto;
import bowling.dto.ScoreDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {
    private static final int FINAL_ROUND = 10;
    private static final int FIRST_ROUND = 1;

    public static void printResultView(int round, PlayerDto player, BowlingBoardDto bowlingBoardDto) {
        printRound();
        printBoard(round, player.getPlayerName(), bowlingBoardDto);
        printPoint(round, bowlingBoardDto);
    }

    private static void printPoint(int round, BowlingBoardDto bowlingBoardDto) {
        List<ScoreDto> scoreDtoList = bowlingBoardDto.getScoreDtoList();
        List<Integer> framePoints = bowlingBoardDto.getFramePoint();
        ThrowsState throwsState = bowlingBoardDto.getThrowsState();

        List<String> point = IntStream.range(0, round)
                .mapToObj(i -> framePoints.get(i) > 10 ? String.format("|  %d  ", framePoints.get(i)) : String.format("|  %d   ", framePoints.get(i)))
                .collect(Collectors.toList());

        for (int i = 0; i < round; i++) {
            makeEmptyScore(round, scoreDtoList, throwsState, point);
        }

        System.out.print("|      ");
        System.out.print(point.stream().collect(Collectors.joining()));
        unusedGames(round);
    }

    private static void makeEmptyScore(int round, List<ScoreDto> scoreDtoList, ThrowsState throwsState, List<String> point) {
        if (scoreDtoList.get(round - 1).getScoreType() == BowlingRole.STRIKE && throwsState != ThrowsState.BONUS_THROWS) {
            point.set(round - 1, "|      ");
        }
        if (scoreDtoList.get(round - 1).getScoreType() == BowlingRole.SPARE && throwsState != ThrowsState.BONUS_THROWS) {
            point.set(round - 1, "|      ");
        }
        if (round != 1 && scoreDtoList.get(round - 2).getScoreType() == BowlingRole.STRIKE && throwsState == ThrowsState.SECOND_THROWS) {
            point.set(round - 2, "|      ");
            point.set(round - 1, "|      ");
        }
    }

    private static void unusedGames(int round) {
        for (int i = round; i < FINAL_ROUND; i++) {
            System.out.print("|      ");
        }
        System.out.println("|");
    }

    private static void printBoard(int round, String playerName, BowlingBoardDto bowlingBoardDto) {
        List<ScoreDto> scoreDtoList = bowlingBoardDto.getScoreDtoList();
        String point = IntStream.range(0, round)
                .mapToObj(i -> printScore(scoreDtoList.get(i), bowlingBoardDto.getThrowsState(), round, i))
                .collect(Collectors.joining());

        System.out.print("|  " + playerName + " ");
        System.out.print(point);
        unusedGames(round);
    }

    private static String printScore(ScoreDto scoreDto, ThrowsState throwsState, int size, int round) {
        String first = bowlingSymbol(scoreDto.getBowlingPoint().get(0));
        String second = bowlingSymbol(scoreDto.getBowlingPoint().get(1));
        if (throwsState == ThrowsState.SECOND_THROWS && size == (round + 1)) {
            return String.format("|  %s   ", first);
        }
        if (scoreDto.getScoreType() == BowlingRole.STRIKE) {
            return "|  X   ";
        }
        if (scoreDto.getScoreType() == BowlingRole.SPARE) {
            return String.format("|  %s|/ ", first);
        }

        return String.format("|  %s|%s ", first, second);
    }

    private static String bowlingSymbol(int point) {
        if (point == 0) {
            return "-";
        }
        return String.format("%d", point);
    }


    private static void printRound() {
        String name = "| NAME |";
        String round = IntStream.range(FIRST_ROUND, FINAL_ROUND).
                mapToObj(i -> "  0" + i + "  ").collect(Collectors.joining("|"));
        System.out.println(name + round + "|  10  |");
    }

    public static void printResultView(PlayerDto playerDto) {
        printResultView(0, playerDto, BowlingBoardDto.of());
    }
}
