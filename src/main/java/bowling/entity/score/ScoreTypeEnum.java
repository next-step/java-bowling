package bowling.entity.score;

import bowling.entity.Pin;

import java.util.Arrays;
import java.util.LinkedList;

public enum ScoreTypeEnum {
    STRIKE(1, true) {
        @Override
        public ScoreType createScoreType(LinkedList<Pin> pins) {
            return new Strike();
        }

        @Override
        public ScoreType createLastFrameScoreType(LinkedList<Pin> pins) {
            return new Strike(0);
        }
    },
    SPARE(2, true) {
        @Override
        public ScoreType createScoreType(LinkedList<Pin> pins) {
            return new Spare(pins.getFirst());
        }

        @Override
        public ScoreType createLastFrameScoreType(LinkedList<Pin> pins) {
            return new Spare(pins.getFirst(), 0);
        }
    },
    MISS(2, false) {
        @Override
        public ScoreType createScoreType(LinkedList<Pin> pins) {
            return new Miss(pins.getFirst(), pins.getLast());
        }

        @Override
        public ScoreType createLastFrameScoreType(LinkedList<Pin> pins) {
            return new Miss(pins.getFirst(), new Pin(pins.getLast().pin() - pins.getFirst().pin()));
        }
    },
    NORMAL_SCORE(1, false) {
        @Override
        public ScoreType createScoreType(LinkedList<Pin> pins) {
            return new NormalScore(pins.getFirst());
        }

        @Override
        public ScoreType createLastFrameScoreType(LinkedList<Pin> pins) {
            return new NormalScore(pins.getFirst());
        }
    },
    NONE(0, false) {
        @Override
        public ScoreType createScoreType(LinkedList<Pin> pins) {
            return new None();
        }

        @Override
        public ScoreType createLastFrameScoreType(LinkedList<Pin> pins) {
            return new None();
        }
    };

    private final int count;
    private final boolean isTenPin;

    ScoreTypeEnum(int count, boolean isTenPin) {
        this.count = count;
        this.isTenPin = isTenPin;
    }

    public static ScoreTypeEnum findScoreType(int count, boolean isTenPin) {
        return Arrays.stream(values())
                .filter(ScoreTypeEnum -> ScoreTypeEnum.count == count)
                .filter(ScoreTypeEnum -> ScoreTypeEnum.isTenPin == isTenPin)
                .findFirst().orElse(NONE);
    }

    public abstract ScoreType createScoreType(LinkedList<Pin> pins);

    public abstract ScoreType createLastFrameScoreType(LinkedList<Pin> pins);
}
