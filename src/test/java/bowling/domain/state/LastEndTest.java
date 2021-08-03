package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("마지막 프레임의 끝을 표현한 상태 테스트")
class LastEndTest {

    @DisplayName("LastEnd 는 복잡 상태를 가지고 초기화 한다")
    @Test
    void init() {
        ComplexState complexState = ComplexState.init();

        assertThat(LastEnd.from(complexState)).isInstanceOf(LastEnd.class);
    }

    @DisplayName("모든 투구가 끝난 상태인 lastEnd 상태는 플레이 순서를 넘기는 turn over 상태이다")
    @Test
    void turnOver() {
        ComplexState complexState = ComplexState.init();
        LastEnd lastEnd = LastEnd.from(complexState);

        assertThat(lastEnd.isTurnOver()).isTrue();
    }

}
