package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

public class NomalFrameTest {

    @ParameterizedTest
    @DisplayName("첫번째 프레임 1번 쓰러뜨린 볼링핀 수 확인")
    @ValueSource(ints = {10, 9, 7, 3})
    public void 첫번째_프레임_쓰러뜨린_볼링핀수(int countOfKnockDown) {
        Frame nomalFrame = NomalFrame.init();
        nomalFrame.add(Pitch.of(countOfKnockDown));
        assertEquals(nomalFrame.getFirstOfKnockDown(), countOfKnockDown);
    }

    @Test
    @DisplayName("첫번째 프레임에 모두 던졌을때 쓰러뜨린 볼링핀 수 확인")
    public void 프레임_모두_쓰러뜨린_볼링핀수() {
        Frame nomalFrame = NomalFrame.init();

        nomalFrame.add(Pitch.of(3));
        nomalFrame.add(Pitch.of(5));

        assertEquals(nomalFrame.size(), 2);
        assertEquals(nomalFrame.getFirstOfKnockDown(), 3);
        assertEquals(nomalFrame.getSecondOfKnockDown(), 5);
    }

    @ParameterizedTest
    @DisplayName("쓰러뜨린 볼링 투구 값 10이상인 경우 예외")
    @ValueSource(ints = {11, 15, 21})
    public void 볼링핀_경계값_테스트(int countOfKnockDown) {
        Frame nomalFrame = NomalFrame.init();

        assertThatThrownBy(() -> nomalFrame.add(Pitch.of(countOfKnockDown)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("볼링 핀은 최대 10개입니다.");
    }
}
