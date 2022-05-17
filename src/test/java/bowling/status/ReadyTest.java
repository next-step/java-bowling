package bowling.status;

import bowling.frame.ShootScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ReadyTest {

    private static final int STRIKE = 10;

    @Test
    @DisplayName("투구 되지 않은 Frame 의 준비 상태를 나타내는 Ready 클래스 생성")
    void create() {
        Ready ready = Ready.create();
        assertThat(ready.isEnd()).isFalse();
    }

    @Test
    @DisplayName("Ready 상태의 프레임에서 Strike 가 아닌 첫 투구를 진행하면 FirstShoot 상태를 반환해줌")
    void firstShootNotStrikeStatus() {
        Ready ready = Ready.create();
        Status firstShootStatus = ready.shoot(ShootScore.from(5));
        assertThat(firstShootStatus.getClass()).hasSameClassAs(FirstShoot.class);
        assertThat(firstShootStatus.isEnd()).isFalse();
    }

    @Test
    @DisplayName("Ready 상태의 프레임에서 Strike 를 입력하면 Strike 상태를 반환해줌")
    void firstShootIsStrike() {
        Ready ready = Ready.create();
        Status strikeStatus = ready.shoot(ShootScore.from(STRIKE));
        assertThat(strikeStatus.getClass()).hasSameClassAs(Strike.class);
        assertThat(strikeStatus.isEnd()).isTrue();
    }
}