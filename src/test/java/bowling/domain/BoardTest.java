package bowling.domain;

import bowling.dto.FrameDto;
import bowling.dto.ScoreDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BoardTest {

    private Board board;
    private Rolls rolls;
    private final Subject<Rolls> subject = new Subject<Rolls>() {
        @Override
        Rolls get() {
            return rolls;
        }

        @Override
        void execute() {
            notifyObservers();
        }
    };

    @BeforeEach
    void setUp() {
        board = new Board();
        rolls = new Rolls();
        subject.register(board);
    }

    private void addRoll(Roll roll) {
        rolls.add(roll);
        subject.execute();
    }

    private List<FrameEnum> toFrameEnumList(Board board) {
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

    @Test
    @DisplayName("볼링을 치지 않은 시나리오 테스트")
    void scenario_empty_update() {
        assertAll(
                () -> assertThat(toFrameEnumList(board))
                        .isEqualTo(emptyList()),
                () -> assertThat(toScoreList(board))
                        .isEqualTo(emptyList())
        );
    }

    @Test
    @DisplayName("볼링을 치지 않은 시나리오 테스트")
    void scenario_empty() {
        assertAll(
                () -> assertThat(toFrameEnumList(board))
                        .isEqualTo(emptyList()),
                () -> assertThat(toScoreList(board))
                        .isEqualTo(emptyList())
        );
    }


    @Test
    @DisplayName("STRIKE 를 치는 시나리오 테스트")
    void scenario_strike() {
        addRoll(Roll.of(10));
        assertAll(
                () -> assertThat(toFrameEnumList(board))
                        .isEqualTo(Arrays.asList(FrameEnum.STRIKE)),
                () -> assertThat(toScoreList(board))
                        .isEqualTo(emptyList())
        );
    }

    @Test
    @DisplayName("STRIKE, SPARE 를 치는 시나리오 테스트")
    void scenario_strike_spare() {
        addRoll(Roll.of(10));

        addRoll(Roll.of(1));
        addRoll(Roll.of(9));
        assertAll(
                () -> assertThat(toFrameEnumList(board))
                        .isEqualTo(Arrays.asList(FrameEnum.STRIKE, FrameEnum.SPARE)),
                () -> assertThat(toScoreList(board))
                        .isEqualTo(Arrays.asList(20))
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
                () -> assertThat(toFrameEnumList(board))
                        .isEqualTo(Arrays.asList(FrameEnum.STRIKE, FrameEnum.SPARE, FrameEnum.MISS)),
                () -> assertThat(toScoreList(board))
                        .isEqualTo(Arrays.asList(20, 34, 43))
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
                () -> assertThat(toFrameEnumList(board))
                        .isEqualTo(Arrays.asList(FrameEnum.STRIKE, FrameEnum.SPARE, FrameEnum.MISS, FrameEnum.UNFINISHED)),
                () -> assertThat(toScoreList(board))
                        .isEqualTo(Arrays.asList(20, 34, 43))
        );
    }
}
