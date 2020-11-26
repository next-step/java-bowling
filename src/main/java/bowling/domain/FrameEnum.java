package bowling.domain;

import static bowling.assets.Const.PIN_NUM;

public enum FrameEnum {
    STRIKE,
    SPARE,
    MISS;

    public static FrameEnum of(CountOfPins first, CountOfPins second) {
        CountOfPins maxCount = CountOfPins.of(PIN_NUM);
        CountOfPins sumCount = first.sum(second);
        if (first.equals(maxCount)) {
            return STRIKE;
        }
        if (sumCount.equals(maxCount)) {
            return SPARE;
        }
        return MISS;
    }
}
