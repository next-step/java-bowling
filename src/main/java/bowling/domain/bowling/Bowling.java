package bowling.domain.bowling;

import bowling.domain.bowl.Bowl;
import bowling.domain.frame.Frames;
import bowling.domain.participant.Participant;
import bowling.domain.pin.Pin;
import bowling.domain.score.Score;

import java.util.List;

public class Bowling {

    private final Participant participant;
    private final Frames frames;

    public Bowling(Participant participant) {
        this.participant = participant;
        frames = Frames.init();
    }

    /**
     * @return 더 투구할 수 있는지
     */
    public boolean pitch(Pin pin) {
        return frames.pitch(pin);
    }

    public int numberOfFrame() {
        return frames.numberOfFrame();
    }

    public String nameOfParticipant() {
        return participant.getName();
    }

    public List<Bowl> bowls() {
        return frames.bowls();
    }

    public List<Score> scores() {
        return frames.scores();
    }
}
