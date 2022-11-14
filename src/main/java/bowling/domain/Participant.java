package bowling.domain;

public class Participant {

    private String participant;

    private Participant(final String participant) {

        this.participant = participant;
    }

    public static Participant from(final String participant) {

        return new Participant(participant);
    }

    public String getParticipant() {
        
        return participant;
    }
}
