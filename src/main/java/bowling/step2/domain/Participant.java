package bowling.step2.domain;

public class Participant {
    private final String name;

    private Participant(String name) {
        this.name = name;
    }

    public static Participant of(String name) {
        return new Participant(name);
    }

    public String name() {
        return this.name;
    }
}
