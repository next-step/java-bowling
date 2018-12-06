package domain.status;

import domain.Pin;
import domain.enums.StatusFrameEnum;

public class Status {
    public static Status of(Pin pin, StatusFrameEnum statusFrameEnum) {
        if (pin.isAllPinDown() && StatusFrameEnum.FIRST == statusFrameEnum) {
            return Strike.getInstance();
        }

        if (pin.isAllPinDown() && StatusFrameEnum.SECOND == statusFrameEnum) {
            return Spare.getInstance();
        }

        if (pin.isAllPinUp()) {
            return Gutter.getInstance();
        }

        if (!pin.isAllPinDown() && StatusFrameEnum.SECOND == statusFrameEnum) {
            return new Miss(Pin.copyOf(pin));
        }


        return new Hit(Pin.copyOf(pin));
    }

}
