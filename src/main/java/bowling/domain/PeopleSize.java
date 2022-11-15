package bowling.domain;

public class PeopleSize {
    private static final int PEOPLE_SIZE_MIN = 1;

    private final int size;

    public PeopleSize(int size) {
        if (size < PEOPLE_SIZE_MIN) {
            throw new IllegalArgumentException("참가자는 최소 " + PEOPLE_SIZE_MIN + "이상이어야 합니다.");
        }
        this.size = size;
    }

    public int size() {
        return this.size;
    }
}
