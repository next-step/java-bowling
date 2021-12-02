package qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswersTest {
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
