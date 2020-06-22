package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
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

        PlayerFrames playerFrames = empty.lastValue(NormalFrame.start(TEN));

        assertThat(playerFrames.size()).isEqualTo(1);
        assertThat(playerFrames).isEqualTo(new PlayerFrames(Collections.singletonList(NormalFrame.start(TEN))));
    }

    @DisplayName("현재 컬렉션의 마지막 프레임이 완료됐다면 새로운 Frame을 추가한다.")
    @Test
    void doLastTest() {
        PlayerFrames empty = PlayerFrames.createEmpty();
        PlayerFrames sizeOneFrames = empty.lastValue(NormalFrame.start(TEN));

        PlayerFrames sizeTwoFrames = sizeOneFrames.lastValue(NormalFrame.start(FIVE));

        assertThat(sizeTwoFrames.size()).isEqualTo(2);
        assertThat(sizeTwoFrames)
                .isEqualTo(new PlayerFrames(Arrays.asList(NormalFrame.start(TEN), NormalFrame.start(FIVE))));
    }
}
