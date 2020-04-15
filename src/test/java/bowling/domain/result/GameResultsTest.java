package bowling.domain.result;

import bowling.domain.PlayerName;
import bowling.domain.frame.Frame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class GameResultsTest {
    @DisplayName("GameResult 목록을 주면 GameResults 객체를 생성된다.")
    @Test
    void create() {
        //given
        GameResult gameResult1 = new GameResult(PlayerName.of("SSJ"), Frame.createTenFrames());
        GameResult gameResult2 = new GameResult(PlayerName.of("YSH"), Frame.createTenFrames());

        //when, then
        assertThatCode(() -> {
            new GameResults(Arrays.asList(gameResult1, gameResult2));
        }).doesNotThrowAnyException();
    }

    @DisplayName("같은 이름의 PlayerName이 존재하면 예외를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"ssj", "Ssj", "sSj", "ssJ", "SSj", "sSJ", "SSJ"})
    void throwExceptionWhenPlayerNamesAreDuplicated(String otherPlayerName) {
        //given
        GameResult gameResult1 = new GameResult(PlayerName.of("SSJ"), Frame.createTenFrames());
        GameResult gameResult2 = new GameResult(PlayerName.of(otherPlayerName), Frame.createTenFrames());

        //when, then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new GameResults(Arrays.asList(gameResult1, gameResult2));
        });
    }
}