package bowling.view;

import bowling.controller.dto.BowlingGameResponse;

public class OutputView {

    private final static int boardWidth = 6;

    public void printFrame(BowlingGameResponse response) {
        printBoardHeader();
        printBoard(response);
    }

    private void printBoardHeader() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    private void printBoard(BowlingGameResponse response) {
        System.out.print(printBoard(response.getParticipantName()));
        for (String pinDownResult : response.getPinDownResults()) {
            System.out.print(printBoard(pinDownResult));
        }
        for (int i = 0; i < 10 - response.getPinDownResults().size(); i++) {
            System.out.print(printBoard(""));
        }
        System.out.print("|");
    }

    private String printBoard(String content) {
        return "|" + alignCenter(content);
    }

    private String alignCenter(String content) {
        int paddingLeft = (boardWidth - content.length()) / 2;
        int paddingRight = boardWidth - content.length() - paddingLeft;
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
