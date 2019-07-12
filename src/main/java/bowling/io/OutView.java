package bowling.io;

import bowling.utils.Pretty;

import java.util.Arrays;

import static bowling.model.frame.Frame.SEPARATOR_OF_FRAME;
import static bowling.utils.Pretty.PARTITION;

public class OutView {

    public static void printProgress(String currentStates) {
        System.out.println("|  NAME  |   01   |   02   |   03   |   04   |   05   |   06   |   07   |   08   |   09   |   10   |");
        System.out.println(getCurrentScores(currentStates));
        System.out.println();
    }

    private static String getCurrentScores(String currentStates) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PARTITION);

        Arrays.stream(currentStates.split(SEPARATOR_OF_FRAME))
                .map(Pretty::alignCenter)
                .map(frame -> frame.concat(PARTITION))
                .forEach(stringBuilder::append);

        return stringBuilder.toString();
    }
}
