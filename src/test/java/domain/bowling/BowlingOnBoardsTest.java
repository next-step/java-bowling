package domain.bowling;

import domain.UserName;
import domain.UserNumber;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
class BowlingOnBoardsTest {

	@Test
	void 입력한_유저수보다_많은_이름이_입력되면_에러가_난다() {
		// given
		BowlingOnBoards bowlingOnBoards = new BowlingOnBoards(UserNumber.of(2));

		// when
		bowlingOnBoards.enroll(UserName.of("ABC"));
		bowlingOnBoards.enroll(UserName.of("EDF"));

		// then
		assertThatThrownBy(() -> {
			bowlingOnBoards.enroll(UserName.of("GHI"));
		})
		.isInstanceOf(IllegalStateException.class);
	}

}
