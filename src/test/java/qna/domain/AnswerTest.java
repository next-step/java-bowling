package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class AnswerTest {
	public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
	public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

	@Test
	void 로그인한_사용자와_답변자가_같은경우만_삭제가능() {
		assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI))
			.isInstanceOf(CannotDeleteException.class)
			.hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
	}

	@Test
	void 삭제_성공() throws CannotDeleteException {
		assertThat(A1.delete(UserTest.JAVAJIGI)).isEqualTo(
			new DeleteHistory(ContentType.ANSWER, A1.getId(), UserTest.JAVAJIGI, null)
		);
	}
}
