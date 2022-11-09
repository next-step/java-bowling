package bowling.view;

import bowling.domain.Name;
import bowling.domain.frame.Frames;

public class ResultView {

    public static final String NAME_TITLE = "| NAME |  ";
    public static final String NAME_CONTENT = "| %4s |";
    public static final String TITLE_BAR_MARK = "  |  ";
    public static final String GUTTER_MARK = "-";
    public static final String ZERO = "0";

    public static void printFrameResult(Name name, Frames frames) {
        printFrameTitle();
        printFrameContents(name, frames);
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
        FrameView.frameResultContents(frames)
                .stream()
                .map(content -> content.replaceAll(ZERO, GUTTER_MARK))
                .forEach(System.out::print);
        System.out.println();
    }

}
