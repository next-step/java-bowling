package qna.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class AnswerTest {
	public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
	public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

	@Test
	@DisplayName("다른사람의 답변이 있으면 삭제가 불가능 하다.")
	public void checkAuthority() {
		assertThrows(CannotDeleteException.class, () ->
			A1.checkAuthority(UserTest.SANJIGI)
		);
	}

	@Test
	@DisplayName("질문을 삭제시 삭제 상태가 변경된다.")
	public void setDeleteAnswer() {
		Answer answer = A1.setDeleted(true);

		assertThat(answer.isDeleted()).isTrue();

		Answer answer2 = A2.setDeleted(true);

		assertThat(answer2.isDeleted()).isTrue();
	}

	@Test
	@DisplayName("질문을 삭제시 히스토리를 저장한다.")
	public void saveDeleteHistory() {
		DeleteHistory deleteHistory = A2.deleteHistory();

		assertThat(deleteHistory).isEqualTo(
			new DeleteHistory(ContentType.ANSWER, A2.getId(), A2.getWriter(), deleteHistory.getCreateDate()));
	}
}
