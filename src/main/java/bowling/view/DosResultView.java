package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameScoreGrade;
import bowling.domain.player.Player;
import bowling.domain.ScoreBoard;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frames;
import bowling.domain.score.Score;
import bowling.domain.score.TurnScore;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class DosResultView implements ResultView {
    @Override
    public void printScoreBoard(final ScoreBoard scoreBoard) {
        printTitles();
        printAllPlayer(scoreBoard);
    }

    private void printTitles() {
        System.out.print(
                nameBoxedText(Text.NAME_TITLE)
        );
        IntStream.rangeClosed(1, Frames.MAX_FRAME_NUMBER)
                .forEach(index ->
                        System.out.print(
                                frameBoxedText(Text.FRAME_TITLE.format(index))
                        )
                );
        printEmptyLine();
    }

    private void printAllPlayer(final ScoreBoard scoreBoard) {
        scoreBoard.forEach(
                iEntry -> printPlayerLine(iEntry.getKey(), iEntry.getValue())
        );

        printEmptyLine();
    }

    private void printPlayerLine(Player player, Frames frames) {
        printPlayerName(player);
        frames.forEach(this::printFrame);
        printEmptyFrame(Frames.MAX_FRAME_NUMBER - frames.size());

        printEmptyLine();
    }

    private void printPlayerName(final Player player) {
        System.out.print(
                nameBoxedText(player.name().value())
        );
    }

    private void printFrame(final Frame frame) {
        System.out.print(
                frameBoxedText(
                        toDisplayFrameScore(frame)
                )
        );
    }

    private void printEmptyFrame(int size) {
        IntStream.range(0, size)
                .forEach(index ->
                        System.out.print(
                                frameBoxedText("")
                        )
                );
    }

    private String nameBoxedText(Object obj) {
        return Text.NAME_BOX.format(obj);
    }

    private String frameBoxedText(Object obj) {
        return Text.FRAME_BOX.format(obj);
    }

    private String toDisplayFrameScore(Frame frame) {
        FrameScoreGrade frameScore = frame.frameScore();

        if (frameScore == FrameScoreGrade.STRIKE) {
            return toDisplayStrikeScore(frame);
        }
        if (frameScore == FrameScoreGrade.SPARE) {
            return toDisplaySpareScore(frame);
        }
        if (frameScore == FrameScoreGrade.MISS) {
            return Text.MISS.toString();
        }
        return toDisplayScores(frame);
    }

    private String toDisplayStrikeScore(Frame frame) {
        if (frame instanceof FinalFrame && frame.isCompleted()) {
            FinalFrame finalFrame = (FinalFrame) frame;
            return Text.STRIKE_BONUS.format(
                    toDisplayScore(finalFrame.bonusScore())
            );
        }
        return Text.STRIKE.toString();
    }

    private String toDisplaySpareScore(Frame frame) {
        if (frame instanceof FinalFrame && frame.isCompleted()) {
            FinalFrame finalFrame = (FinalFrame) frame;
            return Text.SPARE_BONUS.format(
                    toDisplayFirstTurnScore(frame),
                    toDisplayScore(finalFrame.bonusScore())
            );
        }
        return Text.SPARE.format(
                toDisplayFirstTurnScore(frame)
        );
    }

    private String toDisplayFirstTurnScore(Frame frame) {
        return toDisplayScore(frame.scores().first());
    }

    private String toDisplayScores(Frame frame) {
        List<String> displayScores = new ArrayList<>();
        frame.scores().forEach(
                iTurnScore ->
                        displayScores.add(
                                toDisplayScore(iTurnScore)
                        )
        );

        return Strings.join(displayScores, Text.TURN_SCORE_DELIMITER.toChar());
    }

    private String toDisplayScore(Score score) {
        if (score instanceof TurnScore && ((TurnScore) score).isAllClear()) {
            return Text.STRIKE.toString();
        }
        return score.isZero() ? Text.MISS.toString() : String.valueOf(score.value());
    }

    private void printEmptyLine() {
        System.out.println();
    }

    private enum Text {
        NAME_BOX("| %4s |"),
        FRAME_BOX("  %-3s |"),
        NAME_TITLE("NAME"),
        FRAME_TITLE("%02d"),
        TURN_SCORE_DELIMITER("/"),
        MISS("-"),
        STRIKE("X"),
        STRIKE_BONUS("X/%s"),
        SPARE("%s|/"),
        SPARE_BONUS("%s|/|%s");

        private final String str;

        Text(String str) {
            this.str = str;
        }

        public String format(Object... objs) {
            return String.format(str, objs);
        }

        public char toChar() {
            return str.charAt(0);
        }

        @Override
        public String toString() {
            return str;
        }
    }
}
