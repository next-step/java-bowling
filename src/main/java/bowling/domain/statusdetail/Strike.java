package bowling.domain.statusdetail;

import bowling.domain.frame.DownedPin;

import java.util.List;

public class Strike implements Status {

    private static final String strikeMark = "X";

    @Override
    public String interpretFrame(List<DownedPin> pins) {
        return " " + strikeMark + " ";
    }
}
