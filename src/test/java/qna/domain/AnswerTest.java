package qna.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static qna.domain.ContentType.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class AnswerTest {
	public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
	public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

	@DisplayName("전달받은 유저와 답변자가 다를경우 예외 발생")
	@Test
	void deleteWithInvalidWriter() {
		// given
		Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
		User loginUser = UserTest.SANJIGI;

		// when then
		assertThatExceptionOfType(CannotDeleteException.class)
			.isThrownBy(() -> answer.delete(loginUser));
	}

	@DisplayName("Answer 삭제 처리시 상태와 반환값 검증")
	@Test
	void delete() {
		// given
		Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
		User loginUser = UserTest.JAVAJIGI;

		// when
		DeleteHistory deleteHistory = answer.delete(loginUser);

		// then
		assertAll(
			() -> assertThat(answer.isDeleted()).isTrue(),
			() -> assertThat(deleteHistory).isEqualTo(new DeleteHistory(ANSWER, answer.getId(), answer.getWriter()))
		);
	}
}
