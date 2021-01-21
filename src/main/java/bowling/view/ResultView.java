package bowling.view;

import java.util.List;

import static bowling.view.ContentPrintUtils.*;

public class ResultView {

    private static final int maximumCountsOfFrames = 10;
    private static final int defaultMargin = 7;

    private ResultView() {
    }

    public static void printResults(String name, List<String> descriptions) {
        printStatusBar(descriptions);
        printName(name);
        printDescription(descriptions);
    }

    private static void printStatusBar(List<String> contents) {
        System.out.print("| NAME |");

        List<String> contentsWithEmptyContents = fillEmptyFrameContent(contents, maximumCountsOfFrames);

        for (int i = 1; i <= maximumCountsOfFrames; i++) {
            String content = fillBothSideMargin(Integer.toString(i),
                    Math.max(contentsWithEmptyContents.get(i - 1).length() + 2, defaultMargin));

            System.out.print(content + "|");
        }
        System.out.println();
    }

    public static void printName(String name) {
        System.out.print("|  " + name + " |");
    }

    public static void printDescription(List<String> contents) {
        List<String> contentsWithEmpty = fillEmptyFrameContent(contents, maximumCountsOfFrames);

        for (String content : contentsWithEmpty) {
            String marginAndBoardingFilledContent = fillBothSideMargin(fillContentBoarding(content), defaultMargin);
            System.out.print(marginAndBoardingFilledContent + "|");
        }

        System.out.println();
    }
}
