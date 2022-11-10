package bowling.view;

import bowling.domain.Name;
import bowling.domain.frame.Frames;

import static bowling.view.FrameView.getNormalFramesScores;

public class ResultView {

    public static final String NAME_TITLE = "| NAME |  ";
    public static final String NAME_CONTENT = "| %4s |";
    public static final String SCORE_HEAD = "|      |";
    public static final String TITLE_BAR_MARK = "  |  ";
    public static final String GUTTER_MARK = "-";
    public static final String ZERO = "0";

    public static void printFrameResult(Name name, Frames frames) {
        printFrameTitle();
        printFrameContents(name, frames);
        printFrameScores(frames);
        System.out.println();
    }

    private static void printFrameTitle() {
        System.out.print(NAME_TITLE);
        FrameView.frameResultTitles()
                .stream()
                .map(title -> title + TITLE_BAR_MARK)
                .forEach(System.out::print);
        System.out.println();
    }

    private static void printFrameContents(Name name, Frames frames) {
        System.out.printf(NAME_CONTENT, name.getName());
        String content = FrameView.frameResultContents(frames).replaceAll(ZERO, GUTTER_MARK);
        System.out.println(content);
    }

    private static void printFrameScores(Frames frames) {
        String scores = SCORE_HEAD + getNormalFramesScores(frames);
        System.out.println(scores);
    }
}
