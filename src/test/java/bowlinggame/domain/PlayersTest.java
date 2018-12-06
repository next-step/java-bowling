package bowlinggame.domain;

import static org.assertj.core.api.Assertions.assertThat;

import bowlinggame.domain.frame.FrameNumber;
import org.junit.Before;
import org.junit.Test;

public class PlayersTest {

	private Players players;

	@Before
	public void setUp() throws Exception {
		players = Players.fromComma("hon,pob,jk");
	}

	@Test
	public void 콤마로_구분된_문자로_플레이어_생성() {
		assertThat(players.getPlayers()).hasSize(3);
	}

	@Test
	public void 다음_플레이어() {
		Player currentPlayer = players.getFirstPlayer();

		assertThat(players.next(currentPlayer)).isEqualTo(Player.of("pob"));
	}

	@Test
	public void 마지막_플레이어의_다음() {
		Player currentPlayer = players.getFirstPlayer(); // hon
		currentPlayer = players.next(currentPlayer); // pob
		currentPlayer = players.next(currentPlayer); // jk

		assertThat(players.next(currentPlayer)).isEqualTo(Player.of("hon"));
	}

	@Test
	public void 전체_플레이어가_프레임_종료() {
		FrameNumber frameNumber = FrameNumber.first();
		Player currentPlayer = play(players.getFirstPlayer());
		currentPlayer = play(currentPlayer);
		currentPlayer = play(currentPlayer);

		assertThat(players.isFrameOverAllPlayer(frameNumber)).isTrue();
	}

	private Player play(Player player) {
		player.roll(10);
		return players.next(player);
	}
}