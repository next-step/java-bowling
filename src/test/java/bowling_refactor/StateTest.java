package bowling_refactor;

import bowling_refactor.domain.Pin;
import bowling_refactor.domain.Score;
import bowling_refactor.domain.state.State;
import bowling_refactor.domain.state.complete_state.Last;
import bowling_refactor.domain.state.complete_state.Miss;
import bowling_refactor.domain.state.complete_state.Spare;
import bowling_refactor.domain.state.complete_state.Strike;
import bowling_refactor.domain.state.middel_state.FirstBowl;
import bowling_refactor.domain.state.middel_state.Ready;
import bowling_refactor.domain.state.middel_state.SecondBowl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StateTest {

    private State ready;
    private State firstBowl;
    private State secondBowl;
    private State strike;
    private State spare;
    private State miss;
    private State last;

    @BeforeEach
    void setUp() {
        ready = new Ready();
        firstBowl = new FirstBowl(5);
        secondBowl = new SecondBowl(5, 5);
        strike = new Strike();
        spare = new Spare(5, 5);
        miss = new Miss(0, 1);
        last = new Last(5, 5, 5);
    }

    @Test
    @DisplayName("pitch가 10일 때 테스트")
    void after_pitch_next_state() {
        State ready = new Ready();
        assertThat(ready.bowl(10, false)).isEqualTo(new Strike());
    }

    @Test
    @DisplayName("pitch 2번 spare 테스트")
    void after_twice_spare() {
        State firstBowl = new FirstBowl(9);
        assertThat(firstBowl.bowl(1, false)).isEqualTo(new Spare(9,1));
    }

    @Test
    @DisplayName("pitch 2번 miss 테스트")
    void after_twice_miss() {
        State firstBowl = new FirstBowl(3);
        assertThat(firstBowl.bowl(1,false)).isEqualTo(new Miss(3,1));
    }

    @Test
    @DisplayName("State별 getScore 리턴 객체 확인")
    void getScore() {
        int totalScore = 0;
        int missSum = miss.getPins()
                .stream()
                .mapToInt(Pin::getPin)
                .sum();

        int lastSum = last.getPins()
                .stream()
                .mapToInt(Pin::getPin)
                .sum();

        assertThat((ready).getScore(totalScore)).isEqualTo(Score.ofNoneScore());
        assertThat((firstBowl).getScore(totalScore)).isEqualTo(Score.ofNoneScore());
        assertThat((strike).getScore(totalScore)).isEqualTo(Score.ofStrike(0));
        assertThat((spare).getScore(totalScore)).isEqualTo(Score.ofSpare(0));
        assertThat((miss).getScore(totalScore)).isEqualTo(Score.of(0, 1));
        assertThat((last).getScore(totalScore)).isEqualTo(Score.of(0, 15));
    }

    @Test
    @DisplayName("State 별 마지막인지 체크 테스트")
    void isFinal() {
        assertThat(ready.isEnd()).isFalse();
        assertThat(firstBowl.isEnd()).isFalse();
        assertThat(strike.isEnd()).isTrue();
        assertThat(spare.isEnd()).isTrue();
        assertThat(miss.isEnd()).isTrue();
        assertThat(last.isEnd()).isTrue();
    }

    @Test
    @DisplayName("addBonus 체크")
    void addBonus() {
        Score score = Score.ofStrike(0);
        assertThat(ready.addBonus(score)).isEqualTo(new Score(2, 10));
        assertThat(firstBowl.addBonus(score)).isEqualTo(new Score(1, 15));
        assertThat(secondBowl.addBonus(score)).isEqualTo(new Score(0, 20));
        assertThat(strike.addBonus(score)).isEqualTo(new Score(1, 20));
        assertThat(spare.addBonus(score)).isEqualTo(new Score(0, 20));
        assertThat(miss.addBonus(score)).isEqualTo(new Score(0, 11));
        assertThat(last.addBonus(score)).isEqualTo(new Score(0, 20));
    }

}
