package bowling.domain.state;

import bowling.domain.frame.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class StateCreatorTest {

    @Test
    @DisplayName("0보다 작은 점수 입력시 Exception")
    void createStateFailTest() {
        assertThatThrownBy(() -> StateCreator.create(Point.inputPoint(-1)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("10보다 큰 점수 입력시 Exception")
    void createStateFailTest_10보다_큰경우() {
        assertThatThrownBy(() -> StateCreator.create(Point.inputPoint(11)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("정상 점수 생성")
    void createStateTest() {
        State state = StateCreator.create(Point.inputPoint(5));
        assertThat(state.getScore()).isEqualTo("5");
    }

    @Test
    void createStateStrikeTest() {
        State state = StateCreator.create(Point.inputPoint(10));
        assertThat(state.getScore()).isEqualTo("X");
    }

    @Test
    void createScoreGutterTest() {
        State state = StateCreator.create(Point.inputPoint(0));
        assertThat(state.getScore()).isEqualTo("-");
    }

    @Test
    @DisplayName("첫번째 점수의 합과 두번째 점수의 합이 10점인 경우 Spare")
    void createScoreSpareTest() {
        State firstState = StateCreator.create(Point.inputPoint(8));
        State secondState = firstState.nextScore(Point.inputPoint(2));
        assertThat(secondState.getScore()).isEqualTo("/");
    }

    @Test
    @DisplayName("두번재 투구에서도 모든 핀이 쓰러지지 않은 경우 MISS")
    void createScoreMissTest() {
        State firstState = StateCreator.create(Point.inputPoint(5));
        State secondState = firstState.nextScore(Point.inputPoint(0));
        assertThat(secondState.getScore()).isEqualTo("-");
    }

}
