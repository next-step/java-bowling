package bowling.domain;

import bowling.domain.frame.FrameEnum;
import bowling.dto.FrameDto;
import bowling.dto.RollDto;
import bowling.dto.ScoreDto;
import bowling.exception.BadCountOfPinsException;
import bowling.exception.RollException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static bowling.asset.Const.MAX_FRAME_NO;
import static bowling.domain.frame.FrameEnum.*;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

class PlayerStatusTest {
    private PlayerStatus status;

    @BeforeEach
    void setUp() {
        status = new PlayerStatus();
    }

    private List<Integer> toRollList(PlayerStatus status) {
        return status.exportPlayerStatusDto()
                .getRollsDto()
                .getRolls()
                .stream()
                .map(RollDto::getCountOfPins)
                .collect(toList());
    }

    private List<FrameEnum> toFrameList(PlayerStatus status) {
        return status.exportPlayerStatusDto()
                .getBoardDto()
                .getFramesDto()
                .getFrames()
                .stream()
                .map(FrameDto::getFrameEnum)
                .collect(toList());
    }

    private List<Integer> toScoreList(PlayerStatus status) {
        return status.exportPlayerStatusDto()
                .getBoardDto()
                .getScoresDto()
                .getScores()
                .stream()
                .map(ScoreDto::getScore)
                .collect(toList());
    }

    @ParameterizedTest
    @DisplayName("핀 갯수가 음수이면, RollException 이 발생한다.")
    @CsvSource(value = {"-8$3", "10$-1", "11$3", "4$12"}, delimiter = '$')
    void scenario_negative(int roll1, int roll2) {
        assertThatExceptionOfType(RollException.class)
                .isThrownBy(() -> {
                    status.addRoll(Roll.of(roll1));
                    status.addRoll(Roll.of(roll2));
                }).withMessage("핀의 개수는 0 이상 10 이하여야 합니다.");
    }

    @ParameterizedTest
    @DisplayName("적절하지 않은 핀 갯수이면, BadCountOfPinsException 이 발생한다.")
    @CsvSource(value = {"9$2", "4$7"}, delimiter = '$')
    void scenario_badCountOfPins(int roll1, int roll2) {
        assertThatExceptionOfType(BadCountOfPinsException.class)
                .isThrownBy(() -> {
                    status.addRoll(Roll.of(roll1));
                    status.addRoll(Roll.of(roll2));
                }).withMessage("한 프레임에서 쓰러트린 핀의 개수는 0 이상 10 이하여야 합니다.");
    }

    @Test
    @DisplayName("볼링을 치지 않은 시나리오 테스트")
    void scenario_empty() {
        assertAll(
                () -> assertThat(toRollList(status))
                        .isEqualTo(emptyList()),
                () -> assertThat(toFrameList(status))
                        .isEqualTo(emptyList()),
                () -> assertThat(toScoreList(status))
                        .isEqualTo(emptyList())
        );
    }


    @Test
    @DisplayName("STRIKE 를 두번 치는 시나리오 테스트")
    void scenario_strike() {
        status.addRoll(Roll.of(10));
        status.addRoll(Roll.of(10));
        assertAll(
                () -> assertThat(status.getLastCountOfPins())
                        .isEqualTo(10),
                () -> assertThat(toRollList(status))
                        .isEqualTo(Arrays.asList(10, 10)),
                () -> assertThat(toFrameList(status))
                        .isEqualTo(Arrays.asList(STRIKE, STRIKE)),
                () -> assertThat(toScoreList(status))
                        .isEqualTo(emptyList())
        );
    }

    @Test
    @DisplayName("STRIKE, STRIKE, SPARE 를 치는 시나리오 테스트")
    void scenario_strike_spare() {
        status.addRoll(Roll.of(10));
        status.addRoll(Roll.of(10));

        status.addRoll(Roll.of(1));
        status.addRoll(Roll.of(9));
        assertAll(
                () -> assertThat(status.getLastCountOfPins())
                        .isEqualTo(9),
                () -> assertThat(toRollList(status))
                        .isEqualTo(Arrays.asList(10, 10, 1, 9)),
                () -> assertThat(toFrameList(status))
                        .isEqualTo(Arrays.asList(STRIKE, STRIKE, SPARE)),
                () -> assertThat(toScoreList(status))
                        .isEqualTo(Arrays.asList(21, 41))
        );
    }

    @Test
    @DisplayName("STRIKE, SPARE, MISS 를 치는 시나리오 테스트")
    void scenario_strike_spare_miss() {
        status.addRoll(Roll.of(10));

        status.addRoll(Roll.of(1));
        status.addRoll(Roll.of(9));

        status.addRoll(Roll.of(4));
        status.addRoll(Roll.of(5));
        assertAll(
                () -> assertThat(status.getLastCountOfPins())
                        .isEqualTo(5),
                () -> assertThat(toRollList(status))
                        .isEqualTo(Arrays.asList(10, 1, 9, 4, 5)),
                () -> assertThat(toFrameList(status))
                        .isEqualTo(Arrays.asList(STRIKE, SPARE, MISS)),
                () -> assertThat(toScoreList(status))
                        .isEqualTo(Arrays.asList(20, 34, 43))
        );
    }

