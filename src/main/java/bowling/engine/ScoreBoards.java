package bowling.engine;

import bowling.engine.collection.FirstClassList;

public interface ScoreBoards extends FirstClassList<ScoreBoard> {
    ScoreBoard first();
    ScoreBoard next(ScoreBoard previous);
    boolean isEnded();
}
