package bowling.domain.statusdetail;


import bowling.domain.frame.DownedPin;

import java.util.List;

public class Spare implements Status {

    private static final String spareMark = "/";

    @Override
    public String interpretFrame(List<DownedPin> pins) {
        return " " + changeMark(pins.get(0)) + " | " + spareMark + " ";
    }

    private String changeMark(DownedPin pin) {
        if (pin.isGutter()) {
            return "-";
        }

        return Integer.toString(pin.getNumDownedPin());
    }
}
