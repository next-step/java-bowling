package bowling.status;

import bowling.frame.ShootScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SpareTest {

    private static final int STRIKE = 10;
    private static final int FIVE = 5;

    @Test
    @DisplayName("Spare 의 정적 팩토리 메서드로 생성한 Spare 와 FirstShoot 에서 Spare 처리를 통해 생성된 Spare 는 같아야함")
    void create() {
        FirstShoot firstShoot = FirstShoot.from(ShootScore.from(FIVE));
        Status spareStatus = firstShoot.shoot(ShootScore.from(FIVE));

        Spare spare = Spare.from(ShootScore.from(FIVE));
        assertThat(spare).isEqualTo(spareStatus);
        assertThat(spare.isEnd()).isTrue();
    }

    @Test
    @DisplayName("Spare 가 가지고 있는 firstShootScore 의 값은 Strike 가 아니여야 합니다.")
    void invalidSpareStatus() {
        assertThatThrownBy(() -> Spare.from(ShootScore.from(STRIKE)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}