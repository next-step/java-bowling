package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;

public class Bowling {

    private final List<PlayerFrame> playerFrames;
    private final ToIntFunction<Integer> rollFunction;
    private final RecordConsumer recordConsumer;

    public Bowling(List<PlayerFrame> playerFrames,
                   ToIntFunction<Integer> rollFunction,
                   RecordConsumer recordConsumer) {
        this.playerFrames = playerFrames;
        this.rollFunction = rollFunction;
        this.recordConsumer = recordConsumer;
    }

    public void start() {
        while (!isAllLaneEnd()) {
            rolling();
        }
    }

    public boolean isAllLaneEnd() {
        return playerFrames.stream()
                .allMatch(PlayerFrame::isComplete);
    }

    private void rolling() {
        playerFrames.forEach(playerFrame -> {
            while (playerFrame.canRoll()) {
                playerFrame.roll(rollFunction, () -> recordConsumer.acceptAndApply(playerFrames));
            }
            playerFrame.nextFrame();
        });
    }

    public static class Builder {
        private final List<PlayerFrame> playerFrames = new ArrayList<>();
        private final ToIntFunction<Integer> rollFunction;
        private Runnable action = () -> {};
        private Consumer<FrameRecordIterator> recordConsumer = recordIterator -> {};

        public Builder(Players players, ToIntFunction<Integer> rollFunction) {
            this.rollFunction = rollFunction;
            players.stream()
                    .forEach(player -> playerFrames.add(new PlayerFrame(player)));
        }

        public Builder rollEnded(Runnable action) {
            this.action = action;
            return this;
        }

        public Builder peekRecord(Consumer<FrameRecordIterator> recordConsumer) {
            this.recordConsumer = recordConsumer;
            return this;
        }

        public Bowling build() {
            RecordConsumer consumer = new RecordConsumer(action, recordConsumer);
            return new Bowling(playerFrames, rollFunction, consumer);
        }
    }
}
