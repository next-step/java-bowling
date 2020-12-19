package bowling.model.state;

import bowling.model.Pins;
import bowling.model.Score;
import bowling.model.frame.FinalFrame;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class States {
    private static final String DELIMITER = "|";

    private final LinkedList<State> states = new LinkedList<>();

    public State bowling(Pins fallenPins) {
        State bowlingResult = nextState(fallenPins);

        states.add(bowlingResult);

        return bowlingResult;
    }

    public Score addScoreUntilPossible(Score score){
        if(states.size() == 1){
            return calculate(score);
        }

        List<State> finishedStates = states.stream()
                .filter(State::isFinished)
                .collect(Collectors.toList());

        for(int i = 0, limit = finishedStates.size(); i < limit && !score.canCalculate(); i++){
            State previousState = finishedStates.get(i);
            score = previousState.calculateScore(score);
        }

        return score;
    }
    private State nextState(Pins fallenPins){
        if(states.isEmpty()){
            return Start.bowling(fallenPins);
        }
        return last().bowling(fallenPins);
    }

    public void changeLastStateToBonus(Pins fallenPins) {
        states.add(Start.bowling(fallenPins));
    }

    public State last() {
        return states.getLast();
    }

    public boolean isFinished() {
        return !states.isEmpty() && last().isFinished();
    }

    public Score calculate(Score score) {
        return last().calculateScore(score);
    }

    public int sumScore() {
        int result = states.stream().filter(State::isFinished)
                .map(state -> state.score().getScore())
                .reduce(Integer::sum)
                .orElse(0);

        return shouldAddLastOne() ? result + lastScore() : result;
    }

    private boolean shouldAddLastOne() {
        return states.size() == FinalFrame.MAX_BONUS_COUNT && !states.get(FinalFrame.MAX_BONUS_COUNT - 1).isFinished();
    }

    private int lastScore() {
        return states.getLast().score().getScore();
    }

    @Override
    public String toString() {
        return states.stream()
                .map(State::toString)
                .collect(Collectors.joining(DELIMITER));
    }

    public boolean isEmpty() {
        return states.isEmpty();
    }

    public int size() {
        return states.size();
    }


    public boolean canLastCalculate(){
        return states.getLast()
                .score()
                .canCalculate();
    }
}
