package bowling.view;

import bowling.domain.Bowling;
import bowling.domain.BowlingGame;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import bowling.domain.pin.FinalPins;
import bowling.domain.pin.NormalPins;
import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;

public class OutputView {

    private static final int NORMAL_PIN_SIZE = 2;
    private static final int FINAL_BONUS_PIN_SIZE = 3;
    private static final int LAST_NORMAL_FRAME = 9;
    private static final int LAST_FRAME = 10;

    private static final String BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |   10   |";

    private static final String STRIKE = "x";
    private static final String SPARE = "/";
    private static final String DIVIDER = "|";
    private static final String GUTTER = "-";

    private static final String EMPTY_SPACE_SINGLE = " ";
    private static final String EMPTY_SPACE_DOUBLE = "  ";
    private static final String EMPTY_SPACE_TRIPLE = EMPTY_SPACE_SINGLE + EMPTY_SPACE_DOUBLE;

    private static final String LEFT_BOX_SINGLE_SPACE = String.format("%s%s", DIVIDER, EMPTY_SPACE_SINGLE);
    private static final String RIGHT_BOX_SINGLE_SPACE = String.format("%s%s", EMPTY_SPACE_SINGLE, DIVIDER);
    private static final String RIGHT_BOX_DOUBLE_SPACE = String.format("%s%s", EMPTY_SPACE_DOUBLE, DIVIDER);

    private static final String NORMAL_FRAME_FORMAT = "%s%s%s";
    private static final String FINAL_FRAME_FORMAT = "%s%s%s%s%s";

    public void printGame(BowlingGame bowlingGame) {
        System.out.println(BOARD);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(nameStringBuilder(bowlingGame));
        stringBuilder.append(pointStringBuilder(bowlingGame.bowling()));
        System.out.println(stringBuilder.toString());
        System.out.println();
    }

