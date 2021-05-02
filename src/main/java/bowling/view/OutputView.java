package bowling.view;

import bowling.controller.dto.BowlingGameResponse;
import bowling.controller.dto.FrameInfo;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private final static int BOARD_WIDTH = 6;

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
        System.out.println("|");
    }

    private void printFrame(FrameInfo frameInfo) {
        if (frameInfo.isFinalFrame()) {
            printFinalFrame(frameInfo.getPinDownResults());
            return;
        }
        if (frameInfo.isSpare()) {
            printSpare(frameInfo.getPinDownResults());
            return;
        }
        printFrame(frameInfo.getPinDownResults());
    }

    private void printFinalFrame(List<Integer> pinDownResults) {
        if (pinDownResults.size() == 3 && pinDownResults.get(0) + pinDownResults.get(1) == 10) {
            printSpare(pinDownResults.stream().limit(2).collect(Collectors.toList()));
            String content = "|" + parseSign(pinDownResults.get(2));
            System.out.print(makeBoard(content));
        }
        printFrame(pinDownResults);
    }

    private void printSpare(List<Integer> pinDownResults) {
        String content = parseSign(pinDownResults.get(0)) + "|/";
        System.out.print(makeBoard(content));
    }

    private void printFrame(List<Integer> pinDownResults) {
        String content = pinDownResults.stream()
                .map(this::parseSign)
                .collect(Collectors.joining("|"));
        System.out.print(makeBoard(content));
    }

    private void printScoreBoard(List<FrameInfo> frameInfos) {
        System.out.print(makeBoard(""));
        for (int i = 0; i < frameInfos.size(); i++) {
            printScore(frameInfos, i);
        }
        printBlankBoard(frameInfos.size());
        System.out.print("|");
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
        if (score == 10) {
            return "X";
        }
        if (score == 0) {
            return "-";
        }
        return String.valueOf(score);
    }

    private void printBlankBoard(int count) {
        for (int i = 0; i < 10 - count; i++) {
            System.out.print(makeBoard(""));
        }
    }

    private String makeBoard(String content) {
        return "|" + alignCenter(content);
    }

    private String alignCenter(String content) {
        int paddingLeft = (BOARD_WIDTH - content.length()) / 2;
        int paddingRight = BOARD_WIDTH - content.length() - paddingLeft;
        return makeBlank(paddingLeft) + content + makeBlank(paddingRight);
    }

    private String makeBlank(int count) {
        StringBuilder blank = new StringBuilder();
        for (int i = 0; i < count; i++) {
            blank.append(" ");
        }
        return blank.toString();
    }
}
