package bowling.domain.pitch;

public class Pitch {

    private int pitchCount;

    private Pitch(int pitchCount) {
        this.pitchCount = pitchCount;
    }

    public static Pitch pitch(int pitchCount) {
        return new Pitch(pitchCount);
    }
}
