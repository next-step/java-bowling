package bowling.domain.bowl;

import bowling.domain.frame.FrameStatus;
import bowling.domain.frame.Pin;
import bowling.dto.FrameStatusDto;
import bowling.dto.PinDto;
import bowling.dto.ScoreDto;
import bowling.exception.BadCountOfDownPinsException;
import bowling.exception.PinException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static bowling.asset.Const.MAX_FRAME_NUMBER;
import static bowling.domain.frame.FrameStatus.*;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

class BowlTest {
    private Bowl bowl;

    @BeforeEach
    void setUp() {
        bowl = new Bowl();
    }

    private List<Integer> toPinList(Bowl bowl) {
        return bowl
                .exportBowlDto()
                .getFramesDto()
                .getPinsDto()
                .getPins()
                .stream()
                .map(PinDto::getCountOfDownPins)
                .collect(toList());
    }

    private List<FrameStatus> toFrameList(Bowl bowl) {
        return bowl.exportBowlDto()
                .getFramesDto()
                .getFrameStatusesDto()
                .getFrameStatuses()
                .stream()
                .map(FrameStatusDto::getFrameStatus)
                .collect(toList());
    }

    private List<Integer> toScoreList(Bowl bowl) {
        return bowl.exportBowlDto()
                .getScoresDto()
                .getScores()
                .stream()
                .map(ScoreDto::getScore)
                .collect(toList());
    }

    @ParameterizedTest
    @DisplayName("쓰러트린 핀 개수가 음수이면, PinException 이 발생한다.")
    @CsvSource(value = {"-8$3", "10$-1", "11$3", "4$12"}, delimiter = '$')
    void scenario_negative(int pin1, int pin2) {
        assertThatExceptionOfType(PinException.class)
                .isThrownBy(() -> {
                    bowl.addPin(Pin.of(pin1));
                    bowl.addPin(Pin.of(pin2));
                }).withMessage("쓰러트린 핀의 개수는 0 이상 10 이하여야 합니다.");
    }

    @ParameterizedTest
    @DisplayName("쓰러트린 핀 개수가 적절치 않으면, BadCountOfDownPinsException 이 발생한다.")
    @CsvSource(value = {"9$2", "4$7"}, delimiter = '$')
    void scenario_badCountOfDownPins(int pin1, int pin2) {
        assertThatExceptionOfType(BadCountOfDownPinsException.class)
                .isThrownBy(() -> {
                    bowl.addPin(Pin.of(pin1));
                    bowl.addPin(Pin.of(pin2));
                }).withMessage("한 프레임에서 쓰러트린 핀의 개수는 0 이상 10 이하여야 합니다.");
    }

    @Test
    @DisplayName("볼링을 치지 않은 시나리오 테스트")
    void scenario_empty() {
        assertAll(
                () -> assertThat(bowl.getFrameNumber())
                        .isEqualTo(1),
                () -> assertThat(toPinList(bowl))
                        .isEqualTo(emptyList()),
                () -> assertThat(toFrameList(bowl))
                        .isEqualTo(emptyList()),
                () -> assertThat(toScoreList(bowl))
                        .isEqualTo(emptyList())
        );
    }


    @Test
    @DisplayName("STRIKE 를 두번 치는 시나리오 테스트")
    void scenario_strike() {
        bowl.addPin(Pin.of(10));
        bowl.update();
        bowl.addPin(Pin.of(10));
        bowl.update();
        assertAll(
                () -> assertThat(bowl.getFrameNumber())
                        .isEqualTo(3),
                () -> assertThat(toPinList(bowl))
                        .isEqualTo(Arrays.asList(10, 10)),
                () -> assertThat(toFrameList(bowl))
                        .isEqualTo(Arrays.asList(STRIKE, STRIKE)),
                () -> assertThat(toScoreList(bowl))
                        .isEqualTo(emptyList())
        );
    }

