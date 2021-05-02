package bowling.view;

import bowling.controller.dto.BowlingGameResponse;
import bowling.domain.dto.FrameInfo;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private final static int BOARD_WIDTH = 6;
    private final static int FRAME_COUNT = 10;
    private final static String BOARD_DELIMITER = "|";
    private final static String BLANK = " ";

    private final static String SCORE_SIGN_STRIKE = "X";
    private final static String SCORE_SIGN_SPARE = "/";
    private final static String SCORE_SIGN_GUTTER = "-";
    private final static int SCORE_STRIKE = 10;
    private final static int SCORE_GUTTER = 0;

    private final static int FIRST_PITCH = 0;
    private final static int SECOND_PITCH = 1;
    private final static int FINAL_BONUS_PITCH = 2;
    private final static int MAX_FINAL_FRAME_SIZE = 3;

    public void printFrame(BowlingGameResponse response) {
        printBoardHeader();
        printBoardBody(response);
    }

    private void printBoardHeader() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    private void printBoardBody(BowlingGameResponse response) {
        printFrameBoard(response);
        printScoreBoard(response.getFrameInfos());
    }

    private void printFrameBoard(BowlingGameResponse response) {
        System.out.print(makeBoard(response.getParticipantName()));
        for (FrameInfo frameInfo : response.getFrameInfos()) {
            printFrame(frameInfo);
        }
        printBlankBoard(response.getFrameInfos().size());
        System.out.println(BOARD_DELIMITER);
    }

    private void printFrame(FrameInfo frameInfo) {
        if (frameInfo.isFinalFrame()) {
            printFinalFrame(frameInfo);
            return;
        }
        if (frameInfo.isSpare()) {
            printSpare(frameInfo.getPinDownResults());
            return;
        }
        printFrame(frameInfo.getPinDownResults());
    }

    private void printFinalFrame(FrameInfo frameInfo) {
        if (frameInfo.isSpare()) {
            String content = parseSpareContent(frameInfo.getPinDownResults());
            content += parseFinalBonusPitch(frameInfo.getPinDownResults());
            System.out.print(makeBoard(content));
            return;
        }
        printFrame(frameInfo.getPinDownResults());
    }

    private String parseFinalBonusPitch(List<Integer> pinDownResults) {
        if (pinDownResults.size() > FINAL_BONUS_PITCH) {
            return BOARD_DELIMITER + parseSign(pinDownResults.get(FINAL_BONUS_PITCH));
        }
        return BLANK;
    }

    private void printSpare(List<Integer> pinDownResults) {
        String content = parseSpareContent(pinDownResults);
        System.out.print(makeBoard(content));
    }

    private String parseSpareContent(List<Integer> pinDownResults) {
        return parseSign(pinDownResults.get(FIRST_PITCH)) + BOARD_DELIMITER + SCORE_SIGN_SPARE;
    }

    private void printFrame(List<Integer> pinDownResults) {
        String content = pinDownResults.stream()
                .map(this::parseSign)
                .collect(Collectors.joining(BOARD_DELIMITER));
        System.out.print(makeBoard(content));
    }

    private void printScoreBoard(List<FrameInfo> frameInfos) {
        System.out.print(makeBoard(BLANK));
        for (int i = 0; i < frameInfos.size(); i++) {
            printScore(frameInfos, i);
        }
        printBlankBoard(frameInfos.size());
        System.out.print(BOARD_DELIMITER);
    }

    private void printScore(List<FrameInfo> frameInfos, int frameIndex) {
        String content = "";
        FrameInfo targetFrame = frameInfos.get(frameIndex);
        if (targetFrame.isScoreDecided()) {
            int score = frameInfos.stream()
                    .limit(frameIndex + 1)
                    .mapToInt(FrameInfo::getScore)
                    .sum();
            content = String.valueOf(score);
        }
        System.out.print(makeBoard(content));
    }

    private String parseSign(int score) {
        if (score == SCORE_STRIKE) {
            return SCORE_SIGN_STRIKE;
        }
        if (score == SCORE_GUTTER) {
            return SCORE_SIGN_GUTTER;
        }
        return String.valueOf(score);
    }

    private void printBlankBoard(int count) {
        for (int i = 0; i < FRAME_COUNT - count; i++) {
            System.out.print(makeBoard(BLANK));
        }
    }

    private String makeBoard(String content) {
        return BOARD_DELIMITER + alignCenter(content);
    }

    private String alignCenter(String content) {
        int paddingLeft = (BOARD_WIDTH - content.length()) / 2;
        int paddingRight = BOARD_WIDTH - content.length() - paddingLeft;
        return makeBlank(paddingLeft) + content + makeBlank(paddingRight);
    }

    private String makeBlank(int count) {
        StringBuilder blank = new StringBuilder();
        for (int i = 0; i < count; i++) {
            blank.append(BLANK);
        }
        return blank.toString();
    }
}
