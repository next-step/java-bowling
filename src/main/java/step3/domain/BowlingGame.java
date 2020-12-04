package step3.domain;

import step3.exception.InvalidPitchesException;

import java.util.List;

public class BowlingGame {

    public static final int FRAME_FIRST_NO = 0;
    public static final int FRAME_LAST_NO = 8;
    public static final int FRAME_SIZE = 10;

    public static Frames build() {
        return Frames.Builder()
                .size(FRAME_SIZE)
                .head(new NormalFrame(FRAME_FIRST_NO))
                .build();
    }

    public static GameHistory pitches(Frames frames, int pitchesCount) throws InvalidPitchesException {
        Frame frame = frames.getCursor();
        frames.updateCursor();
        frame.pitches(pitchesCount);

        return new GameHistory(frames.getMarks(frames.getHead()), frames.getScores(frames.getHead()));

    }
}
