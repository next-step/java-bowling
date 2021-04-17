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
    }

    private static void printBoard(int round, String playerName, BowlingBoardDto bowlingBoardDto) {
        List<ScoreDto> scoreDtoList = bowlingBoardDto.getScoreDtoList();
        String point = IntStream.range(0, round)
                .mapToObj(i -> printScore(scoreDtoList.get(i), bowlingBoardDto.getThrowsState(), round, i))
                .collect(Collectors.joining());

        System.out.print("|  " + playerName + " ");
        System.out.print(point);
        for (int i = round; i < FINAL_ROUND; i++) {
            System.out.print("|      ");
        }
        System.out.println("|");
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
