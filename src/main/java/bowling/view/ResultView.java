package bowling.view;

import bowling.domain.Frames;
import bowling.domain.User;

/**
 * Created : 2020-12-16 오전 11:23
 * Developer : Seo
 */
public class ResultView {

    public static final String DELIMITER = " |  ";

    public static void print(User user, Frames frames) {
        printHeader(frames);

        System.out.println();
        System.out.printf("|  %-3s |", user.getName());
        printScore(frames);

    }

    private static void printScore(Frames frames) {
        frames.getFrames().stream()
                .forEach(frame -> System.out.printf("  %s  |", frame.getScore().get()));
    }

    private static void printHeader(Frames frames) {
        System.out.print("| NAME |");
        frames.getFrames().stream()
                .forEach(frame -> System.out.printf("  %s  |", frame.getFrameNo()));
    }

    private ResultView() {
    }
}
