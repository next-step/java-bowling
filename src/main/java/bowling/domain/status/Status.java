package bowling.domain.status;

public interface Status {
    static Status makeStatus(int downPin) {
        if (downPin == 10) {
            return new Strike();
        }
        return new Miss(downPin);
    }

    static Status makeStatus(int beforePin, int downPin) {
        if (beforePin + downPin == 10) {
            return new Spare(beforePin, downPin);
        }
        return new Miss(beforePin, downPin);
    }

    String printResult();

    String printAllResult();

    default String isGutter(int downPin) {
        if (downPin == 0)
            return "-";
        return String.valueOf(downPin);
    }
}
