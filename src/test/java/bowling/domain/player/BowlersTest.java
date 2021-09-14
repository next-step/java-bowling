package bowling.domain.player;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("볼러들")
class BowlersTest {

	@DisplayName("볼러 교체")
	@Test
	void changeBowler() {
		// given
		final Bowlers bowlers = Bowlers.of(Arrays.asList("pp1", "pp2", "pp3", "pp4", "pp5", "pp6"));
		Bowler currentBowler = bowlers.first();

		// when

		for (int i = 0; i < bowlers.getBowlers().size() * 2; i++) {
			System.out.println("### before=" + currentBowler.getName());
			currentBowler = bowlers.changeBowler(currentBowler);
			System.out.println("### after =" + currentBowler.getName());
		}
	}
}
