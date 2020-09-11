package qna.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

public class UserTest {
	public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
	public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

	@Test
	public void sameUserTest() {
		assertThat(JAVAJIGI).isEqualTo(new User(1L, "javajigi", "password", "name", "javajigi@slipp.net"));
	}

	@Test
	public void otherUserTest() {
		assertThat(SANJIGI).isNotEqualTo(new User(3L, "sanjigi", "password", "name", "sanjigi@slipp.net"));
	}
}
