package bowling.domain;

import bowling.domain.state.PinCount;
import bowling.exception.InputNumberOutOfBoundsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class HitCountTest {

    @DisplayName("HitCount 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // given
        int count = 10;

        // when
        PinCount hitCount = PinCount.valueOf(count);

        // then
        assertThat(hitCount).isNotNull();
    }

    @DisplayName("HitCount 인스턴스에 범위를 벗어난 값 입력시 예외처리 여부 테스트")
    @Test
    void 검증() {
        // given
        int firstCount = 11;
        int secondCount = -1;

        // when
        assertThatThrownBy(() -> PinCount.valueOf(firstCount))
                .isInstanceOf(InputNumberOutOfBoundsException.class)
                .hasMessage("맞은 갯수 ( " + firstCount + " ) 는 사용할 수 없는 갯수 입니다.");

        // when
        assertThatThrownBy(() -> PinCount.valueOf(secondCount))
                .isInstanceOf(InputNumberOutOfBoundsException.class)
                .hasMessage("맞은 갯수 ( " + secondCount + " ) 는 사용할 수 없는 갯수 입니다.");

    }

    @DisplayName("HitCount 인스턴스가 소유한 값 반환 테스트")
    @Test
    void 반환() {
        // given
        int expected = 10;

        // when
        PinCount hitCount = PinCount.valueOf(expected);
        int actual = hitCount.count();

        // then
        assertThat(actual).isEqualTo(expected);

    }

    @DisplayName("HitCount 인스턴스가 값이 동일할 경우 같은 인스턴스 반환 테스트")
    @Test
    void 비교_동일한_값() {
        // given
        int count = 10;

        // when
        PinCount firstHitCount = PinCount.valueOf(count);
        PinCount secondHitCount = PinCount.valueOf(count);

        // then
        assertAll(
                () -> assertThat(firstHitCount).isEqualTo(secondHitCount),
                () -> assertThat(firstHitCount).isSameAs(secondHitCount)
        );

    }
}