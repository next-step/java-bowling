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


// HELP: Board 와 테스트 내용이 거의 겹치는데, 이벤트를 잘 전달하는지만 테스트할 수 는 없을까?
class BoardObserverTest {

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

    private void addRoll(Roll roll) {
        rolls.add(roll);
        subject.execute();
    }

    @BeforeEach
    void setUp() {
        board = new Board();
        rolls = new Rolls();
        subject.register(new BoardObserver(board));
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
    void scenario_empty() {
        assertAll(
                () -> assertThat(board.prefix())
                        .isEqualTo(1),
                () -> assertThat(board.isStrike())
                        .isFalse(),
                () -> assertThat(board.isSpare())
                        .isFalse(),
                () -> assertThat(board.isFrameFinished())
                        .isTrue(),
                () -> assertThat(
                        toFrameEnumList(board)
                ).isEqualTo(emptyList()),
                () -> assertThat(
                        toScoreList(board)
                ).isEqualTo(emptyList())
        );
    }


    @Test
    @DisplayName("STRIKE 를 치는 시나리오 테스트")
    void scenario_strike() {
        addRoll(Roll.of(10));
        assertAll(
                () -> assertThat(board.prefix())
                        .isEqualTo(2),
                () -> assertThat(board.isStrike())
                        .isTrue(),
                () -> assertThat(board.isSpare())
                        .isFalse(),
                () -> assertThat(board.isFrameFinished())
                        .isTrue(),
                () -> assertThat(
                        toFrameEnumList(board)
                ).isEqualTo(Arrays.asList(FrameEnum.STRIKE)),
                () -> assertThat(
                        toScoreList(board)
                ).isEqualTo(emptyList())
        );
    }

    @Test
    @DisplayName("STRIKE, SPARE 를 치는 시나리오 테스트")
    void scenario_strike_spare() {
        addRoll(Roll.of(10));

        addRoll(Roll.of(1));
        addRoll(Roll.of(9));
        assertAll(
                () -> assertThat(board.prefix())
                        .isEqualTo(3),
                () -> assertThat(board.isStrike())
                        .isFalse(),
                () -> assertThat(board.isSpare())
                        .isTrue(),
                () -> assertThat(board.isFrameFinished())
                        .isTrue(),
                () -> assertThat(
                        toFrameEnumList(board)
                ).isEqualTo(Arrays.asList(FrameEnum.STRIKE, FrameEnum.SPARE)),
                () -> assertThat(
                        toScoreList(board)
                ).isEqualTo(Arrays.asList(20))
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
                () -> assertThat(board.prefix())
                        .isEqualTo(4),
                () -> assertThat(board.isStrike())
                        .isFalse(),
                () -> assertThat(board.isSpare())
                        .isFalse(),
                () -> assertThat(board.isFrameFinished())
                        .isTrue(),
                () -> assertThat(
                        toFrameEnumList(board)
                ).isEqualTo(Arrays.asList(FrameEnum.STRIKE, FrameEnum.SPARE, FrameEnum.MISS)),
                () -> assertThat(
                        toScoreList(board)
                ).isEqualTo(Arrays.asList(20, 34, 43))
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
                () -> assertThat(board.prefix())
                        .isEqualTo(4),
                () -> assertThat(board.isStrike())
                        .isFalse(),
                () -> assertThat(board.isSpare())
                        .isFalse(),
                () -> assertThat(board.isFrameFinished())
                        .isFalse(),
                () -> assertThat(
                        toFrameEnumList(board)
                ).isEqualTo(Arrays.asList(FrameEnum.STRIKE, FrameEnum.SPARE, FrameEnum.MISS, FrameEnum.UNFINISHED)),
                () -> assertThat(
                        toScoreList(board)
                ).isEqualTo(Arrays.asList(20, 34, 43))
        );
    }
}
