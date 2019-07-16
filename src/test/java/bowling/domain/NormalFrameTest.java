package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-17 00:48
 */
public class NormalFrameTest {

    @DisplayName("NormalFrame 생성 시 초기화 작업")
    @Test
    void 일반_프레임_생성_초기화() {
        NormalFrame normal = new NormalFrame();
        assertThat(normal.isGameOver()).isFalse();
    }

    @DisplayName("투구 하기 - 1번(스트라이크여도 새로운프레임 반환 X)")
    @Test
    void 한번_투구_스트라이크_프레임유지() {
        NormalFrame normal = new NormalFrame();
        assertThat(normal.bowl(10)).isEqualTo(normal);
    }

    @DisplayName("투구 하기 - 2번(스페어여도 새로운프레임 반환 X)")
    @Test
    void 두번_투구_스페어_프레임유지() {
        NormalFrame normal = new NormalFrame();
        NormalFrame replace = new NormalFrame();

        replace = normal.bowl(5);
        assertThat(replace.bowl(5)).isEqualTo(normal);
    }

    @DisplayName("투구 하기 - 2번(커터 새로운프레임 반환 X)")
    @Test
    void 두번_투구_거터_프레임유지() {
        NormalFrame normal = new NormalFrame();
        NormalFrame replace = new NormalFrame();

        replace = normal.bowl(0);
        assertThat(replace.bowl(0)).isEqualTo(normal);
    }

    @DisplayName("투구 하기 - 2번(미스 새로운프레임 반환 X)")
    @Test
    void 두번_투구_미스_프레임유지() {
        NormalFrame normal = new NormalFrame();
        NormalFrame replace = new NormalFrame();

        replace = normal.bowl(3);
        assertThat(replace.bowl(6)).isEqualTo(normal);
    }

    @DisplayName("투구 하기 - 2번(스트라이크 이후 투구 새로운프레임 반환)")
    @Test
    void 한번_투구_스트라이크_프레임변경() {
        NormalFrame normal = new NormalFrame();
        NormalFrame replace = new NormalFrame();
        replace = normal.bowl(10);
        assertThat(replace.bowl(1)).isNotSameAs(normal);
    }

    @DisplayName("투구 하기 - 3번(스페어여 이후 투구 새로운프레임 반환)")
    @Test
    void 두번_투구_스페어_프레임변경() {
        NormalFrame normal = new NormalFrame();
        NormalFrame replace = new NormalFrame();

        replace = normal.bowl(5);
        replace = normal.bowl(5);
        assertThat(replace.bowl(5)).isNotSameAs(normal);
    }

    @DisplayName("투구 하기 - 3번(커터 아후 새로운프레임 반환)")
    @Test
    void 두번_투구_거터_프레임변경() {
        NormalFrame normal = new NormalFrame();
        NormalFrame replace = new NormalFrame();

        replace = normal.bowl(0);
        replace = normal.bowl(0);
        assertThat(replace.bowl(0)).isNotSameAs(normal);
    }

    @DisplayName("투구 하기 - 3번(미스 이후 새로운프레임 반환)")
    @Test
    void 두번_투구_미스_프레임변경() {
        NormalFrame normal = new NormalFrame();
        NormalFrame replace = new NormalFrame();

        replace = normal.bowl(3);
        replace = normal.bowl(9);
        assertThat(replace.bowl(1)).isNotSameAs(normal);
    }
}
