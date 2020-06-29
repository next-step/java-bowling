package bowling.view;

import bowling.domain.game.MultiBowlingGame;
import bowling.domain.player.Player;

public class OutputView {
    private static final int ZERO = 0;
    private static final int TOTAL_FRAME_COUNTS = 9;

    private OutputView() {
    }

    public static void printDefaultScoreBoard(Player player) {
        System.out.println(ViewMessage.SCORE_BOARD_HEADER);
        System.out.printf(ViewMessage.DEFAULT_FRAME_SIGNATURE_ROW, player.getName());
        System.out.println(ViewMessage.DEFAULT_FRAME_SCORE_ROW);
    }

    public static void printTest(MultiBowlingGame multiBowlingGame) {
        System.out.println(ViewMessage.SCORE_BOARD_HEADER);

        /*
        FrameDto
            String playerName
            List<String> scoreSignatures
            List<Integer> scores
        */
    }

/*
    public static void printBowlingScoreBoard(Player player, Frames frames) {
        List<Frame> frameList = frames.getFrames();
        FrameScores frameScores = frames.getFrameScores();

        printDefaultLayout(player);
        frameList.forEach(OutputView::printVisibleFrameSignature);
        printBlankFrames(frameList.size());

        printVisibleFrameScores(frameScores);
        printBlankFrames(frameScores.getFrameScoreCounts());
        System.out.println();
    }

    private static void printDefaultLayout(Player player) {
        System.out.println(ViewMessage.SCORE_BOARD_HEADER);
        System.out.printf(ViewMessage.PLAYER_NAME, player.getName());
    }

    private static void printVisibleFrameSignature(Frame frame) {
        List<String> scoreSignatures = frame.getPitchScoreSignatures();
        StringBuilder stringBuilder = new StringBuilder();
        scoreSignatures.forEach(scoreSignature -> appendScoreSignature(scoreSignature, stringBuilder));
        String frameSignature = String.format(ViewMessage.FRAME_SIGNATURE_ROW_FORMAT, stringBuilder.toString());
        System.out.print(frameSignature);
    }

    private static void appendScoreSignature(String scoreSignature, StringBuilder stringBuilder) {
        if (stringBuilder.length() > ZERO) {
            stringBuilder.append(ViewMessage.VERTICAL_LINE);
        }
        stringBuilder.append(scoreSignature);
    }

    private static void printVisibleFrameScores(FrameScores frameScores) {
        List<Integer> scores = frameScores.getFrameScores();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ViewMessage.FRAME_SCORE_ROW_PREFIX);
        scores.forEach(score -> appendScore(score, stringBuilder));
        System.out.print(stringBuilder.toString());
    }

    private static void appendScore(int score, StringBuilder stringBuilder) {
        String frameScore = String.format(ViewMessage.FRAME_SCORE_ROW_FORMAT, score);
        stringBuilder.append(frameScore);
    }

    private static void printBlankFrames(int visibleFrameCounts) {
        int blankFrameCounts = TOTAL_FRAME_COUNTS - visibleFrameCounts;
        IntStream.rangeClosed(ZERO, blankFrameCounts)
                .forEach(i -> System.out.print(ViewMessage.BLANK_FRAME));
        System.out.println();
    }*/
}
