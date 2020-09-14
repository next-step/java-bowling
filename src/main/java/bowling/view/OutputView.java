package bowling.view;

import bowling.domain.Frames;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {
    private static final String NAME_TEXT = "| NAME |";
    private static final String FRAME_NUMBER_TEXT = "  %d  |";
    private static final String FRAME_RESULT_TEXT = "  %s  |";
    private static final int BOWLING_FIRST_FRAME = 1;

    public static void printFrames(Frames frames) {
        printFrameTop(frames);
        printFrameResult(frames);
    }

    private static void printFrameResult(Frames frames) {
        String playerName = frames.getPlayer();
        IntStream.iterate(BOWLING_FIRST_FRAME, index -> index + BOWLING_FIRST_FRAME)
                .limit(frames.size())
                .mapToObj(frames::getResult)
                .map(result -> String.format(FRAME_RESULT_TEXT, result))
                .collect(Collectors.joining());
    }

    private static void printFrameTop(Frames frames) {
        System.out.print(NAME_TEXT);

        String text = IntStream.iterate(BOWLING_FIRST_FRAME, index -> index + BOWLING_FIRST_FRAME)
                .limit(frames.size())
                .mapToObj(index -> String.format(FRAME_NUMBER_TEXT, index))
                .collect(Collectors.joining());

        System.out.println(text);
    }
}
