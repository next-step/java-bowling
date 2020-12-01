package bowling.domain;

import bowling.dto.FrameDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static bowling.asset.Const.MAX_FRAME_NO;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class FramesTest {

    private Frames frames;
    private Rolls rolls;

    @BeforeEach
    void setUp() {
        frames = new Frames();
        rolls = new Rolls();
    }

    private void addRoll(Roll roll) {
        rolls.add(roll);
        frames.update(rolls);
    }

    private List<FrameEnum> toFrameEnumList(Frames frames) {
        return frames.exportFramesDto()
                .getFrames()
                .stream()
                .map(FrameDto::getFrameEnum)
                .collect(toList());
    }

    @Test
    @DisplayName("볼링을 치지 않은 시나리오 테스트")
    void scenario_empty() {
        assertAll(
                () -> assertThat(frames.size())
                        .isEqualTo(0),
                () -> assertThat(frames.getFrameNo())
                        .isEqualTo(1),
                () -> assertThat(frames.isStrike())
                        .isFalse(),
                () -> assertThat(frames.isSpare())
                        .isFalse(),
                () -> assertThat(frames.isGameOver())
                        .isFalse(),
                () -> assertThat(toFrameEnumList(frames))
                        .isEqualTo(emptyList())
        );
    }

    @Test
    @DisplayName("STRIKE 를 치는 시나리오 테스트")
    void scenario_strike() {
        addRoll(Roll.of(10));
        assertAll(
                () -> assertThat(frames.size())
                        .isEqualTo(1),
                () -> assertThat(frames.getFrameNo())
                        .isEqualTo(2),
                () -> assertThat(frames.isStrike())
                        .isTrue(),
                () -> assertThat(frames.isSpare())
                        .isFalse(),
                () -> assertThat(frames.isGameOver())
                        .isFalse(),
                () -> assertThat(toFrameEnumList(frames))
                        .isEqualTo(Arrays.asList(FrameEnum.STRIKE))
        );
    }

    @Test
    @DisplayName("STRIKE, SPARE 를 치는 시나리오 테스트")
    void scenario_strike_spare() {
        addRoll(Roll.of(10));

        addRoll(Roll.of(1));
        addRoll(Roll.of(9));
        assertAll(
                () -> assertThat(frames.size())
                        .isEqualTo(2),
                () -> assertThat(frames.getFrameNo())
                        .isEqualTo(3),
                () -> assertThat(frames.isStrike())
                        .isFalse(),
                () -> assertThat(frames.isSpare())
                        .isTrue(),
                () -> assertThat(frames.isGameOver())
                        .isFalse(),
                () -> assertThat(toFrameEnumList(frames))
                        .isEqualTo(Arrays.asList(FrameEnum.STRIKE, FrameEnum.SPARE))
        );
    }

    @Test
    @DisplayName("STRIKE, SPARE, MISS 를 치는 시나리오 테스트")
    void scenario_strike_spare_miss() {
        addRoll(Roll.of(10));

        addRoll(Roll.of(1));
        addRoll(Roll.of(9));

        addRoll(Roll.of(4));
        addRoll(Roll.of(5));
        assertAll(
                () -> assertThat(frames.size())
                        .isEqualTo(3),
                () -> assertThat(frames.getFrameNo())
                        .isEqualTo(4),
                () -> assertThat(frames.isStrike())
                        .isFalse(),
                () -> assertThat(frames.isSpare())
                        .isFalse(),
                () -> assertThat(frames.isGameOver())
                        .isFalse(),
                () -> assertThat(toFrameEnumList(frames))
                        .isEqualTo(Arrays.asList(FrameEnum.STRIKE, FrameEnum.SPARE, FrameEnum.MISS))
        );
    }

    @Test
    @DisplayName("UNFINISHED 시나리오 테스트")
    void scenario_unfinished() {
        addRoll(Roll.of(10));

        addRoll(Roll.of(1));
        addRoll(Roll.of(9));

        addRoll(Roll.of(4));
        addRoll(Roll.of(5));

        addRoll(Roll.of(8));
        assertAll(
                () -> assertThat(frames.size())
                        .isEqualTo(4),
                () -> assertThat(frames.getFrameNo())
                        .isEqualTo(4),
                () -> assertThat(frames.isStrike())
                        .isFalse(),
                () -> assertThat(frames.isSpare())
                        .isFalse(),
                () -> assertThat(frames.isGameOver())
                        .isFalse(),
                () -> assertThat(toFrameEnumList(frames))
                        .isEqualTo(Arrays.asList(FrameEnum.STRIKE, FrameEnum.SPARE, FrameEnum.MISS, FrameEnum.UNFINISHED))
        );
    }

    @Test
    @DisplayName("sublist 테스트")
    void sublist() {
        addRoll(Roll.of(10));

        addRoll(Roll.of(1));
        addRoll(Roll.of(9));

        addRoll(Roll.of(4));
        addRoll(Roll.of(5));

        addRoll(Roll.of(8));

        assertThat(frames.subList(1, 3)
                .stream()
                .map(Frame::exportFrameDto)
                .map(FrameDto::getFrameEnum)
        ).isEqualTo(Arrays.asList(FrameEnum.SPARE, FrameEnum.MISS));
    }

    @ParameterizedTest
    @DisplayName("MAX_FRAME_NO 이하이면 Game Over 가 아니다.")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    void isGameOver_false(int frameNo) {
        for (int i = 0; i < frameNo; i++) {
            addRoll(Roll.of(10));
        }

        assertThat(frames.isGameOver())
                .isFalse();
    }

    @Test
    @DisplayName("MAX_FRAME_NO 을 넘기면 Game Over 가 된다.")
    void isGameOver_true() {
        for (int i = 0; i < MAX_FRAME_NO; i++) {
            addRoll(Roll.of(10));
        }

        assertThat(frames.isGameOver())
                .isTrue();
    }
}
