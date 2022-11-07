package bowling.view;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frames;
import bowling.domain.status.Miss;
import bowling.domain.status.Ready;
import bowling.domain.status.Spare;
import bowling.domain.status.Strike;

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
    public static final String TWO_BLANK = "  ";
    public static final String ONE_BLANK = " ";
    public static final String BAR_MARK = "|";
    public static final String NULL_MARK = " ";
    public static final String GUTTER_MARK = "-";

    public static List<String> frameResultTitles() {
        return IntStream.rangeClosed(MIN_FRAME_NO, MAX_FRAME_NO)
                .mapToObj(i -> String.format("%02d", i))
                .collect(toList());
    }

    public static List<String> frameResultContents(Frames frames) {
        List<String> contents = getNormalFramesContents(frames);
        contents.add(getFinalFrameContents(frames));

        for (int i = frames.getFramesSize(); i < MAX_FRAME_NO; i++) {
            contents.add(String.format("%7s", BAR_MARK));
        }

        return contents;
    }

    private static String getFinalFrameContents(Frames frames) {
        String finalContent = "";
        if (frames.getCurrentFrame().isFinalFrame()) {
            FinalFrame finalFrame = (FinalFrame) frames.getCurrentFrame();
            finalContent = " " + finalFrame.getStatuses()
                    .stream()
                    .filter(status -> !(status instanceof Ready))
                    .map(status -> {
                        if (status instanceof Strike) {
                            return STRIKE_MARK + BAR_MARK;
                        }
                        if (status instanceof Spare) {
                            return status.getCountOfFirst() + BAR_MARK + SPARE_MARK + BAR_MARK;
                        }
                        if (status instanceof Miss) {
                            return status.getCountOfFirst() + BAR_MARK + status.getCountOfSecond() + BAR_MARK;
                        }
                        return status.getCountOfFirst() + BAR_MARK;
                    }).collect(Collectors.joining());

             finalContent = String.format("%-6s", finalContent);
             finalContent = finalFrame.isFinished() ? finalContent : finalContent + BAR_MARK;
        }

        return finalContent;
    }

    private static List<String> getNormalFramesContents(Frames frames) {
        return frames.getFrameStatus()
                .stream()
                .filter(status -> !Objects.isNull(status))
                .map(status -> {
                    if (status instanceof Strike) {
                        return TWO_BLANK + STRIKE_MARK + ONE_BLANK + TWO_BLANK + BAR_MARK;
                    }
                    if (status instanceof Spare) {
                        return TWO_BLANK + status.getCountOfFirst() + BAR_MARK + SPARE_MARK + ONE_BLANK + BAR_MARK;
                    }
                    if (status instanceof Miss) {
                        return TWO_BLANK + status.getCountOfFirst() + BAR_MARK + status.getCountOfSecond() + ONE_BLANK + BAR_MARK;
                    }
                    return TWO_BLANK + status.getCountOfFirst() + BAR_MARK + NULL_MARK + ONE_BLANK + BAR_MARK;
                })
                .collect(toList());
    }

}
