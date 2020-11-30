package bowling.domain;

import bowling.dto.FrameDto;
import bowling.dto.RollDto;
import bowling.dto.ScoreDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static bowling.domain.FrameEnum.*;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PlayerStatusTest {

    @Test
    @DisplayName("playFrame 으로 인한 Board 결과 테스트")
    void playFrame() {
        PlayerStatus status = PlayerStatus.of(num -> Roll.of(num), Arrays.asList());
        status.playFrame();
        status.playFrame();
        status.playFrame();
        status.playFrame();
        status.playFrame();

        List<Integer> rolls = toRolls(status);
        List<FrameEnum> frames = toFrames(status);
        List<Integer> scores = toScores(status);

        assertAll(
                () -> assertThat(rolls)
                        .isEqualTo(Arrays.asList(1, 1, 2, 2, 3, 3, 4, 4, 5, 5)),
                () -> assertThat(frames)
                        .isEqualTo(Arrays.asList(MISS, MISS, MISS, MISS, SPARE)),
                () -> assertThat(scores)
                        .isEqualTo(Arrays.asList(2, 4, 6, 8))
        );
    }

    @Test
    @DisplayName("마지막이 Strike 일 때, playBonus 으로 인한 Board 결과 테스트")
    void playBonus_STRIKE() {
        PlayerStatus status = PlayerStatus.of(num -> Roll.of(10), Arrays.asList());
        status.playFrame();
        status.playFrame();
        status.playBonus();

        List<Integer> rolls = toRolls(status);
        List<FrameEnum> frames = toFrames(status);
        List<Integer> scores = toScores(status);

        assertAll(
                () -> assertThat(rolls)
                        .isEqualTo(Arrays.asList(10, 10, 10, 0)),
                () -> assertThat(frames)
                        .isEqualTo(Arrays.asList(STRIKE, STRIKE, STRIKE, UNFINISHED)),
                () -> assertThat(scores)
                        .isEqualTo(Arrays.asList(30, 20))
        );
    }

    @Test
    @DisplayName("마지막이 SPARE 일 때, playBonus 으로 인한 Board 결과 테스트")
    void playBonus_SPARE() {
        PlayerStatus status = PlayerStatus.of(num -> Roll.of(5), Arrays.asList());
        status.playFrame();
        status.playFrame();
        status.playBonus();

        List<Integer> rolls = toRolls(status);
        List<FrameEnum> frames = toFrames(status);
        List<Integer> scores = toScores(status);

        assertAll(
                () -> assertThat(rolls)
                        .isEqualTo(Arrays.asList(5, 5, 5, 5, 5)),
                () -> assertThat(frames)
                        .isEqualTo(Arrays.asList(SPARE, SPARE, UNFINISHED)),
                () -> assertThat(scores)
                        .isEqualTo(Arrays.asList(15, 15))
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
