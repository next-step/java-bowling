package bowling.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.exception.InvalidProgressBowlingException;
import bowling.domain.frame.FinalFrame;

@DisplayName("볼링")
class BowlingTest {

	@DisplayName("투구")
	@Test
	void pitch() {
		// given
		Bowling bowling = Bowling.of();

		// when
		while (bowling.possiblePitch()) {
			bowling = bowling.pitch(10);
		}

		// then
		assertThat(bowling.getFrames().size()).isEqualTo(10);
		assertThat(bowling.getFrames().get(9)).isInstanceOf(FinalFrame.class);
	}

	@DisplayName("투구 - 종료된 볼링")
	@Test
	void play_invalid() {
		// given
		Bowling bowling = Bowling.of();
		while (bowling.possiblePitch()) {
			bowling = bowling.pitch(10);
		}

		// when
		final Bowling finalBowling = bowling;
		assertThrows(InvalidProgressBowlingException.class, () -> finalBowling.pitch(10));

		// then

	}
}
