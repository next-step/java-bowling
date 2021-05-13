package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BonusCountTest {

    @DisplayName("BonusCount 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        BonusCount bonusCount = BonusCount.none();

        // then
        assertThat(bonusCount).isNotNull();
    }

    @DisplayName("BonusCount 인스턴스가 종료되었지에 대한 테스트")
    @Test
    void 종료() {
        // when
        BonusCount firstCount = BonusCount.spare();
        BonusCount secondCount = BonusCount.none();

        // then
        assertAll(
                () -> assertThat(firstCount.isFinish()).isFalse(),
                () -> assertThat(secondCount.isFinish()).isTrue()
        );

    }

    @DisplayName("BonusCount 인스턴스가 감소 되는지에 대한 테스트")
    @Test
    void 감소() {
        // when
        BonusCount bonusCount = BonusCount.spare();
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