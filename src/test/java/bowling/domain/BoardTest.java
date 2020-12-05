package bowling.domain;

import bowling.domain.frame.FrameEnum;
import bowling.dto.FrameDto;
import bowling.dto.ScoreDto;
import bowling.exception.BadCountOfPinsException;
import bowling.exception.PinException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static bowling.asset.Const.MAX_FRAME_NO;
import static bowling.domain.frame.FrameEnum.*;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

class BoardTest {
    private Pins pins;
    private Board board;

    @BeforeEach
    void setUp() {
        pins = new Pins();
        board = new Board();
    }

    private void addPin(Pin pin) {
        pins.add(pin);
        board.update(pins);
    }

    private List<FrameEnum> toFrameList(Board board) {
        return board.exportBoardDto()
                .getFramesDto()
                .getFrames()
                .stream()
                .map(FrameDto::getFrameEnum)
                .collect(toList());
    }

    private List<Integer> toScoreList(Board board) {
        return board.exportBoardDto()
                .getScoresDto()
                .getScores()
                .stream()
                .map(ScoreDto::getScore)
                .collect(toList());
    }

    @ParameterizedTest
    @DisplayName("핀 갯수가 음수이면, PinException 이 발생한다.")
    @CsvSource(value = {"-8$3", "10$-1", "11$3", "4$12"}, delimiter = '$')
    void scenario_negative(int pin1, int pin2) {
        assertThatExceptionOfType(PinException.class)
                .isThrownBy(() -> {
                    addPin(Pin.of(pin1));
                    addPin(Pin.of(pin2));
                }).withMessage("핀의 개수는 0 이상 10 이하여야 합니다.");
    }

    @ParameterizedTest
    @DisplayName("적절하지 않은 핀 갯수이면, BadCountOfPinsException 이 발생한다.")
    @CsvSource(value = {"9$2", "4$7"}, delimiter = '$')
    void scenario_badCountOfPins(int pin1, int pin2) {
        assertThatExceptionOfType(BadCountOfPinsException.class)
                .isThrownBy(() -> {
                    addPin(Pin.of(pin1));
                    addPin(Pin.of(pin2));
                }).withMessage("한 프레임에서 쓰러트린 핀의 개수는 0 이상 10 이하여야 합니다.");
    }

    @Test
    @DisplayName("볼링을 치지 않은 시나리오 테스트")
    void scenario_empty() {
        assertAll(
                () -> assertThat(toFrameList(board))
                        .isEqualTo(emptyList()),
                () -> assertThat(toScoreList(board))
                        .isEqualTo(emptyList())
        );
    }


    @Test
    @DisplayName("STRIKE 를 두번 치는 시나리오 테스트")
    void scenario_strike() {
        addPin(Pin.of(10));
        addPin(Pin.of(10));
        assertAll(
                () -> assertThat(toFrameList(board))
                        .isEqualTo(Arrays.asList(STRIKE, STRIKE)),
                () -> assertThat(toScoreList(board))
                        .isEqualTo(emptyList())
        );
    }

    @Test
    @DisplayName("STRIKE, STRIKE, SPARE 를 치는 시나리오 테스트")
    void scenario_strike_spare() {
        addPin(Pin.of(10));
        addPin(Pin.of(10));

        addPin(Pin.of(1));
        addPin(Pin.of(9));
        assertAll(
                () -> assertThat(toFrameList(board))
                        .isEqualTo(Arrays.asList(STRIKE, STRIKE, SPARE)),
                () -> assertThat(toScoreList(board))
                        .isEqualTo(Arrays.asList(21, 41))
        );
    }

    @Test
    @DisplayName("STRIKE, SPARE, MISS 를 치는 시나리오 테스트")
    void scenario_strike_spare_miss() {
        addPin(Pin.of(10));

        addPin(Pin.of(1));
        addPin(Pin.of(9));

        addPin(Pin.of(4));
        addPin(Pin.of(5));
        assertAll(
                () -> assertThat(toFrameList(board))
                        .isEqualTo(Arrays.asList(STRIKE, SPARE, MISS)),
                () -> assertThat(toScoreList(board))
                        .isEqualTo(Arrays.asList(20, 34, 43))
        );
    }

    @Test
    @DisplayName("UNFINISHED 시나리오 테스트")
    void scenario_unfinished() {
        addPin(Pin.of(10));

        addPin(Pin.of(1));
        addPin(Pin.of(9));

        addPin(Pin.of(4));
        addPin(Pin.of(5));

        addPin(Pin.of(10));

        addPin(Pin.of(8));
        assertAll(
                () -> assertThat(toFrameList(board))
                        .isEqualTo(Arrays.asList(STRIKE, SPARE, MISS, STRIKE, UNFINISHED)),
                () -> assertThat(toScoreList(board))
                        .isEqualTo(Arrays.asList(20, 34, 43))
        );
    }

    @Test
    @DisplayName("STRIKE 100번 추가하는 시나리오 테스트")
    void scenario_strike_100() {
        for (int i = 0; i < 100; i++) {
            addPin(Pin.of(10));
        }
        List<FrameEnum> frameList = toFrameList(board);
        List<Integer> scoreList = toScoreList(board);
        assertAll(
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
        for (int i = 0; i < 100; i++) {
            addPin(Pin.of(5));
        }
        List<FrameEnum> frameList = toFrameList(board);
        List<Integer> scoreList = toScoreList(board);
        assertAll(
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
        for (int i = 0; i < 100; i++) {
            addPin(Pin.of(3));
        }
        List<FrameEnum> frameList = toFrameList(board);
        List<Integer> scoreList = toScoreList(board);
        assertAll(
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
