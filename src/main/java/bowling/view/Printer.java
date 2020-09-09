package bowling.view;

import bowling.domain.Frame;
import bowling.domain.FrameSet;
import bowling.domain.GameResults;

import java.util.stream.Collectors;

public class Printer {
    public static void showResult(FrameSet frameSet) {
        showHead(frameSet);
        showBody(frameSet);
        System.out.println();
    }

    private static void showHead(FrameSet frameSet) {
        System.out.print("| NAME |");
        for (Frame frame : frameSet.getFrames()) {
            System.out.print(String.format("  %02d  |", frame.getStage()));
        }
        System.out.println();
    }

    private static void showBody(FrameSet frameSet) {
        System.out.print(String.format("| %4s |", frameSet.getBowler()));

        for (Frame frame : frameSet.getFrames()) {
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
