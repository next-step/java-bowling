package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class GameTest {
    @DisplayName("정상 종료 테스트")
    @Test
    public void finishTest() {
        //given
        List<Shot> shotList = Arrays.asList(new Shot(10), new Shot(10), new Shot(10), new Shot(10), new Shot(10), new Shot(10), new Shot(10), new Shot(10), new Shot(10), new Shot(10), new Shot(10), new Shot(10));
        Game game = Game.of(new User("abc"));
        //when
        shotList.stream().forEach((shot) -> game.play(shot));

        //then
        assertThat(game.isFinished()).isEqualTo(true);
    }

    @DisplayName("종료 후에 또 플레이시 테스트")
    @Test
    public void playAfterFinishGameTest() {
        //given
        List<Shot> shotList = Arrays.asList(new Shot(10), new Shot(10), new Shot(10), new Shot(10), new Shot(10), new Shot(10), new Shot(10), new Shot(10), new Shot(10), new Shot(10), new Shot(10), new Shot(10));
        Game game = Game.of(new User("abc"));
        //when
        shotList.stream().forEach((shot) -> game.play(shot));

        //then
        assertThatIllegalStateException().isThrownBy(()-> game.play(new Shot(1)));
    }
}
