package bowling.type;

import bowling.domain.Shot;
import bowling.util.BowlingUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum LastFrameState {
    FIRST_SHOT {
        @Override
        public boolean isThisState(List<Shot> shots) {
            return shots.isEmpty();
        }

        @Override
        public int remainPins(int downPins) {
            return 10;
        }
    }, SECOND_SHOT {
        @Override
        public boolean isThisState(List<Shot> shots) {
            return shots.size() == 1 && shots.get(0).getDownCount() < 10;
        }

        @Override
        public int remainPins(int downPins) {
            return 10 - downPins;
        }
    }, FIRST_STRIKE {
        @Override
        public boolean isThisState(List<Shot> shots) {
            int sum = BowlingUtils.sum(shots);
            return shots.size() <= 2 && sum >= 10 && sum < 20;
        }

        @Override
        public int remainPins(int downPins) {
            return 20 - downPins;
        }
    }, SPARE {
        @Override
        public boolean isThisState(List<Shot> shots) {
            int sum = BowlingUtils.sum(shots);
            return shots.size() == 2 && sum == 10;
        }

        @Override
        public int remainPins(int downPins) {
            return 10;
        }
    }, DOUBLE_STRIKE {
        @Override
        public boolean isThisState(List<Shot> shots) {
            int sum = BowlingUtils.sum(shots);
            return shots.size() == 2 && sum == 20;
        }

        @Override
        public int remainPins(int downPins) {
            return 10;
        }
    };

    public abstract boolean isThisState(List<Shot> shots);

    public abstract int remainPins(int downPins);

    public static Optional<LastFrameState> getState(List<Shot> shots) {
        return Arrays.stream(LastFrameState.values())
                .filter((state) -> state.isThisState(shots))
                .findFirst();
    }

    public static int calculateRemainPins(List<Shot> shots) {
        LastFrameState currentState = Arrays.stream(LastFrameState.values())
                .filter((state) -> state.isThisState(shots))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("it is invalid situation"));

        return currentState.remainPins(BowlingUtils.sum(shots));
    }
}
