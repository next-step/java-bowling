package bowling.domain.game;

import bowling.domain.pin.FramePins;
import bowling.domain.pin.FramePinsCreator;
import bowling.domain.pin.Pins;
import bowling.exception.game.CanNotAccessMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FinalFrame implements Frame {
    public static final int FINAL_FRAME_NUMBER = 10;
    public static final int MAX_BONUS_BOWLING_BY_STRIKE = 2;
    public static final int MAX_BONUS_BOWLING_BY_SPARE = 1;

    private final FrameNumber frameNumber;
    private FramePins framePins;
    private List<Pins> bonusPins = new ArrayList<>();

    private FinalFrame() {
        this.frameNumber = FrameNumber.of(FINAL_FRAME_NUMBER);
    }

    public static FinalFrame init() {
        return new FinalFrame();
    }

    @Override
    public Frame next(int countOfPins) {
        if (!canBowling()) {
            throw new CanNotAccessMethod();
        }

        if (canBonusBowling()) {
            this.bonusPins.add(Pins.of(countOfPins));
            return this;
        }

        this.framePins = FramePinsCreator.next(framePins, Pins.of(countOfPins));
        return this;
    }

    private boolean canBonusBowling() {
        return framePins != null
                && ((framePins.isStrike() && bonusPins.size() < MAX_BONUS_BOWLING_BY_STRIKE)
                || (framePins.isSpare() && bonusPins.size() < MAX_BONUS_BOWLING_BY_SPARE));
    }

    @Override
    public boolean canBowling() {
        return framePins == null || !framePins.isEnd() || canBonusBowling();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(frameNumber, that.frameNumber) &&
                Objects.equals(framePins, that.framePins) &&
                Objects.equals(bonusPins, that.bonusPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, framePins, bonusPins);
    }
}
