package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MissTest {

    @DisplayName("Miss 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        Miss miss = Miss.from(0, 9);

        // then
        assertThat(miss).isNotNull();
    }

    @DisplayName("Miss 인스턴스에 알맞은 값이 주입되었는지 테스트")
    @Test
    void 검증() {
        // when
        assertThatThrownBy(()->Miss.from(0, 10))
                .isInstanceOf(InsufficientMissException.class);
                .hasMessage("( %s )와 ( %s )의 합인 ( %s )는, 9이하의 값이 아닙니다.");

    }
}