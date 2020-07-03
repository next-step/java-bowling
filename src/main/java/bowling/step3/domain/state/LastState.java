package bowling.step3.domain.state;

import bowling.step3.domain.score.Score;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class LastState {
    private static final String DELIMITER = "|";
    private static final int FIRST_TRY = 1;

    private LinkedList<State> states = new LinkedList<>();

    public LastState() {
        states.add(new Ready());
    }

    public LastState pitch(Pins pins){
        if(!isFinished() && !isFirstStrike()){
            states.add(states.pop().pitch(pins));
            return this;
        }
        states.add(new Ready().pitch(pins));
        return this;
    }

    private boolean isFirstStrike() {
        return states.size() > FIRST_TRY && states.getFirst() instanceof Strike;
    }

    public boolean isMissState(){
        return nowState() instanceof Miss;
    }

    private State nowState() {
        return states.getLast();
    }

    private boolean isFinished(){
        return nowState().isFinish();
    }

    public int getScore(){
        return states.stream()
                     .map(State::getScore)
                     .mapToInt(Score::getScore)
                     .sum();
    }

    public Score addAdditionalScore(Score prevScore) {
        Score nowScore = prevScore;
        for (int i = 0; i < states.size(); i++) {
            Score score = states.get(i).addAdditionalScore(nowScore);
            if (score.isFinishedScore()) {
                return score;
            }
            nowScore = score;
        }
        return Score.readyScore();
    }

    public String disPlay(){
        return states.stream()
                     .map(State::display)
                     .collect(Collectors.joining(DELIMITER));
    }
}
