package bowling.domain.game;

import bowling.domain.frame.FrameNumber;
import bowling.domain.frame.Frames;
import bowling.domain.state.PinTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;

import static bowling.domain.player.PlayerTest.PLAYER_ONE;
import static bowling.domain.player.PlayerTest.PLAYER_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GamesTest {
    @ParameterizedTest
    @NullAndEmptySource
    void Games는_frames없이_생성_될_경우_예외를_발생_시킨다(List<Game> games) {
        assertThatThrownBy(() -> {
            new Games(games);
        }).isInstanceOf(RuntimeException.class);
    }


    @Test
    void bowl은_현재_프레임에_점수를_추가하고_프레임을_추가_시킨다() {
        Game game = new Game(PLAYER_ONE, Frames.initialize());

        game.bowl(PinTest.TEN);

        assertThat(game.currentFrameNumber()).isEqualTo(new FrameNumber(2));
    }

    @Test
    void bowl은_game의_bowl을_호출하고_턴을_변경시킨다() {
        Games games = new Games(List.of(new Game(PLAYER_ONE, Frames.initialize()), new Game(PLAYER_TWO, Frames.initialize())));

        games.bowl(PinTest.TEN);

        assertThat(games.whoseTurn()).isEqualTo(PLAYER_TWO);
    }
}
