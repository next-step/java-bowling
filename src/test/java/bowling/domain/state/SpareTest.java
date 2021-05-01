package bowling.domain.state;

import bowling.domain.HitCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class SpareTest {

    @DisplayName("Spare 인스턴스 생성 테스트")
    @Test
    void 생성() {
        // given
        State ready = Ready.initialize();

        // when
        State firstBowl = ready.bowl(HitCount.valueOf(0));
        State spare = firstBowl.bowl(HitCount.valueOf(10));

        // then
        assertAll(
                () -> assertThat(spare).isNotNull(),
                () -> assertThat(spare).isInstanceOf(Spare.class)
        );
    }

    @DisplayName("Spare 인스턴스 생성시 들어오는 값들의 합이 10이 되는지 테스트")
    @Test
    void 검증() {

        // given
        int firstCount = 9;
        int secondCount = 0;

        // when
        assertThatThrownBy(()-> Spare.of(firstCount, secondCount))
                .isInstanceOf(InsufficientForMaxCountException.class)
                .hasMessage("( 9 )와 ( 0 )의 합인 ( 9 )는, 10을 충족하지 않습니다.");
    }
}