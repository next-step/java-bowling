package bowling.domain.bowling;

import bowling.domain.bowl.Bowl;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.participant.Participant;
import bowling.domain.pin.Pin;
import bowling.domain.score.Score;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class Bowling {

    private final Participant participant;
    private final Frames frames;

    public Bowling(String nameOfParticipant) {
        this(new Participant(nameOfParticipant));
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

    public static List<Bowling> listOf(List<String> nameOfParticipants) {
        return nameOfParticipants.stream()
                .map(Bowling::new)
                .collect(toList());
    }

    public void pitch(Pin pin) {
        frames.pitch(pin);
    }

    public int numberOfFrame() {
        return frames.numberOfFrame();
    }

    public boolean isFrameEnd(int numberOfFrame) {
        return !frames.canPitchInFrame(numberOfFrame);
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
