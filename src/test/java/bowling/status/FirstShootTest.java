package bowling.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.frame.ConstShootScore.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class FirstShootTest {

    private Status firstFiveShoot;

    @BeforeEach
    void setUp() {
        firstFiveShoot = FirstShoot.from(FIVE_SCORE);
    }

    @Test
    @DisplayName("첫 투구 이후 두 번째 투구를 앞두고 있는 상태를 생성")
    void create() {
        assertAll(
                () -> assertThat(firstFiveShoot).isEqualTo(FirstShoot.from(FIVE_SCORE)),
                () -> assertThat(firstFiveShoot.isEnd()).isFalse()
        );
    }

    @Test
    @DisplayName("첫 투구의 값이 Strike 인 10이 들어온다면 생성할 수 없습니다")
    void invalidFirstShootCondition() {
        assertThatThrownBy(() -> FirstShoot.from(STRIKE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("첫 번째, 두 번째 투구의 합이 10인 경우 Spare 상태로 전환되어야 함")
    void firstShootToSpareStatus() {
        Status spareStatus = firstFiveShoot.shoot(FIVE_SCORE);
        assertAll(
                () -> assertThat(spareStatus.getClass()).hasSameClassAs(Spare.class),
                () -> assertThat(spareStatus.isEnd()).isTrue()
        );
    }

    @Test
    @DisplayName("첫 번째, 두 번째 투구의 합이 10이 아닌 경우 Miss 상태로 전환되어야 함")
    void firstShootToMissStatus() {
        Status missStatus = firstFiveShoot.shoot(ZERO_SCORE);
        assertAll(
                () -> assertThat(missStatus.getClass()).hasSameClassAs(Miss.class),
                () -> assertThat(missStatus.isEnd()).isTrue()
        );
    }
}