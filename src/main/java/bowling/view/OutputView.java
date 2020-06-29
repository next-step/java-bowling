package bowling.view;

import bowling.domain.dto.BowlingGameDto;
import bowling.domain.dto.ScoreSignaturesDto;

import java.util.List;
import java.util.stream.IntStream;

public class OutputView {
    private static final int ZERO = 0;
    private static final int TOTAL_FRAME_COUNTS = 9;

    private OutputView() {
    }

    public static void printDefaultScoreBoard(List<BowlingGameDto> bowlingGameDtos) {
        System.out.println(ViewMessage.SCORE_BOARD_HEADER);
        for (BowlingGameDto bowlingGameDto : bowlingGameDtos) {
            System.out.printf(ViewMessage.DEFAULT_FRAME_SIGNATURE_ROW, bowlingGameDto.getPlayerName());
            System.out.println(ViewMessage.DEFAULT_FRAME_SCORE_ROW);
        }
        System.out.println();
    }

    public static void printBowlingScoreBoard(List<BowlingGameDto> bowlingGameDtos) {
        System.out.println(ViewMessage.SCORE_BOARD_HEADER);

        for (BowlingGameDto bowlingGameDto : bowlingGameDtos) {
            System.out.printf(ViewMessage.PLAYER_NAME, bowlingGameDto.getPlayerName());
            List<ScoreSignaturesDto> scoreSignaturesDtos = bowlingGameDto.getScoreSignatures();
            scoreSignaturesDtos.forEach(OutputView::printVisibleFrameSignature);
            printBlankFrames(scoreSignaturesDtos.size());

            List<Integer> scores = bowlingGameDto.getScores();

            printVisibleFrameScores(scores);
            printBlankFrames(scores.size());
        }
        System.out.println();

    }

    private static void printVisibleFrameSignature(ScoreSignaturesDto scoreSignaturesDto) {
        List<String> scoreSignatures = scoreSignaturesDto.getScoreSignatures();
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

    private static void printVisibleFrameScores(List<Integer> scores) {
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
    }
}
