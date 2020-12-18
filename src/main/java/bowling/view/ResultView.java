package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.User;

/**
 * Created : 2020-12-16 오전 11:23
 * Developer : Seo
 */
public class ResultView {

    public static final String DELIMITER = " |  ";

    public static void print(User user, Frames frames) {
        init(frames, user);
        eachFrame(frames, user);
    }

    private static void init(Frames frames, User user) {
        header(frames);
        System.out.printf("|  %-3s |", user.getName());
        frames.getFrames().stream().forEach(frame -> System.out.printf("  %-3s |", ""));
    }

    private static void header(Frames frames) {
        System.out.print("| NAME |");
        frames.getFrames().stream().forEach(frame -> System.out.printf("  %s  |", frame.getFrameNoString()));
        System.out.println();
    }

    private static void eachFrame(Frames frames, User user) {
        for (int i = 0; i < frames.size(); i++) {
            space();
            Frame frame = frames.get(i);
            frameSum(i, frame);
            header(frames);
            untilFrame(i, frames, user);
        }
    }

    private static void frameSum(int i, Frame frame) {
        System.out.println((i + 1) + "프레임 투구 : " + frame.getScore().get());
    }

    private static void untilFrame(int i, Frames frames, User user) {
        System.out.printf("|  %-3s |", user.getName());
        for (int j = 0; j < i + 1; j++) {
            System.out.printf("  %-3s |", frames.get(j).getScore().getSymbol(j));
        }
        for (int j = i + 1; j < 10; j++) {
            System.out.printf("  %-3s |", "");
        }
    }

    private static void space() {
        System.out.println();
        System.out.println();
    }

    private ResultView() {
    }
}
