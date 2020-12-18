package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.User;

/**
 * Created : 2020-12-16 오전 11:23
 * Developer : Seo
 */
public class ResultView {

    public static final String FORMAT_SPACE = "  %-3s |";

    public static void print(User user, Frames frames) {
        init(frames, user);
        eachFrame(frames, user);
    }

    private static void init(Frames frames, User user) {
        header(frames);
        scores(frames, user);
    }

    private static void header(Frames frames) {
        printf("| NAME |", "");
        frames.getFrames().forEach(frame -> printf("  %s  |", frame.getFrameNoString()));
        space();
    }

    private static void scores(Frames frames, User user) {
        printf("|" + FORMAT_SPACE, user.getName());
        frames.getFrames().forEach(frame -> printf(FORMAT_SPACE, ""));
    }

    private static void eachFrame(Frames frames, User user) {
        for (int i = 0; i < frames.size(); i++) {
            space();
            space();
            sumFrameScore(i, frames.get(i));
            header(frames);
            untilFrame(i, frames, user);
        }
    }

    private static void sumFrameScore(int i, Frame frame) {
        printf((i + 1) + "프레임 투구 : " + frame.getScore().get(), "");
        space();
    }

    private static void untilFrame(int i, Frames frames, User user) {
        printf("|" + FORMAT_SPACE, user.getName());
        for (int j = 0; j < i + 1; j++) {
            printf(FORMAT_SPACE, frames.get(j).getScore().getSymbol(j));
        }
        remainFrames(i);
    }

    private static void remainFrames(int i) {
        for (int j = i + 1; j < 10; j++) {
            printf(FORMAT_SPACE, "");
        }
    }

    private static void printf(String format, String args) {
        System.out.printf(format, args);
    }

    private static void space() {
        System.out.println();
    }

    private ResultView() {
    }
}
