package bowling.domain.statusdetail;


import bowling.domain.frame.DownedPin;

import java.util.List;

public class Miss implements Status {

    private static final String gutterMark = "-";

    @Override
    public String interpretFrame(List<DownedPin> pins) {
        if (pins.size() == 0) {
            return "       ";
        }

        return " " + changeToGutterMark(0, pins) + " | " + changeToGutterMark(1, pins) + " ";
    }

    private String changeToGutterMark(int index, List<DownedPin> pins) {
        if (index >= pins.size()) {
            return " ";
        }

        if (pins.get(index).isGutter()) {
            return gutterMark;
        }

        return Integer.toString(pins.get(index).getNumDownedPin());
    }
}
