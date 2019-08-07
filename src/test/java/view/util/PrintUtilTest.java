package view.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-08-06 19:42
 */
class PrintUtilTest {

    @DisplayName("헤더영역 출력")
    @Test
    void 프레임_헤더_영역_출력() {
        StringBuilder sb = new StringBuilder();
        sb.append("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");

        assertThat(PrintUtil.headerArea()).isEqualTo(sb.toString());
    }

    @DisplayName("플레이어의 이름 출력")
    @Test
    void 프레임_플레이어_이름_영역_출력() {
        StringBuilder sb = new StringBuilder();
        sb.append("| KBY |");

        assertThat(PrintUtil.playerNameArea("KBY")).isEqualTo(sb.toString());
    }

    @DisplayName("투구되지 않은 여분의 프레임을 빈칸으로 출력")
    @Test
    void 투구되지_않은() {
        StringBuilder sb = new StringBuilder();
        sb.append("      |");

        assertThat(PrintUtil.idleFrameArea(9)).isEqualTo(sb.toString());
    }

    @DisplayName("점수 출력")
    @Test
    void 프레임_점수_출력() {
        assertAll(
                () -> assertThat(PrintUtil.stateFrmaeArea("X")).isEqualTo("  X   |"),
                () -> assertThat(PrintUtil.stateFrmaeArea("-|/")).isEqualTo("  -|/ |"),
                () -> assertThat(PrintUtil.stateFrmaeArea("3|6")).isEqualTo("  3|6 |")
        );
    }
}