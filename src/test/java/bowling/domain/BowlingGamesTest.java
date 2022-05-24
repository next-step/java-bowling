package bowling.domain;

import bowling.domain.frame.Frames;
import bowling.domain.frame.LastFrame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BowlingGamesTest {

    @ParameterizedTest
    @MethodSource("bowlingGamesCreateProvider")
    @DisplayName("BowlingGames 생성을 테스트 한다")
    void create(List<BowlingGame> bowlingGames) {
        assertThat(BowlingGames.create(bowlingGames)).isInstanceOf(BowlingGames.class);
    }

    static Stream<Arguments> bowlingGamesCreateProvider() {
        return Stream.of(
                arguments(List.of(
                        BowlingGame.of(
                                Frames.create(List.of(
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                LastFrame.create()
                                        )
                                ), Player.create("aaa")),
                        BowlingGame.of(
                                Frames.create(List.of(
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                LastFrame.create()
                                        )
                                ), Player.create("bbb")),
                        BowlingGame.of(
                                Frames.create(List.of(
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                NormalFrame.create(),
                                                LastFrame.create()
                                        )
                                ), Player.create("ccc"))
                ))
        );
    }

}