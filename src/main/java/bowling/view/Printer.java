package bowling.view;

import bowling.domain.Bowler;
import bowling.domain.Frame;
import bowling.domain.GameResults;

import java.util.stream.Collectors;

public class Printer {
    private static final String STAGE_PREFIX_STRING = "| NAME |";
    private static final String STAGE_FORMAT = "  %02d  |";
    private static final String BOWLER_NAME_FORMAT = "| %4s |";
    private static final String GAME_RESULT_FORMAT = "  %-3s |";
    private static final String GAME_RESULT_DELIMITER = "|";

    public static void showResult(Bowler bowler) {
        showHead(bowler);
        showBody(bowler);
        System.out.println();
    }

    private static void showHead(Bowler bowler) {
        System.out.print(STAGE_PREFIX_STRING);
        for (Frame frame : bowler.getFrames()) {
            System.out.print(String.format(STAGE_FORMAT, frame.getStage()));
        }
        System.out.println();
    }

    private static void showBody(Bowler bowler) {
        System.out.print(String.format(BOWLER_NAME_FORMAT, bowler.toString()));

        for (Frame frame : bowler.getFrames()) {
            GameResults results = frame.getResults();
            System.out.printf(
                    String.format(GAME_RESULT_FORMAT,
                            results.getHistory().stream()
                                    .map(String::valueOf)
                                    .collect(Collectors.joining(GAME_RESULT_DELIMITER))
                    )
            );
        }
    }
}
