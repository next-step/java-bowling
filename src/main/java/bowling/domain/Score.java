package bowling.domain;

import static bowling.util.Const.MAX_PIN;
import static bowling.util.Const.NULL;

public class Score {
    private final ScoreType scoreType;
    private final Hit hit;

    public Score() {
        this.hit =  new Hit(NULL,NULL);
        this.scoreType = ScoreType.NULL;
    }

    public Score(int first) {
        this.hit = new Hit(first);
        this.scoreType = ScoreType.of(first);
    }

    public Score(int first, int second) {
        this.hit = new Hit(first, second);
        this.scoreType = ScoreType.of(first, second);
    }

    public static Score play(Score prevScore) {
        if (prevScore.isPresent()) {
            int hit = Player.pitch(prevScore.hit.remainingPin());
            return new Score(prevScore.hit.first(), hit);
        }
        int hit = Player.pitch(MAX_PIN);
        return new Score(hit);
    }

    private boolean isPresent() {
        return this.scoreType != ScoreType.NULL;
    }


    public static String scoreBoard(Score score) {
        if (!score.isPresent()) {
            return format(" ");
        }
        if (score.scoreType == ScoreType.STRIKE) {
            return format("X");
        }
        if (score.scoreType == ScoreType.SECOND) {
            return format(score.hit.firstStr());
        }
        if (score.scoreType == ScoreType.GUTTER) {
            return format("-|-");
        }
        if (score.scoreType == ScoreType.MISS) {
            return format(score.hit.firstStr() + "|" + score.hit.secondStr());
        }
        if (score.scoreType == ScoreType.SPARE) {
            return format(score.hit.first() + "|/");
        }
        throw new RuntimeException("unreachable " + score);
    }

    public static String format(String string) {
        return String.format("%-4s", string);
    }

    public boolean done() {
        return this.scoreType != ScoreType.SECOND;
    }
}
