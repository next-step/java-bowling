package bowling.view;

import bowling.domain.player.Player;

public class OutputView {

    private OutputView() {
    }

    public static void printDefaultScoreBoard(Player player) {
        System.out.println(ViewMessages.SCORE_BOARD_HEADER);
        System.out.printf(ViewMessages.SCORE_BOARD_DEFAULT, player.getName());
    }
/*
    public static void printBowlingScoreBoard(Frames frames, Player player) {
        System.out.println(ViewMessages.SCORE_BOARD_HEADER);
        System.out.printf(ViewMessages.SCORE_BOARD_NAME, player.getName());
        List<Frame> frameList = frames.getFrames();
        StringBuilder stringBuilder = new StringBuilder();

        frameList.forEach(frame -> printFrame(frame, stringBuilder));
        appendBlank(frames, stringBuilder);
        System.out.println(stringBuilder.toString());
        System.out.println();
    }

    private static void printFrame(Frame frame, StringBuilder stringBuilder) {
        stringBuilder.append("  ");
        String score = BowlingScoreParser.parseScore(frame.getPitches());
        if (score.length() == 1) {
            score += "  ";
        }
        stringBuilder.append(score);
        stringBuilder.append(" |");
    }

    private static void appendBlank(Frames frames, StringBuilder stringBuilder) {
        for (int i = 0; i < 10 - frames.getCurrentFrameIndex(); i++) {
            stringBuilder.append("      |");
        }
    }*/
}
