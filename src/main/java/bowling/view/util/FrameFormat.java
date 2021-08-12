package bowling.view.util;

import bowling.domain.frame.Frame;
import bowling.domain.score.Score;
import bowling.domain.score.TurnScores;
import bowling.domain.score.framescore.*;
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
        if (frameScore instanceof BonusSpare) {
            TurnScores turnScores = frame.turnScores();
            return Text.BONUS_SPARE.format(turnScores.first().value(), turnScores.last().value());
        }
        if (frameScore.isEmpty()) {
            return EMPTY;
        }

        return toDisplayScores(frame);
    }

    private String toDisplayFirstTurnScore(Frame frame) {
        return scoreFormat(frame.turnScores().first());
    }

    private String scoreFormat(Score score) {
        return new ScoreFormat(score).format();
    }

    private String toDisplayScores(Frame frame) {
        List<String> displayScores = new ArrayList<>();
        frame.turnScores().forEach(
                iTurnScore ->
                        displayScores.add(
                                scoreFormat(iTurnScore)
                        )
        );

        return Strings.join(displayScores, TURN_SCORE_DELIMITER);
    }


    private enum Text {
        MISS("-"),
        STRIKE("X"),
        SPARE("%s|/"),
        BONUS_SPARE("%d|/|%d");

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
