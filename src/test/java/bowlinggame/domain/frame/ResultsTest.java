package bowlinggame.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import bowlinggame.domain.frame.result.Miss;
import bowlinggame.domain.frame.result.Results;
import bowlinggame.domain.frame.result.Spare;
import org.junit.Before;
import org.junit.Test;

public class ResultsTest {

	private Results results;

	@Before
	public void setUp() throws Exception {
		results = new Results();
	}

	@Test
	public void 쓰러진_핀_개수로_첫투구_결과기록() {
		assertThat(results.record(Pin.fromKnockedPinCount(5))).isInstanceOf(Miss.class);
		assertThat(results.isAllKnockedDown()).isFalse();
	}

	@Test
	public void 쓰러진_핀_개수로_두번째투구_결과기록() {
		results.record(Pin.fromKnockedPinCount(3));
		assertThat(results.record(Pin.fromKnockedPinCount(10))).isInstanceOf(Spare.class);
		assertThat(results.isAllKnockedDown()).isTrue();
	}
}