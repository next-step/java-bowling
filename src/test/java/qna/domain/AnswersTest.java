package qna.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class AnswersTest {

	@Test
	@DisplayName("작성자와 로그인한 사용자가 다를 경우 예외를 던진다.")
	public void Given_CheckOwner_Then_Throw() {
		Answers answers = new Answers();
		answers.add(AnswerTest.A1);
		answers.add(AnswerTest.A2);

		assertThatThrownBy(() -> answers.softDelete(UserTest.JAVAJIGI))
			.isInstanceOf(CannotDeleteException.class);
	}

	@Test
	@DisplayName("작성자와 로그인한 사용자가 같을 경우 예외를 던지지 않는다.")
	public void Given_CheckOwner_Then_NotThrow() {
		Answers answers = new Answers();
		answers.add(AnswerTest.A1);

		answers.softDelete(UserTest.JAVAJIGI);

		assertDoesNotThrow(() -> answers.softDelete(UserTest.JAVAJIGI));
	}

	@Test
	@DisplayName("isDeleted 필드가 true 로 업데이트된다.")
	public void Given_SoftDelete_Then_Deleted() {
		Answers answers = new Answers();
		answers.add(AnswerTest.A1);

		answers.softDelete(UserTest.JAVAJIGI);

		assertThat(AnswerTest.A1.isDeleted()).isTrue();
	}
}
