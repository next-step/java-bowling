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
        for (int i = 0; i < frames.getFrames().size(); i++) {
            sb.append(getScores(frames.getFrame(i), sumScore));
            sumScore += getSumScore(frames.getFrame(i).getTotal());
        }
        System.out.println(sb.toString());
    }

    private static int getSumScore(Integer score) {
        if(score != null){
            return score;
        }
        return 0;
    }

    private static String getScores(Frame beforeFrame, Integer sumScore) {
        Integer score = beforeFrame.getTotal();
        return makeBlockBlock(String.valueOf(score + sumScore));
    }

    private static void getFrames(Frames frames, Player player) {
        getPlayerName(player.getName());
        String frameViews = frames.getFrames().stream()
                .map(frame -> viewFrame(frame))
                .collect(Collectors.joining(BLANK_BLOCK));
        System.out.println(frameViews + BLANK_BLOCK);
    }

    private static void getPlayerName(String name) {
        System.out.print(BLANK_BLOCK + makeSpaceName(name) + BLANK_BLOCK);
    }

    private static String viewFrame(Frame frame) {
        Pins points = frame.getPins();
        return makeSpaceScore(convertScore(points));
    }
    private static String convertScore(Pins pins) {
        if (pins.isRolledOnce()) {
            return convertStringScore(pins.getFirstPin());
        }
        if (pins.isRolledTwice()) {
            return convertScore(pins.getSecondPin(), pins.getSecondPin());
        }
        if (pins.isRolledThird()) {
            return convertScore(pins.getFirstPin(), pins.getFirstPin()) + BLANK_BLOCK + convertStringScore(pins.getThirdPin());
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

    private static String convertScore(Pin point1, Pin point2) {
        int sumPoint = point1.getPin() + point2.getPin();
        if (sumPoint == 10) {
            return convertStringScore(point1) + BLANK_BLOCK + STRING_SPARE;
        }
        return convertStringScore(point1) + BLANK_BLOCK + convertStringScore(point2);
    }

    /**
     * 이름 출력시 5글자까지 반복하여 공백을 더해준다.
     *
     * @param userName
     * @return
     */
    private static String makeSpaceName(String userName) {
        if (userName.length() > MAX_NAME_LENGTH) {
            return userName;
        }
        if (userName.length() % 2 == 1) {
            return makeSpaceName(BLANK + userName);
        }
        return makeSpaceName(userName + BLANK);
    }

    private static String makeSpaceScore(String score) {
        if (score.length() > MAX_NAME_LENGTH) {
            return score;
        }
        return makeSpaceName(score);
    }

    private static String makeBlockBlock(String score) {
        if (score.length() > MAX_NAME_LENGTH) {
            return MARGIN_BLOCK;
        }
        return makeSpaceName(score) + BLANK_BLOCK;
    }
}
