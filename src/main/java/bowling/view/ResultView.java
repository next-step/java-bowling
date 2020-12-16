package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.User;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created : 2020-12-16 오전 11:23
 * Developer : Seo
 */
public class ResultView {

    public static final String DELIMITER = " |  ";

    public static void print(User user, Frames frames) {
        System.out.println(frames.size());
        System.out.print("| NAME |");
        IntStream.range(1, 11)
                .forEach(value -> System.out.print("  0" + value + "  |"));
    }

    private ResultView() {
    }
}
