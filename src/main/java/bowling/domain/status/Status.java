package bowling.domain.status;

public interface Status {
    Status bowl(int downPin);

    String printResult();

    boolean canPlayMore();

    boolean isClearAllPins();

    default String isGutter(int downPin) {
        if (downPin == 0) {
            return "-";
        }
        return String.valueOf(downPin);
    }


}
