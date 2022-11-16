package bowling.view;

import bowling.domain.Score;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frames;
import bowling.domain.status.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class FrameView {
    private static final int MAX_FRAME_NO = 10;
    private static final int MIN_FRAME_NO = 1;
    public static final String STRIKE_MARK = "X";
    public static final String SPARE_MARK = "/";
    public static final String BAR_MARK = "|";

    public static List<String> frameResultTitles() {
        return IntStream.rangeClosed(MIN_FRAME_NO, MAX_FRAME_NO)
                .mapToObj(i -> String.format("%02d", i))
                .collect(toList());
    }

    public static String frameResultContents(Frames frames) {
        StringBuilder contents = new StringBuilder(getNormalFramesContents(frames));
        contents.append(getFinalFrameContents(frames));
        getRemainFramesScores(frames.getCurrentFrameIdx() + 1, contents);

        return contents.toString();
    }

    private static String getNormalFramesContents(Frames frames) {
        return frames.getFrameStatus()
                .stream()
                .filter(status -> !Objects.isNull(status))
                .map(status -> { String content = "   ";
                    if (status instanceof Strike) {
                        content = String.format("%-3s", STRIKE_MARK);
                    }
                    if (status instanceof Spare) {
                        String spareScore = String.join(BAR_MARK, String.valueOf(status.getCountOfFirst()), SPARE_MARK);
                        content = String.format("%-3s", spareScore);
                    }
                    if (status instanceof Miss) {
                        String missScore = String.join(BAR_MARK, String.valueOf(status.getCountOfFirst()), String.valueOf(status.getCountOfSecond()));
                        content = String.format("%-3s", missScore);
                    }
                    if (status instanceof FirstBowl) {
                        content = String.format("%-3s", status.getCountOfFirst() + BAR_MARK);
                    }
                    return content;
                })
                .collect(Collectors.joining(" |  ", "  ", " |"));
    }

    private static String getFinalFrameContents(Frames frames) {
        String finalContent = "";
        if (frames.getCurrentFrame().isFinalFrame()) {
            FinalFrame finalFrame = (FinalFrame) frames.getCurrentFrame();
            finalContent = " " + finalFrame.getStatuses()
                    .stream()
                    .filter(status -> !(status instanceof Ready))
                    .map(status -> { String content = "";
                        if (status instanceof Strike) {
                            content = STRIKE_MARK;
                        }
                        if (status instanceof Spare) {
                            content = String.join(BAR_MARK, String.valueOf(status.getCountOfFirst()), SPARE_MARK);
                        }
                        if (status instanceof Miss) {
                            content = String.join(BAR_MARK, String.valueOf(status.getCountOfFirst()), String.valueOf(status.getCountOfSecond()));
                        }
                        if (status instanceof FirstBowl) {
                            content = String.valueOf(status.getCountOfFirst());
                        }
                        return content;
                    }).collect(Collectors.joining(BAR_MARK));

            finalContent = String.format("%-6s", finalContent) + BAR_MARK;
        }
        return finalContent;
    }

    public static String getNormalFramesScores(Frames frames) {
        StringBuilder contents = new StringBuilder();
        String content = frames.getFrameScores()
                .stream()
                .map(score -> String.format("%2s", score.getValue()))
                .collect(Collectors.joining("  |  ", "  ", "  |"));

        int startIdx = 0;
        if (content.length() >= 7) {
            contents.append(content);
            startIdx = frames.getCurrentFrameIdx() + 1;
        }

        getRemainFramesScores(startIdx, contents);
        return contents.toString();
    }

    private static void getRemainFramesScores(int startIdx, StringBuilder contents) {
        for (int i = startIdx; i < MAX_FRAME_NO; i++) {
            contents.append(String.format("%7s", BAR_MARK));
        }
    }

    public static String getFinalFramesScores(Frames frames) {
        if (frames.getCurrentFrame().isFinalFrame()) {
            Score score = frames.getCurrentFrame().getScore();
            String finalScore = String.valueOf(score.getValue());
            return String.format("  " + "%-4s", finalScore) + BAR_MARK;
        }
        return "";
    }
}
