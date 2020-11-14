package bowling.domain.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.domain.player.Player;

import java.util.stream.Collectors;

public class OutputView {

    private static final String BLANK = " ";
    private static final int MAX_NAME_LENGTH = 5;
    private static final String BOARD_TITLE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String BLANK_BLOCK = "|";
    private static final String STRING_STRIKE = "X";
    private static final String STRING_SPARE = "/";
    private static final String STRING_GUTTER = "-";
    private static final String STRING_NONE = "";
    private static final String MARGIN_BLOCK = "        |";
    private static final int SPARE_PIN = 10;
    private static final int NO_HIT = 0;

    private OutputView() {
    }

    public static void getScoreBoard(Player player, Frames frames) {
        getBoardTitle();
        getFrames(frames, player);
        getScores(frames);
    }

    private static void getBoardTitle() {
        System.out.println(BOARD_TITLE);
    }

    private static void getScores(Frames frames) {
        getPlayerName(STRING_NONE);
        StringBuilder sb = new StringBuilder();
        Integer sumScore = 0;
        for (int i = 0; i < frames.getFrameSize(); i++) {
            sb.append(getScores(frames.getFrame(i), sumScore));
            sumScore += getSumScore(frames.getFrame(i).getScore());
        }
        System.out.println(sb.toString());
    }

    private static int getSumScore(Integer score) {
        if (score != null) {
            return score;
        }
        return 0;
    }

    private static String getScores(Frame frame, Integer sumScore) {
//        if (frame.hasRolled() && (frame.getPins().isStrike() || frame.getPins().isSpare())) {
//            return makeMargin(STRING_NONE) + BLANK_BLOCK;
//        } else
        if (frame.hasRolled()){
            return makeBlockSpace(String.valueOf(frame.getTotal() + sumScore));
        }
        return makeMargin(STRING_NONE) + BLANK_BLOCK;
    }

    private static void getFrames(Frames frames, Player player) {
        getPlayerName(player.getName());
        String frameViews = frames.getFrames().stream()
                .map(frame -> getFrame(frame))
                .collect(Collectors.joining(BLANK_BLOCK));
        System.out.println(frameViews + BLANK_BLOCK);
    }

    private static void getPlayerName(String name) {
        System.out.print(BLANK_BLOCK + makeMargin(name) + BLANK_BLOCK);
    }

    private static String getFrame(Frame frame) {
        Pins pins = frame.getPins();
        return makeSpaceScore(convertScore(pins));
    }

    private static String convertScore(Pins pins) {
        if (pins.isRolledOnce()) {
            return convertStringScore(pins.getFirstPin());
        }
        if (pins.isRolledTwice()) {
            return convertStringScore(pins.getFirstPin(), pins.getSecondPin());
        }
        if (pins.isRolledThird()) {
            return convertStringScore(pins.getFirstPin(), pins.getFirstPin()) + BLANK_BLOCK + convertStringScore(pins.getThirdPin());
        }
        return STRING_NONE;
    }

    private static String convertStringScore(Pin pin) {
        if (pin.isStrike()) {
            return STRING_STRIKE;
        }
        if (pin.isNotHit()) {
            return STRING_GUTTER;
        }
        return String.valueOf(pin.getPin());
    }

    private static String convertStringScore(Pin pin1, Pin pin2) {
        int totalPins = pin1.getPin() + pin2.getPin();
        return totalPins == SPARE_PIN ? getScoreAndSpare(pin1) : getOnlyScore(pin1, pin2);
    }

    private static String getOnlyScore(Pin pin1, Pin pin2) {
        return convertStringScore(pin1) + BLANK_BLOCK + convertStringScore(pin2);
    }

    private static String getScoreAndSpare(Pin pin1) {
        return convertStringScore(pin1) + BLANK_BLOCK + STRING_SPARE;
    }

    /**
     * 이름 출력시 5글자까지 반복하여 공백을 더해준다.
     *
     * @param value
     * @return
     */
    private static String makeMargin(String value) {
        if (value.length() > MAX_NAME_LENGTH) {
            return value;
        }
        if (value.length() % 2 == 1) {
            return makeMargin(BLANK + value);
        }
        return makeMargin(value + BLANK);
    }

    private static String makeSpaceScore(String score) {
        if (score.length() > MAX_NAME_LENGTH) {
            return score;
        }
        return makeMargin(score);
    }

    private static String makeBlockSpace(String score) {
        if (score.length() > MAX_NAME_LENGTH) {
            return MARGIN_BLOCK;
        }
        return makeMargin(score) + BLANK_BLOCK;
    }
}