    @Test
    @DisplayName("STRIKE, STRIKE, SPARE 를 치는 시나리오 테스트")
    void scenario_strike_spare() {
        bowl.addPin(Pin.of(10));
        bowl.update();
        bowl.addPin(Pin.of(10));
        bowl.update();

        bowl.addPin(Pin.of(1));
        bowl.addPin(Pin.of(9));
        bowl.update();
        assertAll(
                () -> assertThat(bowl.getFrameNumber())
                        .isEqualTo(4),
                () -> assertThat(toPinList(bowl))
                        .isEqualTo(Arrays.asList(10, 10, 1, 9)),
                () -> assertThat(toFrameList(bowl))
                        .isEqualTo(Arrays.asList(STRIKE, STRIKE, SPARE)),
                () -> assertThat(toScoreList(bowl))
                        .isEqualTo(Arrays.asList(21, 41))
        );
    }

    @Test
    @DisplayName("STRIKE, SPARE, MISS 를 치는 시나리오 테스트")
    void scenario_strike_spare_miss() {
        bowl.addPin(Pin.of(10));
        bowl.update();

        bowl.addPin(Pin.of(1));
        bowl.addPin(Pin.of(9));
        bowl.update();

        bowl.addPin(Pin.of(4));
        bowl.addPin(Pin.of(5));
        bowl.update();
        assertAll(
                () -> assertThat(bowl.getFrameNumber())
                        .isEqualTo(4),
                () -> assertThat(toPinList(bowl))
                        .isEqualTo(Arrays.asList(10, 1, 9, 4, 5)),
                () -> assertThat(toFrameList(bowl))
                        .isEqualTo(Arrays.asList(STRIKE, SPARE, MISS)),
                () -> assertThat(toScoreList(bowl))
                        .isEqualTo(Arrays.asList(20, 34, 43))
        );
    }

    @Test
    @DisplayName("UNFINISHED 시나리오 테스트")
    void scenario_unfinished() {
        bowl.addPin(Pin.of(10));
        bowl.update();

        bowl.addPin(Pin.of(1));
        bowl.addPin(Pin.of(9));
        bowl.update();

        bowl.addPin(Pin.of(4));
        bowl.addPin(Pin.of(5));
        bowl.update();

        bowl.addPin(Pin.of(10));
        bowl.update();

        bowl.addPin(Pin.of(8));
        assertAll(
                () -> assertThat(bowl.getFrameNumber())
                        .isEqualTo(5),
                () -> assertThat(toPinList(bowl))
                        .isEqualTo(Arrays.asList(10, 1, 9, 4, 5, 10, 8)),
                () -> assertThat(toFrameList(bowl))
                        .isEqualTo(Arrays.asList(STRIKE, SPARE, MISS, STRIKE, UNFINISHED)),
                () -> assertThat(toScoreList(bowl))
                        .isEqualTo(Arrays.asList(20, 34, 43))
        );
    }

    @Test
    @DisplayName("STRIKE 만 finish 까지 치는 시나리오 테스트")
    void scenario_strike_finish() {
        int countOfDownPins = 10;
        while (bowl.isPlayable()) {
            bowl.addPin(Pin.of(countOfDownPins));
        }
        List<FrameStatus> frameList = toFrameList(bowl);
        List<Integer> scoreList = toScoreList(bowl);

        assertAll(
                () -> assertThat(toPinList(bowl))
                        .isEqualTo(singletonList(10)),
                () -> assertThat(frameList)
                        .isEqualTo(singletonList(STRIKE)),
                () -> assertThat(scoreList)
                        .isEqualTo(emptyList())
        );
    }

    @Test
    @DisplayName("SPARE 만 finish 까지 치는 시나리오 테스트")
    void scenario_spare_finish() {
        int countOfDownPins = 5;
        while (bowl.isPlayable()) {
            bowl.addPin(Pin.of(countOfDownPins));
        }
        List<FrameStatus> frameList = toFrameList(bowl);
        List<Integer> scoreList = toScoreList(bowl);

        assertAll(
                () -> assertThat(toPinList(bowl))
                        .isEqualTo(Arrays.asList(5, 5)),
                () -> assertThat(frameList)
                        .isEqualTo(singletonList(SPARE)),
                () -> assertThat(scoreList)
                        .isEqualTo(emptyList())
        );
    }

