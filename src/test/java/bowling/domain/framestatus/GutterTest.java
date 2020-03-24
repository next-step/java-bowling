package bowling.domain.framestatus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class GutterTest {

    @Test
    @DisplayName("거터 객체 생성")
    void createGutter() {
        Gutter gutter = new Gutter(0);
        assertThat(gutter.getCurrentScore()).isEqualTo(0);
    }

    @Test
    @DisplayName("Score 0 이 아닌 값에 대한 거터 예외 처리")
    void exceptGutter() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Gutter(10));
    }

    @Test
    @DisplayName("두 번째 투구 시 거터 생성")
    void createGutterBySecondBowl() {
        Gutter gutter = new Gutter(8, 0);
        assertThat(gutter.getCurrentScore()).isEqualTo(0);
        assertThat(gutter.getPreScore()).isEqualTo(8);
        assertThatIllegalArgumentException().isThrownBy(() -> new Gutter(8, 3));
    }
}
