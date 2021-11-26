package qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswersTest {
	@DisplayName("모든 답변글의 답변자가 전달받은 User 와 동일한 경우 true 반환")
	@Test
	void isAllOwnerTrue() {
		// given
		Answers answers = new Answers();
		answers.add(AnswerTest.A1);
		answers.add(AnswerTest.A1);

		// when
		boolean result = answers.isAllOwner(UserTest.JAVAJIGI);

		// then
		assertThat(result).isTrue();
	}

	@DisplayName("모든 답변글의 답변자가 전달받은 User 와 동일하지 않을 경우 false 반환")
	@Test
	void isAllOwnerFalse() {
		// given
		Answers answers = new Answers();
		answers.add(AnswerTest.A1);
		answers.add(AnswerTest.A2);

		// when
		boolean result = answers.isAllOwner(UserTest.JAVAJIGI);

		// then
		assertThat(result).isFalse();
	}

	@DisplayName("등록된 모든 답변글을 삭제후 반환값 검증")
	@Test
	void deleteAll() {
		// given
		Answers answers = new Answers();
		answers.add(AnswerTest.A1);
		answers.add(AnswerTest.A1);

		User loginUser = UserTest.JAVAJIGI;

		// when
		List<DeleteHistory> deleteHistories = answers.deleteAll(loginUser);

		// then
		assertThat(deleteHistories).hasSize(2);
	}
}
