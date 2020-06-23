package bowling.pitch;

import bowling.score.Score;

import java.util.ArrayList;
import java.util.List;

public class FinalPitches implements Pitches {

    private final List<Pitch> pitches = new ArrayList<>();

    @Override
    public void throwBall(Score score) {
        Pitch pitch = createPitch(score);
        if (pitches.isEmpty()) {
            pitches.add(Pitch.initiate(score));
            return;
        }
        if (pitches.get(pitches.size() - 1).isStrike() || pitches.get(pitches.size() - 1).isSpare()) {
            pitches.add(Pitch.initiate(score));
            return;

        }
    }

    private Pitch createPitch(Score score) {
        if (pitches.isEmpty()) {
            return Pitch.initiate(score);
        }
        Pitch pitch = getCurrentPitch();
        if (pitch.isSpare() || pitch.isStrike()) {
            return Pitch.initiate(score);
        }
        return pitch.next(score);
    }

    private Pitch getCurrentPitch() {
        return pitches.get(pitches.size() - 1);
    }

    @Override
    public List<Pitch> getPitches() {
        return pitches;
    }
}