    private StringBuilder nameStringBuilder(BowlingGame bowlingGame) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(LEFT_BOX_SINGLE_SPACE);
        stringBuilder.append(bowlingGame.player().name());
        stringBuilder.append(RIGHT_BOX_DOUBLE_SPACE);
        return stringBuilder;
    }

    private StringBuilder pointStringBuilder(Bowling bowling) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(normalFrameStrings(bowling.frames()));
        stringBuilder.append(finalFrameStrings(bowling.frames()));
        return stringBuilder;
    }

    private StringBuilder normalFrameStrings(Frames frames) {
        StringBuilder stringBuilder = new StringBuilder();
        int normalFrameCount = Math.min(frames.size(), LAST_NORMAL_FRAME);
        for (int count = 1; count <= normalFrameCount; count++) {
            stringBuilder.append(EMPTY_SPACE_DOUBLE);
            NormalFrame frame = (NormalFrame) frames.frames().get(count - 1);
            NormalPins pins = (NormalPins) frame.pins();
            stringBuilder.append(normalPinsString(pins));
            stringBuilder.append(RIGHT_BOX_SINGLE_SPACE);
        }
        for (int count = normalFrameCount + 1; count <= LAST_NORMAL_FRAME; count++) {
            stringBuilder.append(EMPTY_SPACE_DOUBLE);
            stringBuilder.append(EMPTY_SPACE_TRIPLE);
            stringBuilder.append(RIGHT_BOX_SINGLE_SPACE);
        }
        return stringBuilder;
    }

    private StringBuilder normalPinsString(NormalPins pins) {
        StringBuilder stringBuilder = new StringBuilder();
        if (pins.isEnd()) {
            return normalPinsEndedString(pins);
        }
        stringBuilder.append(String.format(NORMAL_FRAME_FORMAT, gutterIfNeeded(pins.pin(Pins.FIRST)),
                DIVIDER, EMPTY_SPACE_SINGLE));
        return stringBuilder;
    }

    private StringBuilder normalPinsEndedString(NormalPins pins) {
        StringBuilder stringBuilder = new StringBuilder();
        if (pins.isStrike()) {
            stringBuilder.append(String.format(NORMAL_FRAME_FORMAT, STRIKE, EMPTY_SPACE_SINGLE, EMPTY_SPACE_SINGLE));
            return stringBuilder;
        }
        if (pins.isSpare()) {
            stringBuilder.append(String.format(NORMAL_FRAME_FORMAT, gutterIfNeeded(pins.pin(Pins.FIRST)),
                    DIVIDER, SPARE));
            return stringBuilder;
        }
        stringBuilder.append(String.format(NORMAL_FRAME_FORMAT, gutterIfNeeded(pins.pin(Pins.FIRST)), DIVIDER,
                gutterIfNeeded(pins.pin(Pins.SECOND))));
        return stringBuilder;
    }

    private StringBuilder finalFrameStrings(Frames frames) {
        StringBuilder stringBuilder = new StringBuilder();
        if (frames.frames().size() <= LAST_NORMAL_FRAME) {
            stringBuilder.append(String.format(NORMAL_FRAME_FORMAT,
                    EMPTY_SPACE_TRIPLE, EMPTY_SPACE_TRIPLE, RIGHT_BOX_DOUBLE_SPACE));
            return stringBuilder;
        }
        FinalFrame frame = (FinalFrame) frames.frames().get(LAST_FRAME - 1);
        FinalPins pins = (FinalPins) frame.pins();
        if (frame.isEnd()) {
            stringBuilder.append(String.format(NORMAL_FRAME_FORMAT, EMPTY_SPACE_DOUBLE,
                    finalPinsEndedString(pins), RIGHT_BOX_SINGLE_SPACE));
            return stringBuilder;
        }
        stringBuilder.append(String.format(NORMAL_FRAME_FORMAT, EMPTY_SPACE_DOUBLE,
                finalPinsNotEndedString(pins), RIGHT_BOX_SINGLE_SPACE));
        return stringBuilder;
    }

    private StringBuilder finalPinsEndedString(FinalPins pins) {
        StringBuilder stringBuilder = new StringBuilder();
        if (pins.size() == NORMAL_PIN_SIZE) {
            stringBuilder.append(String.format(FINAL_FRAME_FORMAT, gutterIfNeeded(pins.pin(Pins.FIRST)),
                    DIVIDER, convertIfNeeded(pins.pin(Pins.SECOND)), DIVIDER, GUTTER));
            return stringBuilder;
        }
        Pin firstPin = pins.pin(Pins.FIRST);
        Pin secondPin = pins.pin(Pins.SECOND);
        Pin thirdPin = pins.pin(Pins.THIRD);
        if (firstPin.didClear() && secondPin.didClear()) {
            stringBuilder.append(String.format(FINAL_FRAME_FORMAT, STRIKE, DIVIDER, STRIKE,
                    DIVIDER, convertIfNeeded(thirdPin)));
            return stringBuilder;
        }
        if(firstPin.didClear() & thirdPin.didClear(secondPin)){
            stringBuilder.append(String.format(FINAL_FRAME_FORMAT, STRIKE, DIVIDER, convertIfNeeded(secondPin),
                    DIVIDER, SPARE));
            return stringBuilder;
        }
        if(firstPin.didClear()){
            stringBuilder.append(String.format(FINAL_FRAME_FORMAT, STRIKE, DIVIDER, convertIfNeeded(secondPin),
                    DIVIDER, convertIfNeeded(thirdPin)));
            return stringBuilder;
        }
        stringBuilder.append(String.format(FINAL_FRAME_FORMAT, gutterIfNeeded(firstPin),
                DIVIDER, SPARE, DIVIDER, convertIfNeeded(thirdPin)));
        return stringBuilder;
    }

    private StringBuilder finalPinsNotEndedString(FinalPins pins) {
        StringBuilder stringBuilder = new StringBuilder();
        Pin firstPin = pins.pin(Pins.FIRST);
        if (firstPin.didClear() && pins.size() < NORMAL_PIN_SIZE) {
            stringBuilder.append(String.format(FINAL_FRAME_FORMAT, STRIKE, DIVIDER, EMPTY_SPACE_SINGLE,
                    DIVIDER, EMPTY_SPACE_SINGLE));
            return stringBuilder;
        }
        if(pins.size()<NORMAL_PIN_SIZE){
            stringBuilder.append(String.format(FINAL_FRAME_FORMAT, gutterIfNeeded(firstPin), DIVIDER, EMPTY_SPACE_SINGLE,
                    DIVIDER, EMPTY_SPACE_SINGLE));
            return stringBuilder;
        }
        Pin secondPin = pins.pin(Pins.SECOND);
        if (firstPin.didClear() && pins.size() < FINAL_BONUS_PIN_SIZE) {
            stringBuilder.append(String.format(FINAL_FRAME_FORMAT, STRIKE, DIVIDER,
                    convertIfNeeded(secondPin), DIVIDER, EMPTY_SPACE_SINGLE));
            return stringBuilder;
        }
        if (secondPin.didClear(firstPin)) {
            stringBuilder.append(String.format(FINAL_FRAME_FORMAT, gutterIfNeeded(firstPin), DIVIDER, SPARE,
                    DIVIDER, EMPTY_SPACE_SINGLE));
            return stringBuilder;
        }
        stringBuilder.append(String.format(FINAL_FRAME_FORMAT, gutterIfNeeded(firstPin), DIVIDER, EMPTY_SPACE_SINGLE,
                DIVIDER, EMPTY_SPACE_SINGLE));
        return stringBuilder;
    }

    private String convertIfNeeded(Pin pin) {
        if (pin.guttered()) {
            return GUTTER;
        }
        if (pin.didClear()) {
            return STRIKE;
        }
        return String.valueOf(pin.pin());
    }

    private String gutterIfNeeded(Pin pin) {
        if (pin.guttered()) {
            return GUTTER;
        }
        return String.valueOf(pin.pin());
    }
}
