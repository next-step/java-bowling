package qna.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static qna.domain.ContentType.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {
	public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
	public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

	@DisplayName("Answer 삭제 처리시 상태와 반환값 검증")
	@Test
	void delete() {
		// given
		Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

		// when
		DeleteHistory deleteHistory = answer.delete();

		// then
		assertAll(
			() -> assertThat(answer.isDeleted()).isTrue(),
			() -> assertThat(deleteHistory).isEqualTo(new DeleteHistory(ANSWER, answer.getId(), answer.getWriter()))
		);
	}
}
