package bowling.view;

import bowling.domain.Pitch;

public class CountView {
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String GUTTER = "-";
    
    public static String getCount(Pitch frame) {
        if (frame.isStrike()) {
            return STRIKE;
        }
        if (frame.isSpare()) {
            return SPARE;
        }
        if (frame.isGutter()) {
            return GUTTER;
        }
        return String.valueOf(frame.getPinCount());
    }
}
