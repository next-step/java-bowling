package bowling.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.frame.ConstShootScore.FIVE_SCORE;
import static bowling.frame.ConstShootScore.STRIKE;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class SpareTest {

    @Test
    @DisplayName("Spare 의 정적 팩토리 메서드로 생성한 Spare 와 FirstShoot 에서 Spare 처리를 통해 생성된 Spare 는 같아야함")
    void create() {
        Status firstShoot = FirstShoot.from(FIVE_SCORE);
        Status spareStatus = firstShoot.shoot(FIVE_SCORE);

        Status spare = Spare.from(FIVE_SCORE);
        assertAll(
                () -> assertThat(spare).isEqualTo(spareStatus),
                () -> assertThat(spare.isEnd()).isTrue()
        );
    }

    @Test
    @DisplayName("Spare 가 가지고 있는 firstShootScore 의 값은 Strike 가 아니여야 합니다.")
    void invalidSpareStatus() {
        assertThatThrownBy(() -> Spare.from(STRIKE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Spare 는 더 투구할 수 없습니다")
    void spareCantShoot() {
        assertThatThrownBy(() -> Spare.from(FIVE_SCORE).shoot(FIVE_SCORE))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}