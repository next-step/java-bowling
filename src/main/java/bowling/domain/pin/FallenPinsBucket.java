package bowling.domain.pin;

import bowling.domain.exception.OutOfBoundFallenPinsBucket;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class FallenPinsBucket {

    public static final int NORMAL_FRAME_SIZE = 2;
    public static final int FINAL_FRAME_SIZE = 3;

    private final FallenPins[] bucket;

    private FallenPinsBucket(int size) {
        this.bucket = new FallenPins[size];
    }

    public static FallenPinsBucket ofNormalSize() {
        return new FallenPinsBucket(NORMAL_FRAME_SIZE);
    }

    public static FallenPinsBucket ofFinalSize() {
        return new FallenPinsBucket(FINAL_FRAME_SIZE);
    }

    public FallenPins getFallenPins(int index) {
        validateIndex(index);
        return bucket[index];
    }

    public void saveFallenPins(FallenPins fallenPins, int index) {
        validateIndex(index);
        bucket[index] = fallenPins;
    }

    public boolean isTurnFinished(int index) {
        validateIndex(index);
        return Optional.ofNullable(bucket[index])
                .isPresent();
    }

    public boolean isFull() {
        return Arrays.stream(bucket)
                .allMatch(Objects::nonNull);
    }

    public int getSize() {
        return bucket.length;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= bucket.length) {
            throw new OutOfBoundFallenPinsBucket();
        }
    }

}
