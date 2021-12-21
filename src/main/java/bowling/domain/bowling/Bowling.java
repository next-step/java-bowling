package bowling.domain.bowling;

import bowling.domain.bowl.Bowl;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.participant.Participant;
import bowling.domain.pin.Pin;
import bowling.domain.score.Score;

import java.util.List;
import java.util.Objects;

public class Bowling {

    private final Participant participant;
    private final Frames frames;

    public Bowling(String name) {
        this(new Participant(name));
    }

    public Bowling(Participant participant) {
        this(participant, Frames.init());
    }

    public Bowling(String name, Frame frame) {
        this(new Participant(name), new Frames(frame));
    }

    public Bowling(Participant participant, Frames frames) {
        this.participant = participant;
        this.frames = frames;
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

    public boolean canPitchInFrame(int numberOfFrame) {
        return frames.canPitchInFrame(numberOfFrame);
    }

    public boolean canPitch() {
        return frames.canPitch();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bowling bowling = (Bowling) o;
        return Objects.equals(participant, bowling.participant) && Objects.equals(frames, bowling.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(participant, frames);
    }
}