    @Test
    @DisplayName("UNFINISHED 시나리오 테스트")
    void scenario_unfinished() {
        status.addRoll(Roll.of(10));

        status.addRoll(Roll.of(1));
        status.addRoll(Roll.of(9));

        status.addRoll(Roll.of(4));
        status.addRoll(Roll.of(5));

        status.addRoll(Roll.of(10));

        status.addRoll(Roll.of(8));
        assertAll(
                () -> assertThat(status.getLastCountOfPins())
                        .isEqualTo(8),
                () -> assertThat(toRollList(status))
                        .isEqualTo(Arrays.asList(10, 1, 9, 4, 5, 10, 8)),
                () -> assertThat(toFrameList(status))
                        .isEqualTo(Arrays.asList(STRIKE, SPARE, MISS, STRIKE, UNFINISHED)),
                () -> assertThat(toScoreList(status))
                        .isEqualTo(Arrays.asList(20, 34, 43))
        );
    }

    @Test
    @DisplayName("STRIKE 100번 추가하는 시나리오 테스트")
    void scenario_strike_100() {
        List<Integer> rollList = new LinkedList<>();
        int roll = 10;
        for (int i = 0; i < 100; i++) {
            status.addRoll(Roll.of(roll));
            rollList.add(roll);
        }
        List<FrameEnum> frameList = toFrameList(status);
        List<Integer> scoreList = toScoreList(status);
        assertAll(
                () -> assertThat(status.getLastCountOfPins())
                        .isEqualTo(10),
                () -> assertThat(toRollList(status))
                        .isEqualTo(rollList),
                () -> assertThat(frameList)
                        .isEqualTo(Arrays.asList(STRIKE, STRIKE, STRIKE, STRIKE, STRIKE, STRIKE, STRIKE, STRIKE, STRIKE, STRIKE)),
                () -> assertThat(scoreList)
                        .isEqualTo(Arrays.asList(30, 60, 90, 120, 150, 180, 210, 240, 270, 300)),
                () -> assertThat(scoreList.size())
                        .isEqualTo(MAX_FRAME_NO),
                () -> assertThat(frameList.size())
                        .isEqualTo(MAX_FRAME_NO)
        );
    }

    @Test
    @DisplayName("SPARE 100번 추가하는 시나리오 테스트")
    void scenario_spare_100() {
        List<Integer> rollList = new LinkedList<>();
        int roll = 5;
        for (int i = 0; i < 100; i++) {
            status.addRoll(Roll.of(roll));
            rollList.add(roll);
        }
        List<FrameEnum> frameList = toFrameList(status);
        List<Integer> scoreList = toScoreList(status);
        assertAll(
                () -> assertThat(status.getLastCountOfPins())
                        .isEqualTo(5),
                () -> assertThat(toRollList(status))
                        .isEqualTo(rollList),
                () -> assertThat(frameList)
                        .isEqualTo(Arrays.asList(SPARE, SPARE, SPARE, SPARE, SPARE, SPARE, SPARE, SPARE, SPARE, SPARE)),
                () -> assertThat(scoreList)
                        .isEqualTo(Arrays.asList(15, 30, 45, 60, 75, 90, 105, 120, 135, 150)),
                () -> assertThat(scoreList.size())
                        .isEqualTo(MAX_FRAME_NO),
                () -> assertThat(frameList.size())
                        .isEqualTo(MAX_FRAME_NO)
        );
    }

    @Test
    @DisplayName("MISS 100번 추가하는 시나리오 테스트")
    void scenario_miss_100() {
        List<Integer> rollList = new LinkedList<>();
        int roll = 3;
        for (int i = 0; i < 100; i++) {
            status.addRoll(Roll.of(roll));
            rollList.add(roll);
        }
        List<FrameEnum> frameList = toFrameList(status);
        List<Integer> scoreList = toScoreList(status);
        assertAll(
                () -> assertThat(status.getLastCountOfPins())
                        .isEqualTo(3),
                () -> assertThat(toRollList(status))
                        .isEqualTo(rollList),
                () -> assertThat(frameList)
                        .isEqualTo(Arrays.asList(MISS, MISS, MISS, MISS, MISS, MISS, MISS, MISS, MISS, MISS)),
                () -> assertThat(scoreList)
                        .isEqualTo(Arrays.asList(6, 12, 18, 24, 30, 36, 42, 48, 54, 60)),
                () -> assertThat(scoreList.size())
                        .isEqualTo(MAX_FRAME_NO),
                () -> assertThat(frameList.size())
                        .isEqualTo(MAX_FRAME_NO)
        );
    }
}