    @Test
    @DisplayName("MISS 만 finish 까지 치는 시나리오 테스트")
    void scenario_miss_finish() {
        int countOfDownPins = 3;
        while (bowl.isPlayable()) {
            bowl.addPin(Pin.of(countOfDownPins));
        }
        List<FrameStatus> frameList = toFrameList(bowl);
        List<Integer> scoreList = toScoreList(bowl);

        assertAll(
                () -> assertThat(toPinList(bowl))
                        .isEqualTo(Arrays.asList(3, 3)),
                () -> assertThat(frameList)
                        .isEqualTo(singletonList(MISS)),
                () -> assertThat(scoreList)
                        .isEqualTo(singletonList(6))
        );
    }


    @Test
    @DisplayName("STRIKE 만 game_over 까지 치는 시나리오 테스트")
    void scenario_strike_game_over() {
        int countOfDownPins = 10;
        while (bowl.isPlayable()) {
            play(countOfDownPins, bowl);
        }
        List<Integer> pinList = new LinkedList<>();
        List<FrameStatus> frameList = toFrameList(bowl);
        List<Integer> scoreList = toScoreList(bowl);

        for (int i = 0; i < MAX_FRAME_NUMBER + 2; i++) {
            pinList.add(countOfDownPins);
        }
        assertAll(
                () -> assertThat(toPinList(bowl))
                        .isEqualTo(pinList),
                () -> assertThat(frameList)
                        .isEqualTo(Arrays.asList(STRIKE, STRIKE, STRIKE, STRIKE, STRIKE, STRIKE, STRIKE, STRIKE, STRIKE, STRIKE)),
                () -> assertThat(scoreList)
                        .isEqualTo(Arrays.asList(30, 60, 90, 120, 150, 180, 210, 240, 270, 300)),
                () -> assertThat(scoreList.size())
                        .isEqualTo(MAX_FRAME_NUMBER),
                () -> assertThat(frameList.size())
                        .isEqualTo(MAX_FRAME_NUMBER)
        );
    }

    @Test
    @DisplayName("SPARE 만 game_over 까지 치는 시나리오 테스트")
    void scenario_spare_game_over() {
        int countOfDownPins = 5;
        while (bowl.isPlayable()) {
            play(countOfDownPins, bowl);
        }
        List<Integer> pinList = new LinkedList<>();
        List<FrameStatus> frameList = toFrameList(bowl);
        List<Integer> scoreList = toScoreList(bowl);

        for (int i = 0; i < MAX_FRAME_NUMBER * 2 + 1; i++) {
            pinList.add(countOfDownPins);
        }
        assertAll(
                () -> assertThat(toPinList(bowl))
                        .isEqualTo(pinList),
                () -> assertThat(frameList)
                        .isEqualTo(Arrays.asList(SPARE, SPARE, SPARE, SPARE, SPARE, SPARE, SPARE, SPARE, SPARE, SPARE)),
                () -> assertThat(scoreList)
                        .isEqualTo(Arrays.asList(15, 30, 45, 60, 75, 90, 105, 120, 135, 150))
        );
    }

    @Test
    @DisplayName("MISS 만 game_over 까지 치는 시나리오 테스트")
    void scenario_miss_game_over() {
        int countOfDownPins = 3;
        while (bowl.isPlayable()) {
            play(countOfDownPins, bowl);
        }
        List<Integer> pinList = new LinkedList<>();
        List<FrameStatus> frameList = toFrameList(bowl);
        List<Integer> scoreList = toScoreList(bowl);

        for (int i = 0; i < MAX_FRAME_NUMBER * 2; i++) {
            pinList.add(countOfDownPins);
        }
        assertAll(
                () -> assertThat(toPinList(bowl))
                        .isEqualTo(pinList),
                () -> assertThat(frameList)
                        .isEqualTo(Arrays.asList(MISS, MISS, MISS, MISS, MISS, MISS, MISS, MISS, MISS, MISS)),
                () -> assertThat(scoreList)
                        .isEqualTo(Arrays.asList(6, 12, 18, 24, 30, 36, 42, 48, 54, 60))
        );
    }

    private void play(int countOfDownPins, Bowl bowl) {
        while (bowl.isPlayable()) {
            bowl.addPin(Pin.of(countOfDownPins));
        }
        bowl.update();
    }
}
