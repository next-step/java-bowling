package bowling.view;

import bowling.domain.Player;
import bowling.domain.ScoreBoard;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.TurnScore;
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DosResultView implements ResultView {
    @Override
    public void printException(final Exception e) {
        System.out.println(e.getMessage());
    }

    @Override
    public void printScoreBoard(final ScoreBoard scoreBoard) {
        printTitles(scoreBoard);
        printAllPlayer(scoreBoard);
    }

    private void printTitles(final ScoreBoard scoreBoard) {
        System.out.print(
                nameBoxedText(Text.NAME_TITLE)
        );
        IntStream.rangeClosed(1, scoreBoard.framesSize())
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

        printEmptyLine();
    }

    private void printPlayerName(final Player player) {
        System.out.print(
                nameBoxedText(player.name())
        );
    }

    private void printFrame(final Frame frame) {
        System.out.print(
                frameBoxedText(
                        toDisplayFrameScore(frame)
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
        if (frame.isStrike()) {
            return toDisplayStrikeScore(frame);
        }
        if (frame.isSpare()) {
            return toDisplaySpareScore(frame);
        }
        if (frame.isMiss()) {
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
        return toDisplayScore(frame.scores().get(0));
    }

    private String toDisplayScores(Frame frame) {
        List<String> displayScores =
                frame.scores().stream()
                        .map(this::toDisplayScore)
                        .collect(Collectors.toList());

        return Strings.join(displayScores, Text.TURN_SCORE_DELIMITER.toChar());
    }

    private String toDisplayScore(TurnScore score) {
        if (score.isMax()) {
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
