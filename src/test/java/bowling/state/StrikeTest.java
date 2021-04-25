package bowling.state;

import bowling.domain.exception.CannotBowlException;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StrikeTest {
    private Strike strike;

    @BeforeEach
    void setUp() {
        strike = Strike.create();
    }

    @Test
    @DisplayName("턴이 끝났는지 확인 테스트")
    void isFinishedTest() {
        assertThat(strike.isFinished()).isTrue();
    }

    @Test
    @DisplayName("bowl 예외 테스트")
    void bowlToSpareTest() {
        assertThatThrownBy(() -> strike.bowl(3))
                .isInstanceOf(CannotBowlException.class)
                .hasMessage("더 이상 투구할 수 없습니다.");
    }
}
