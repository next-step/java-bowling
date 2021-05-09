package bowling.domain;

import bowling.exception.InvalidBonusCountSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BonusCountTest {

    @DisplayName("BonusCount 인스턴스 생성 여부 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void 생성(int remain) {
        // when
        BonusCount bonusCount = BonusCount.valueOf(remain);

        // then
        assertThat(bonusCount).isNotNull();
    }

    @DisplayName("BonusCount 인스턴스에 범위를 벗어난 값 입력시 예외처리 테스트")
    @Test
    void 검증_범위를_벗어난_값() {
        // given
        int negativeValue = -1;
        int overSizeValue = 3;

        // when and then
        assertThatThrownBy(() -> BonusCount.valueOf(negativeValue))
                .isInstanceOf(InvalidBonusCountSizeException.class)
                .hasMessage("알맞는 보너스 횟수에 대한 크기가 아닙니다.");


        assertThatThrownBy(() -> BonusCount.valueOf(overSizeValue))
                .isInstanceOf(InvalidBonusCountSizeException.class)
                .hasMessage("알맞는 보너스 횟수에 대한 크기가 아닙니다.");
    }

    @DisplayName("BonusCount 인스턴스가 종료되었지에 대한 테스트")
    @Test
    void 종료() {
        // given
        int remain = 0;

        // when
        BonusCount bonusCount = BonusCount.valueOf(remain);

        // then
        assertThat(bonusCount.isFinish()).isTrue();
    }
}