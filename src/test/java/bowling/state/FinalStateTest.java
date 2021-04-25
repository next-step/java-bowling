package bowling.state;

import bowling.domain.exception.CannotBowlException;
import bowling.domain.exception.PinsCountException;
import bowling.domain.state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FinalStateTest {
    private State finalState;

    @BeforeEach
    void setUp() {
        finalState = FinalState.create();
    }

    @Test
    @DisplayName("Miss 발생시 종료 확인 테스트")
    void isFinishedWhenMissTest() {
        finalState = finalState.bowl(3);
        finalState = finalState.bowl(5);
        assertThat(finalState.isFinished()).isTrue();
    }

    @Test
    @DisplayName("Spare 발생시 종료 확인 테스트")
    void isFinishedWhenSpareTest() {
        finalState = finalState.bowl(6);
        finalState = finalState.bowl(4);
        assertThat(finalState.isFinished()).isFalse();
    }

    @Test
    @DisplayName("Spare-Continue 발생시 종료 확인 테스트")
    void isFinishedWhenSpareContinueTest() {
        finalState = finalState.bowl(6);
        finalState = finalState.bowl(4);
        finalState = finalState.bowl(5);
        assertThat(finalState.isFinished()).isTrue();
    }

    @Test
    @DisplayName("Strike 발생시 종료 확인 테스트")
    void isFinishedWhenStrikeTest() {
        finalState = finalState.bowl(10);
        assertThat(finalState.isFinished()).isFalse();
    }

    @Test
    @DisplayName("Double 발생시 종료 확인 테스트")
    void isFinishedWhenDoubleTest() {
        finalState = finalState.bowl(10);
        finalState = finalState.bowl(10);
        assertThat(finalState.isFinished()).isFalse();
    }

    @Test
    @DisplayName("Turkey 발생시 종료 확인 테스트")
    void isFinishedWhenTurkeyTest() {
        finalState = finalState.bowl(10);
        finalState = finalState.bowl(10);
        finalState = finalState.bowl(10);
        assertThat(finalState.isFinished()).isTrue();
    }

    @Test
    @DisplayName("bowl 예외 테스트")
    void cannotBowlExceptionTest() {
        finalState = finalState.bowl(3);
        finalState = finalState.bowl(5);
        assertThatThrownBy(() -> finalState.bowl(3))
                .isInstanceOf(CannotBowlException.class)
                .hasMessage("더 이상 투구할 수 없습니다.");
    }

    @ParameterizedTest(name = "넘어뜨린 핀 개수 예외 테스트")
    @ValueSource(ints = {-1,11})
    void pinsCountExceptionTest(int pitch) {
        assertThatThrownBy(() -> finalState.bowl(pitch))
                .isInstanceOf(PinsCountException.class)
                .hasMessage("넘어뜨린 핀수는 0에서 10 사이의 정수여야 합니다.");
    }

    @ParameterizedTest(name = "두번째 투구에서 넘어뜨린 핀 개수 예외 테스트")
    @ValueSource(ints = {6,7})
    void pinsCountExceptionSecondPitchTest(int pitch) {
        finalState.bowl(5);
        assertThatThrownBy(() -> finalState.bowl(pitch))
                .isInstanceOf(PinsCountException.class)
                .hasMessage("넘어뜨린 핀수는 0에서 10 사이의 정수여야 합니다.");
    }
}
