package bowling.domain;

import bowling.dto.FrameDto;
import bowling.dto.RollDto;
import bowling.dto.ScoreDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static bowling.asset.Const.MAX_FRAME_NO;
import static bowling.domain.FrameEnum.*;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PlayerStatusTest {

    @ParameterizedTest
    @DisplayName("콜백 함수의 register 테스트")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    void register(int playTimes) {
        List<Integer> list = new LinkedList<>();
        PlayerStatus status = PlayerStatus.of(() -> Roll.of(10));
        status.register(subject -> list.add(0));
        for (int i = 0; i < playTimes; i++) {
            status.play();
        }
        assertThat(list.size())
                .isEqualTo(playTimes);
    }

    @Test
    @DisplayName("playFrame 으로 인한 Board 결과 테스트")
    void playFrame() {
        PlayerStatus status = PlayerStatus.of(() -> Roll.of(1));
        for (int i = 0; i < MAX_FRAME_NO; i++) {
            status.play();
        }

        List<Integer> rolls = toRolls(status);
        List<FrameEnum> frames = toFrames(status);
        List<Integer> scores = toScores(status);

        assertAll(
                () -> assertThat(rolls)
                        .isEqualTo(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)),
                () -> assertThat(frames)
                        .isEqualTo(Arrays.asList(MISS, MISS, MISS, MISS, MISS, MISS, MISS, MISS, MISS, MISS)),
                () -> assertThat(scores)
                        .isEqualTo(Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)),
                () -> assertThat(frames.size())
                        .isEqualTo(MAX_FRAME_NO),
                () -> assertThat(scores.size())
                        .isEqualTo(MAX_FRAME_NO)
        );
    }

    @Test
    @DisplayName("마지막이 Strike 일 때, playBonus 으로 인한 Board 결과 테스트")
    void playBonus_STRIKE() {
        PlayerStatus status = PlayerStatus.of(() -> Roll.of(10));
        for (int i = 0; i < MAX_FRAME_NO; i++) {
            status.play();
        }

        List<Integer> rolls = toRolls(status);
        List<FrameEnum> frames = toFrames(status);
        List<Integer> scores = toScores(status);

        assertAll(
                () -> assertThat(rolls)
                        .isEqualTo(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10)),
                () -> assertThat(frames)
                        .isEqualTo(Arrays.asList(STRIKE,
                                STRIKE,
                                STRIKE,
                                STRIKE,
                                STRIKE,
                                STRIKE,
                                STRIKE,
                                STRIKE,
                                STRIKE,
                                STRIKE)),
                () -> assertThat(scores)
                        .isEqualTo(Arrays.asList(30, 60, 90, 120, 150, 180, 210, 240, 270, 300)),
                () -> assertThat(frames.size())
                        .isEqualTo(MAX_FRAME_NO),
                () -> assertThat(scores.size())
                        .isEqualTo(MAX_FRAME_NO)
        );
    }

    @Test
    @DisplayName("마지막이 SPARE 일 때, playBonus 으로 인한 Board 결과 테스트")
    void playBonus_SPARE() {
        PlayerStatus status = PlayerStatus.of(() -> Roll.of(5));
        for (int i = 0; i < MAX_FRAME_NO; i++) {
            status.play();
        }

        List<Integer> rolls = toRolls(status);
        List<FrameEnum> frames = toFrames(status);
        List<Integer> scores = toScores(status);

        assertAll(
                () -> assertThat(rolls)
                        .isEqualTo(Arrays.asList(5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5)),
                () -> assertThat(frames)
                        .isEqualTo(Arrays.asList(SPARE,
                                SPARE,
                                SPARE,
                                SPARE,
                                SPARE,
                                SPARE,
                                SPARE,
                                SPARE,
                                SPARE,
                                SPARE)),
                () -> assertThat(scores)
                        .isEqualTo(Arrays.asList(15, 30, 45, 60, 75, 90, 105, 120, 135, 150)),
                () -> assertThat(frames.size())
                        .isEqualTo(MAX_FRAME_NO),
                () -> assertThat(scores.size())
                        .isEqualTo(MAX_FRAME_NO)
        );
    }

    private List<Integer> toRolls(PlayerStatus status) {
        return status.exportPlayerStatusDto()
                .getRollsDto()
                .getRolls()
                .stream()
                .map(RollDto::getCountOfPins)
                .collect(toList());
    }

    private List<FrameEnum> toFrames(PlayerStatus status) {
        return status.exportPlayerStatusDto()
                .getBoardDto()
                .getFramesDto()
                .getFrames()
                .stream()
                .map(FrameDto::getFrameEnum)
                .collect(toList());
    }

    private List<Integer> toScores(PlayerStatus status) {
        return status.exportPlayerStatusDto()
                .getBoardDto()
                .getScoresDto()
                .getScores()
                .stream()
                .map(ScoreDto::getScore)
                .collect(toList());
    }
}
