package bowling.domain.scores;

import bowling.domain.Score;

interface ScoresState {
    void accumulate(ScoresContext context, Score score);
}
