package bowling.domain.score;

import bowling.exception.InvalidBonusCountSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

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
        int firstRemain = 1;
        int secondRemain = 0;

        // when
        BonusCount firstCount = BonusCount.valueOf(firstRemain);
        BonusCount secondCount = BonusCount.valueOf(secondRemain);

        // then
        assertAll(
                () -> assertThat(firstCount.isFinish()).isFalse(),
                () -> assertThat(secondCount.isFinish()).isTrue()
        );

    }

    @DisplayName("BonusCount 인스턴스가 감소 되는지에 대한 테스트")
    @Test
    void 감소() {
        // given
        int remain = 1;

        // when
        BonusCount bonusCount = BonusCount.valueOf(remain);
        BonusCount decreasedCount = bonusCount.decrease();

        // then
        assertThat(decreasedCount.isFinish()).isTrue();

    }

    @DisplayName("BonusCount 인스턴스가 보너스 없는 인스턴스를 반환하는지에 대한 테스트")
    @Test
    void 반환_보너스_없음() {
        // when
        BonusCount bonusCount = BonusCount.none();

        // then
        assertThat(bonusCount.isFinish()).isTrue();
    }

    @DisplayName("BonusCount 인스턴스가 보너스가 하나 있는 인스턴스를 반환하는지에 대한 테스트")
    @Test
    void 반환_보너스_하나() {
        // when
        BonusCount bonusCount = BonusCount.spare();

        // then
        assertAll(
                () -> assertThat(bonusCount.isFinish()).isFalse(),
                () -> assertThat(bonusCount.decrease().isFinish()).isTrue()
        );

    }

    @DisplayName("BonusCount 인스턴스가 보너스가 두개 있는 인스턴스를 반환하는지에 대한 테스트")
    @Test
    void 반환_보너스_둘() {
        // when
        BonusCount bonusCount = BonusCount.strike();

        // then
        assertAll(
                () -> assertThat(bonusCount.isFinish()).isFalse(),
                () -> assertThat(bonusCount.decrease().isFinish()).isFalse(),
                () -> assertThat(bonusCount.decrease().decrease().isFinish()).isTrue()
        );

    }


}