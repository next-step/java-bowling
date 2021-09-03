package bowling.step2.domain;

public class Participant {
    private final String name;

    private final int MIN_NAME_LENGTH = 1;
    private final int MAX_NAME_LENGTH = 3;

    private Participant(String name) {
        validateName(name);
        this.name = name;
    }

    public static Participant of(String name) {
        return new Participant(name);
    }

    private void validateName(String name) {
        if (nameLengthNotInRange(name)) {
            throw new RuntimeException("이름은 1에서 3자리까지 가능합니다.");
        }
    }

    private boolean nameLengthNotInRange(String name) {
        return name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH;
    }

    public String name() {
        return this.name;
    }
}
