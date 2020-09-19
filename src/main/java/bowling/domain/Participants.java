package bowling.domain;

public class Participants {

    private static final int LIMIT_LENGTH = 3;
    private String name;
    private BowlingFrame bowlingFrame;

    private Participants(String name, BowlingFrame bowlingFrame) {
        this.name = name;
        this.bowlingFrame = bowlingFrame;
    }

    public static Participants of(String name) {
        validationCheck(name);
        return new Participants(name, BowlingFrame.init());
    }

    private static void validationCheck(String name) {
        if (name.length() != LIMIT_LENGTH) {
            throw new IllegalArgumentException("영어 3글자로 입력해주세요");
        }
    }

    public String getName() {
        return name;
    }

    public BowlingFrame getBowlingFrame() {
        return bowlingFrame;
    }
}
