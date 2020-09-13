package bowling.domain.player;

import bowling.exception.BowlingException;
import org.junit.jupiter.api.Test;

import static bowling.domain.player.Player.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

	@Test
	void construct() {
		String given = "KTY";
		assertThat(new Player(given)).isEqualTo(new Player(given));
	}

	@Test
	void failConstructionByTooLongName() {
		String given = "KTYS";
		assertThatThrownBy(() -> new Player(given))
				.isInstanceOf(BowlingException.class)
				.hasMessage(String.format(EXCEED_MAX_NAME_LENGTH, MAX_LENGTH));
	}

	@Test
	void failConstructionByNoneAlphabetName() {
		String given = "테드";
		assertThatThrownBy(() -> new Player(given))
				.isInstanceOf(BowlingException.class)
				.hasMessage(NAME_SHOULD_BE_ALPHABET);
	}

}
