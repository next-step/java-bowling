package com.jaeyeonling.bowling.domain;

import com.jaeyeonling.bowling.domain.pins.KnockdownPins;
import com.jaeyeonling.bowling.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGamesTest {

    private List<User> users;
    private BowlingGames bowlingGames;

    @BeforeEach
    void setUp() {
        users = List.of(User.of("AAA"), User.of("DDD"), User.of("CCC"));
        bowlingGames = BowlingGames.with(users);
    }

    @DisplayName("첫 볼링 게임은 첫 유저부터 시작한다.")
    @Test
    void firstBowlingGame() {
        // given
        final BowlingGame bowlingGame = bowlingGames.getCurrentBowlingGame();

        // when
        final boolean isFirstUser = bowlingGame.getUser().equals(users.get(0));

        // then
        assertThat(isFirstUser).isTrue();
    }

    @DisplayName("스트라이크인 경우 다음 유저로 넘어간다.")
    @Test
    void strikeCurrentBowlingGame() {
        // given
        bowl(KnockdownPins.MAX);
        final BowlingGame bowlingGame = bowlingGames.getCurrentBowlingGame();

        // when
        final boolean isSecondUser = bowlingGame.getUser().equals(users.get(1));

        // then
        assertThat(isSecondUser).isTrue();
    }

    @DisplayName("스트라이크가 아닌 경우 한번 더 던질 수 있다.")
    @ParameterizedTest()
    @ValueSource(ints = {KnockdownPins.MIN_VALUE, 5, KnockdownPins.MAX_VALUE - 1})
    void missCurrentBowlingGame(final int knockdownPins) {
        // given
        bowl(KnockdownPins.valueOf(knockdownPins));
        final BowlingGame bowlingGame = bowlingGames.getCurrentBowlingGame();

        // when
        final boolean isFirstUser = bowlingGame.getUser().equals(users.get(0));

        // then
        assertThat(isFirstUser).isTrue();
    }

    @DisplayName("모든 볼링 게임이 끝나기 전 까지 플레이 할 수 있다.")
    @Test
    void canPlayNotComplete() {
        for (int i = 0, countOfPlay = users.size() * 11; i < countOfPlay; i++) {
            // given
            bowl(KnockdownPins.MAX);

            // when
            boolean canPlay = bowlingGames.canPlay();

            // then
            assertThat(canPlay).isTrue();
        }
    }

    @DisplayName("모든 볼링 게임이 끝나면 플레이할 수 없다.")
    @Test
    void canPlayComplete() {
        // given
        for (int i = 0, countOfPlay = users.size() * 12; i < countOfPlay; i++) {
            bowl(KnockdownPins.MAX);
        }

        // when
        boolean canPlay = bowlingGames.canPlay();

        // then
        assertThat(canPlay).isFalse();
    }

    private void bowl(final KnockdownPins knockdownPins) {
        bowlingGames.getCurrentBowlingGame().bowl(knockdownPins);
    }
}
