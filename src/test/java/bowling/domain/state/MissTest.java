package bowling.domain.state;

import bowling.exception.InsufficientMissCountException;
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
        // given
        int firstCount = 0;
        int secondCount = 10;

        // when and then
        assertThatThrownBy(()->Miss.from(firstCount, secondCount))
                .isInstanceOf(InsufficientMissCountException.class)
                .hasMessage("( "+firstCount+" )와 ( "+secondCount+" )의 합인 " +
                        "( "+Math.addExact(firstCount, secondCount)+" )는, 9이하 값을 충족하지 않습니다.");

    }
}