package bowling.view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ContentPrintUtils {

    public static String fillContentBoarding(String content) {
        return " " + content + " ";
    }

    public static String fillBothSideMargin(String content, int minimumContentLength) {
        if (content.length() >= minimumContentLength) {
            return content;
        }

        int margin = minimumContentLength - content.length();

        return repeatedCharacterString(' ', margin - margin / 2)
                + content
                + repeatedCharacterString(' ', margin / 2);
    }

    public static List<String> fillEmptyFrameContent(List<String> rawContents, int minimumCountsOfContents) {
        List<String> fixedSizeContents = new ArrayList<>(rawContents);

        for (int i = 0; i < minimumCountsOfContents - rawContents.size(); i++) {
            fixedSizeContents.add("");
        }

        return fixedSizeContents;
    }

    public static String repeatedCharacterString(char c, int repeatTimes) {
        return IntStream.range(0, repeatTimes)
                .mapToObj(x -> Character.toString(c))
                .collect(Collectors.joining(""));
    }
}
