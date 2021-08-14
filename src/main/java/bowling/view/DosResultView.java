package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.framescore.FrameScore;
import bowling.domain.player.Player;
import bowling.domain.score.Score;
import bowling.domain.scoreboard.ScoreBoard;
import bowling.view.util.FrameFormat;
import bowling.view.util.ScoreFormat;

import java.util.Map;
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
        for (Map.Entry<Player, Frames> iEntry : scoreBoard) {
            printPlayerFrameLine(iEntry.getKey(), iEntry.getValue());
            printPlayerFrameScoreLine(iEntry.getValue());
        }

        printEmptyLine();
    }

    private void printPlayerFrameLine(Player player, Frames frames) {
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
                        new FrameFormat(frame.frameScore()).format()
                )
        );
    }

    private void printEmptyFrame(int size) {
        IntStream.range(0, size)
                .forEach(index ->
                        System.out.print(
                                frameBoxedText(Text.EMPTY)
                        )
                );
    }

    private void printPlayerFrameScoreLine(final Frames frames) {
        System.out.print(nameBoxedText(Text.EMPTY));

        Score cumulativeScore = new Score(0);

        for (Frame iFrame : frames) {
            cumulativeScore = cumulativeScore.sum(
                    printFrameScore(cumulativeScore, iFrame)
            );
        }
        printEmptyFrame(Frames.MAX_FRAME_NUMBER - frames.size());

        printEmptyLine();
    }

    private Score printFrameScore(Score cumulativeScore, final Frame frame) {
        FrameScore iFrameScore = frame.frameScore();

        if (!iFrameScore.isShowScoreValue()) {
            System.out.print(
                    frameBoxedText(Text.EMPTY)
            );
            return Score.empty();
        }

        Score iFrameScoreSumValue = iFrameScore.turnScores().sum();
        cumulativeScore = cumulativeScore.sum(
                iFrameScoreSumValue
        );
        System.out.print(
                frameBoxedText(
                        new ScoreFormat(cumulativeScore).format()
                )
        );
        return iFrameScoreSumValue;
    }

    private String nameBoxedText(Object obj) {
        return Text.NAME_BOX.format(obj);
    }

    private String frameBoxedText(Object obj) {
        return Text.FRAME_BOX.format(obj);
    }

    private void printEmptyLine() {
        System.out.println();
    }

    private enum Text {
        EMPTY(""),
        NAME_TITLE("NAME"),
        FRAME_TITLE("%02d"),
        NAME_BOX("| %4s |"),
        FRAME_BOX("  %-3s |");

        private final String str;

        Text(String str) {
            this.str = str;
        }

        public String format(Object... objs) {
            return String.format(str, objs);
        }

        @Override
        public String toString() {
            return str;
        }
    }
}
