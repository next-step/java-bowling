package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-19 11:57
 */
public class InitStateTest {
    @DisplayName("초기 상태값 설정")
    @Test
    void 상태값_초기화_생성() {
        State state = InitState.of();
        assertAll(
                () -> assertThat(state.isOver()).isFalse(),
                () -> assertThat(state.printState()).isEqualTo("| ")
        );
    }
}
