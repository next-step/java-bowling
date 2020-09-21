package bowling.domain;

import java.util.List;
import java.util.function.Consumer;

public class RecordConsumer {

    private final Runnable runnable;
    private final Consumer<FrameRecordIterator> recordConsumer;

    public RecordConsumer(Runnable runnable, Consumer<FrameRecordIterator> recordConsumer) {
        this.runnable = runnable;
        this.recordConsumer = recordConsumer;
    }

    public void acceptAndApply(List<PlayerFrame> playerFrames) {
        runnable.run();
        playerFrames.forEach(playerFrame -> recordConsumer.accept(playerFrame.getFrameRecordIterator()));
    }
}
