package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerFramesTests {
    private static final int TEN = 10;
    private static final int FIVE = 5;

    @DisplayName("아무 것도 없는 상태로 초기화")
    @Test
    void create() {
        PlayerFrames playerFrames = PlayerFrames.createEmpty();

        assertThat(playerFrames.size()).isEqualTo(0);
    }

    @DisplayName("비어 있는 상태에서 새로운 Frame을 추가할 수 있다.")
    @Test
    void addNewToEmptyTest() {
        PlayerFrames empty = PlayerFrames.createEmpty();
        NormalFrame normalFrame = NormalFrame.start(TEN);

        PlayerFrames playerFrames = empty.lastValue(normalFrame);

        assertThat(playerFrames.size()).isEqualTo(1);
        assertThat(playerFrames).isEqualTo(new PlayerFrames(Collections.singletonList(normalFrame)));
    }

    @DisplayName("컬렉션의 가장 마지막 Frame이 완료됐다면 다음 Frame을 추가한다.")
    @Test
    void doLastTest() {
    }
}
