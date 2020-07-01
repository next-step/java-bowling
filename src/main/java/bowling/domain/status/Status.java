package bowling.domain.status;

public interface Status {
    Status next(int downPin);

    String printResult();

    boolean canPlayMore();

    boolean isClearAllPins();

    boolean canRemovePendingStatue();

    default String isGutter(int downPin) {
        if (downPin == 0) {
            return "-";
        }
        return String.valueOf(downPin);
    }


}
