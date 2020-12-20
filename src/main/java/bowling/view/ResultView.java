package bowling.view;

import bowling.domain.Frames;
import bowling.domain.None;
import bowling.domain.User;
import bowling.domain.Users;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Created : 2020-12-16 오전 11:23
 * Developer : Seo
 */
public class ResultView {
    public static final String HEADER_NAME = "| NAME |";
    public static final String HEADER_FRAME_NO = "  %s  |";
    public static final String FORMAT_SPACE = "  %-3s |";
    public static final String DELIMITER = "|";
    public static final int START_FRAME = 1;
    public static final int FINAL_FRAME = 10;
    public static final int ONE = 1;
    public static final String NONE = "";
    public static final int USER_INDEX = 0;

    private Frames frames;
    private Users users;

    public ResultView(Frames frames, Users users) {
        this.frames = frames;
        this.users = users;
    }

    public ResultView() {
    }

    public void print() {
        header();
        scores();
    }

    private void header() {
        print(HEADER_NAME);
        IntStream.range(START_FRAME, FINAL_FRAME + ONE)
                .forEach(value -> {
                    String no = value < 10 ? "0" + value : String.valueOf(value);
                    printf(FORMAT_SPACE, no);
                });
        nextLine();
    }

    private void scores() {
        for (int i = USER_INDEX; i < this.users.size(); i++) {
            score(this.frames, this.users.get(i));
            sum(this.frames, this.users.get(i));
        }
    }

    private void score(Frames frames, User user) {
        printf(DELIMITER + FORMAT_SPACE, user.getName());
        printf(FORMAT_SPACE, NONE);
        frames.getFrames().forEach(frame -> frame.getUsers().getUsers()
                .forEach(userScore -> printf(FORMAT_SPACE, String.valueOf(Optional.ofNullable(userScore.getState()).orElse(new None())))));
        remainFrames(frames.size());
        nextLine();
    }

    private static void sum(Frames frames, User user) {
        printf(DELIMITER + FORMAT_SPACE, NONE);
        printf(FORMAT_SPACE, NONE);
        frames.getFrames().forEach(frame -> printf(FORMAT_SPACE, NONE));
        remainFrames(frames.size());
        nextLine();
    }

    private static void remainFrames(int frameNo) {
        for (int i = frameNo + ONE; i < FINAL_FRAME; i++) {
            printf(FORMAT_SPACE, NONE);
        }
    }

    private static void print(String args) {
        System.out.print(args);
    }

    private static void printf(String format, String args) {
        System.out.printf(format, args);
    }

    private static void nextLine() {
        System.out.println();
    }
}
