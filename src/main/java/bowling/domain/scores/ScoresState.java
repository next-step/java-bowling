package bowling.domain.scores;

abstract class ScoresState {
    abstract void accumulate(Scores scores, Score score);
}
