package bowling.view;

import bowling.domain.Bowler;
import bowling.domain.Frame;
import bowling.domain.GameResults;

import java.util.stream.Collectors;

public class Printer {
    public static void showResult(Bowler bowler) {
        showHead(bowler);
        showBody(bowler);
        System.out.println();
    }

    private static void showHead(Bowler bowler) {
        System.out.print("| NAME |");
        for (Frame frame : bowler.getFrames()) {
            System.out.print(String.format("  %02d  |", frame.getStage()));
        }
        System.out.println();
    }

    private static void showBody(Bowler bowler) {
        System.out.print(String.format("| %4s |", bowler.toString()));

        for (Frame frame : bowler.getFrames()) {
            GameResults results = frame.getResults();
            System.out.printf(
                    String.format("  %-3s |",
                            results.getHistory().stream()
                                    .map(result -> String.valueOf(result))
                                    .collect(Collectors.joining("|"))
                    )
            );
        }
    }
}
