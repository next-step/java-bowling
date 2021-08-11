package bowling.view.util;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameScoreGrade;
import bowling.domain.score.Score;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;

public class FrameFormat {
    private static final char TURN_SCORE_DELIMITER = '|';
    private final Frame frame;

    public FrameFormat(final Frame frame) {
        this.frame = frame;
    }

    public String format() {
        FrameScoreGrade frameScore = frame.frameScoreGrade();

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
                    scoreFormat(finalFrame.bonusScore())
            );
        }
        return Text.STRIKE.toString();
    }

    private String toDisplaySpareScore(Frame frame) {
        if (frame instanceof FinalFrame && frame.isCompleted()) {
            FinalFrame finalFrame = (FinalFrame) frame;
            return Text.SPARE_BONUS.format(
                    toDisplayFirstTurnScore(frame),
                    scoreFormat(finalFrame.bonusScore())
            );
        }
        return Text.SPARE.format(
                toDisplayFirstTurnScore(frame)
        );
    }

    private String toDisplayFirstTurnScore(Frame frame) {
        return scoreFormat(frame.scores().first());
    }

    private String toDisplayScores(Frame frame) {
        List<String> displayScores = new ArrayList<>();
        frame.scores().forEach(
                iTurnScore ->
                        displayScores.add(
                                new ScoreFormat(iTurnScore).format()
                        )
        );

        return Strings.join(displayScores, TURN_SCORE_DELIMITER);
    }

    private String scoreFormat(Score score) {
        return new ScoreFormat(score).format();
    }

    private enum Text {
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

        @Override
        public String toString() {
            return str;
        }
    }
}
