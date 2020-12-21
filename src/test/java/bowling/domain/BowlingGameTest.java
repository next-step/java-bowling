package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BowlingGameTest {
    private static final String PLAYER_NAME_1 = "AAA";
    private static final String PLAYER_NAME_2 = "BBB";
    private static final String PLAYER_NAME_3 = "CCC";

    private PlayerNames playerNames;

    @BeforeEach
    public void init() {
        List<PlayerName> playerNameList = Arrays.asList(PlayerName.valueOf(PLAYER_NAME_1), PlayerName.valueOf(PLAYER_NAME_2), PlayerName.valueOf(PLAYER_NAME_3));
        playerNames = PlayerNames.of(playerNameList);
    }

    @ParameterizedTest
    @MethodSource("pitchingsAndExpectedPlayer")
    public void turnTest(List<Integer> pitchings, String expectedPlayerName) {
        //given
        BowlingGame bowlingGame = BowlingGame.init(playerNames);

        //when
        for (Integer knockDownPins : pitchings) {
            bowlingGame.setKnockDownPins(KnockDownPins.valueOf(knockDownPins));
        }

        //then
        Player currentPlayer = bowlingGame.getCurrentPlayer();
        assertThat(currentPlayer.getPlayerName()).isEqualTo(PlayerName.valueOf(expectedPlayerName));
    }

    private static Stream<Arguments> pitchingsAndExpectedPlayer() {
        return Stream.of(
                Arguments.of(Collections.emptyList(), PLAYER_NAME_1),
                Arguments.of(Arrays.asList(10), PLAYER_NAME_2),
                Arguments.of(Arrays.asList(10, 10), PLAYER_NAME_3),
                Arguments.of(Arrays.asList(10, 10, 10), PLAYER_NAME_1),
                Arguments.of(Arrays.asList(5), PLAYER_NAME_1),
                Arguments.of(Arrays.asList(5, 5), PLAYER_NAME_2),
                Arguments.of(Arrays.asList(5, 5, 5), PLAYER_NAME_2),
                Arguments.of(Arrays.asList(5, 5, 5, 5), PLAYER_NAME_3),
                Arguments.of(Arrays.asList(3, 3), PLAYER_NAME_2)
        );
    }

    @ParameterizedTest
    @MethodSource("pitchingsAndIsEnd")
    public void isEndTest(List<Integer> pitchings, boolean expectedIsEnd) {
        //given
        BowlingGame bowlingGame = BowlingGame.init(playerNames);

        //when
        for (Integer knockDownPins : pitchings) {
            bowlingGame.setKnockDownPins(KnockDownPins.valueOf(knockDownPins));
        }

        //then
        assertThat(bowlingGame.isEnd()).isEqualTo(expectedIsEnd);
    }

    private static Stream<Arguments> pitchingsAndIsEnd() {
        return Stream.of(
                Arguments.of(Collections.emptyList(), false),
                Arguments.of(Arrays.asList(
                        10, 10, 10, // frame1
                        10, 10, 10, // frame2
                        10, 10, 10, // frame3
                        10, 10, 10, // frame4
                        10, 10, 10, // frame5
                        10, 10, 10, // frame6
                        10, 10, 10, // frame7
                        10, 10, 10, // frame8
                        10, 10, 10 // frame9
                ), false),
                Arguments.of(Arrays.asList(
                        10, 10, 10, // frame1
                        10, 10, 10, // frame2
                        10, 10, 10, // frame3
                        10, 10, 10, // frame4
                        10, 10, 10, // frame5
                        10, 10, 10, // frame6
                        10, 10, 10, // frame7
                        10, 10, 10, // frame8
                        10, 10, 10, // frame9
                        10, 10, 10, 10, 10, 10, 10, 10, 10 // frame10
                ), true),
                Arguments.of(Arrays.asList(
                        10, 10, 10, // frame1
                        10, 10, 10, // frame2
                        10, 10, 10, // frame3
                        10, 10, 10, // frame4
                        10, 10, 10, // frame5
                        10, 10, 10, // frame6
                        10, 10, 10, // frame7
                        10, 10, 10, // frame8
                        10, 10, 10, // frame9
                        1, 1, 2, 2, 3, 3 // frame10
                ), true)
        );
    }
}
