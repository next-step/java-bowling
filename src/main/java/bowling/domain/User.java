package bowling.domain;

public class User {
    private static final String MAX_LENGTH_OVER = "이름은 최대 3글자입니다.";
    private static final int MAX_LENGTH = 3;

    private final String name;
    private Frames frames;

    private User(String name) {
        this(name, Frames.init());
    }

    private User(String name, Frames frames) {
        this.name = name;
        this.frames = frames;
    }

    public static User of(String name) {
        validMaxLength(name);
        return new User(name);
    }

    public String name() {
        return name;
    }

    public Frames frames() {
        return frames;
    }

    public boolean isPlay() {
        return frames.isPlay();
    }

    public boolean isEndFrame() {
        return frames.isLastIndexFrameEnd();
    }

    public void play(int score) {
        frames = frames.play(score);
        frames.scoreInit();
    }

    private static void validMaxLength(String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(MAX_LENGTH_OVER);
        }
    }
}
