package bowling.view.util;

import bowling.domain.frame.Frame;
import bowling.domain.score.framescore.*;
import bowling.domain.score.Score;
import bowling.domain.score.TurnScores;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;

public class FrameFormat {
    private static final String EMPTY = "";
    private static final char TURN_SCORE_DELIMITER = '|';

    private final Frame frame;

    public FrameFormat(final Frame frame) {
        this.frame = frame;
    }

    public String format() {
        FrameScore frameScore = frame.frameScore();

        if (frameScore instanceof Strike) {
            return Text.STRIKE.toString();
        }
        if (frameScore instanceof Spare) {
            return Text.SPARE.format(toDisplayFirstTurnScore(frame));
        }
        if (frameScore instanceof Miss) {
            return Text.MISS.toString();
        }
        if (frameScore.isEmpty()) {
            return EMPTY;
        }
        if (frameScore instanceof BonusSpare) {
            TurnScores turnScores = frame.turnScores();
            return Text.SPARE_BONUS.format(turnScores.first().value(), turnScores.last().value());
        }

        return toDisplayScores(frame);
    }

    private String toDisplayFirstTurnScore(Frame frame) {
        return scoreFormat(frame.turnScores().first());
    }

    private String toDisplayScores(Frame frame) {
        List<String> displayScores = new ArrayList<>();
        frame.turnScores().forEach(
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
        SPARE("%s|/"),
        SPARE_BONUS("%d|/|%d");

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
