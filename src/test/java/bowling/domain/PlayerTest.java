package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
public class PlayerTest {

    @Test
    public void 플레이어를_생성할_수_있다() {
        //given
        //when
        //then
        assertThat(Player.from("SML")).isEqualTo(Player.from("SML"));
    }

    @Test
    public void 이름이_영어가_아니면_익셉션이_발생한다() {
        //given
        //when
        //then
        assertThatThrownBy(() -> Player.from("상민"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 영어여야 합니다.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void 플레이어의_이름이_null이거나_비어있으면_익셉션이_발생한다(String name) {
        //given
        //when
        //then
        assertThatThrownBy(() -> Player.from(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름이 비어있습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"AAAA", "BBBBB", "CCCCCC"})
    public void 플레이어의_이름은_3자를_넘으면_익셉션이_발생한다(String name) {
        //given
        //when
        //then
        assertThatThrownBy(() -> Player.from(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 3자를 넘을 수 없습니다.");
    }

    @Nested
    @DisplayName("프레임 종료 확인 테스트")
    class FrameEndTest {

        @Test
        public void 현재_프레임이_스트라이크라면_현재_프레임은_끝났다() {
            //given
            Player sml = Player.from("SML");
            //when
            sml = sml.bowl(10);
            //then
            assertThat(sml.nowFrameEnd()).isTrue();
        }

        @Test
        public void 현재_프레임이_스트라이크가_아니고_첫시도라면_끝나지_않는다() {
            //given
            Player sml = Player.from("SML");
            //when
            sml = sml.bowl(8);
            //then
            assertThat(sml.nowFrameEnd()).isFalse();
        }

    }
}
