package bowling.domain.game;

import java.util.Objects;

public class BowlingGamesCursor {

    private int peopleCursor;
    private int frameNumberCursor;

    private BowlingGamesCursor() {
        this.peopleCursor = 0;
        this.frameNumberCursor = 0;
    }

    public static BowlingGamesCursor of() {
        return new BowlingGamesCursor();
    }

    public int increasePeopleCursor() {
        return this.peopleCursor++;
    }

    public int increaseFrameNumberCursor() {
        return this.frameNumberCursor++;
    }

    public void resetPeopleCursor() {
        this.peopleCursor = 0;
    }

    public int getPeopleCursor() {
        return peopleCursor;
    }

    public int getFrameNumberCursor() {
        return frameNumberCursor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGamesCursor that = (BowlingGamesCursor) o;
        return peopleCursor == that.peopleCursor && frameNumberCursor == that.frameNumberCursor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(peopleCursor, frameNumberCursor);
    }
}
