package bowling.domain.state;

import bowling.domain.pin.DownedPins;
import bowling.domain.score.CalculableScore;
import bowling.domain.score.InCalculableScore;
import bowling.domain.score.InProgressScore;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static bowling.domain.Fixture.DOWNED_PINS_5;
import static org.assertj.core.api.Assertions.*;

@DisplayName("프레임 상태 표현을 위한 클래스 테스트")
class StateTest {

    @DisplayName("상태에 쓰러진 핀 정보를 넘기면 다음 상태를 넘긴다")
    @Test
    void downPins() {
        State someState = new SomeState();

        assertThat(someState.downPins(DOWNED_PINS_5)).isInstanceOf(State.class);
    }

    @DisplayName("상태에 쓰러진 null 핀 정보를 넘기면 예외를 발생 시킨다")
    @Test
    void downPinsException() {
        State someState = new SomeState();

        assertThatThrownBy(() -> someState.downPins(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("기본적으로 일반 상태는 종료상태가 아니다")
    @Test
    void isEnd() {
        State someState = new SomeState();

        assertThat(someState.isEnd()).isFalse();
    }

    @DisplayName("기본적으로 일반 상태는 Miss 상태가 아니다")
    @Test
    void isMiss() {
        State someState = new SomeState();

        assertThat(someState.isMiss()).isFalse();
    }

    @DisplayName("기본적으로 일반 상태는 Clean 상태가 아니다")
    @Test
    void isClean() {
        State someState = new SomeState();

        assertThat(someState.isClean()).isFalse();
    }

    @DisplayName("기본적으로 어떤 상태의 스코어 값은 계산 불가능 상태이다")
    @Test
    void score() {
        State someState = new SomeState();
        assertThat(someState.score()).isInstanceOf(InCalculableScore.class);
    }

    @DisplayName("스코어 값을 더할때 이미 스코어가 계산 가능하다면 그대로 리턴한다.")
    @Test
    void addScore() {
        SomeState someState = new SomeState();
        CalculableScore calculableScore = CalculableScore.from(10);

        assertThat(someState.addScore(calculableScore)).isEqualTo(calculableScore);
    }

    @DisplayName("스코어 값을 더할때 이미 스코어가 계산 불가능 하다면 추가적인 연산후 계산된 스코어를 반환한다")
    @Test
    void addBonusScore() {
        SomeState someState = new SomeState();
        InProgressScore inProgressScore = InProgressScore.ofSpare();

        assertThat(someState.addScore(inProgressScore)).isNotEqualTo(inProgressScore);
    }

    static class SomeState extends State {

        @Override
        protected State nextState(DownedPins downedPins) {
            return new SomeState();
        }

        @Override
        public List<Integer> getDownedPins() {
            return null;
        }

        @Override
        protected Score addBonusScore(Score score) {
            return InProgressScore.ofStrike();
        }
    }

}
