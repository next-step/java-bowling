package bowling.domain.state;

import bowling.domain.Score;

public class Strike implements State {

    @Override
    public State bowl(int countOfPin) {
        throw new IllegalStateException("스트라이크 이 후에는 볼을 더 던질 수 없음");
    }

    @Override
    public Score addBonus(Score previousScore) {
        return Score.of(this.score(), previousScore);
    }

    @Override
    public Score score() {
        return Score.ofStrike();
    }

}
