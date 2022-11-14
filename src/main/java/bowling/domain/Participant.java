package bowling.domain;

public class Participant {

    private final String name;

    private Participant(final String name) {

        this.name = name;
    }

    public static Participant from(final String participant) {

        return new Participant(participant);
    }

    public String getName() {
        
        return name;
    }
}
