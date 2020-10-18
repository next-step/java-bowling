package bowling.domain.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.domain.player.Player;
import bowling.domain.score.ScoreConverter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

public class OutputView {

    private static final String BLANK = " ";
    private static final int MAX_NAME_LENGTH = 5;
    private static final String BOARD_TITLE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String BLANK_BLOCK = "|";
    private static final String MARK_JOIN_DELIMETER = "|";
    private static final String MARK_STRIKE = "X";
    private static final String MARK_SPARE = "/";
    private static final String MARK_GUTTER = "-";
    private static final String MARK_NONE = "";
    public static final int NAME_BLOCK = 0;
    private static final int BOARD_BOX = 11;
    private static Map<Integer, String> map = new HashMap<>();
    private static String[] scores = new String[BOARD_BOX];


    private OutputView() {
    }

    private static void printScore() {
        for (int i = 0; i < scores.length; i++) {
            System.out.print(makeSpaceName(scores[i]));
        }
        System.out.print(lineSeparator());
    }

    public static void getScoreBoard2(Player player,Frames frames) {
        getBoardTitle();
        viewFrames(frames, player);
        viewScores(frames);
    }

    private static void getBoardTitle() {
        System.out.println(BOARD_TITLE);
    }

    private static void scoreAppend(Frame frame, Map map) {
        for (int i = 1; i <= frame.getIndex(); i++) {
            scores[i] = makeSpaceScore(String.valueOf(map.get(i)));
        }
    }

    private static void viewFrameHead() {
        System.out.println(BOARD_TITLE);
    }

    private static void viewScores(Frames frames) {
        getPlayerName("");
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
        return makeSpaceScore2(String.valueOf(score + sumScore));
    }

    private static void viewFrames(Frames frames, Player player) {
        getPlayerName(player.getName());
        String frameViews = frames.getFrames().stream()
                .map(frame -> viewFrame(frame))
                .collect(Collectors.joining(BLANK_BLOCK));
        System.out.println(frameViews + BLANK_BLOCK);
    }

    private static void getPlayerName(String name) {
        System.out.print("|" + makeSpaceName(name) + "|");
    }

    private static String viewFrame(Frame frame) {
        Pins points = frame.getPins();
        return makeSpaceScore(getScoreMark(points));
    }
    private static String getScoreMark(Pins pins) {
        if (pins.rollCount() == 1) {
            return getMark(pins.getPin(0));
        }
        if (pins.rollCount() == 2) {
            return getMark(pins.getPin(0), pins.getPin(1));
        }
        if (pins.rollCount() == 3) {
            return getMark(pins.getPin(0), pins.getPin(1)) + MARK_JOIN_DELIMETER + getMark(pins.getPin(2));
        }
        return "";
    }

    private static String getMark(Pin pin) {
        if (pin.getPin() == 10) {
            return MARK_STRIKE;
        }
        if (pin.getPin() == 0) {
            return MARK_GUTTER;
        }
        return String.valueOf(pin.getPin());
    }

    private static String getMark(Pin point1, Pin point2) {
        int sumPoint = point1.getPin() + point2.getPin();
        if (sumPoint == 10) {
            return getMark(point1) + MARK_JOIN_DELIMETER + MARK_SPARE;
        }
        return getMark(point1) + MARK_JOIN_DELIMETER + getMark(point2);
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

    private static String makeSpaceScore2(String score) {
        if (score.length() > MAX_NAME_LENGTH) {
            return "        |";
        }
        return makeSpaceName(score) + "|";
    }
}
