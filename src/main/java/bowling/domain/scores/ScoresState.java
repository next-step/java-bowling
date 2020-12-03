package bowling.domain.scores;

abstract class ScoresState {
    abstract void accumulate(Scores context, Score score);
}
