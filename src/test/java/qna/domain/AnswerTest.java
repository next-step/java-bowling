package qna.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class AnswerTest {
	public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
	public static final Answer A2 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
	private Answers answers;
	private User loginUser;

	@BeforeEach
	void setUp() {
		answers = new Answers();
		answers.addAnswer(A1);
		answers.addAnswer(A2);
		loginUser = UserTest.JAVAJIGI;
	}

	@DisplayName("답변을 삭제하고 해당 답변을 반환한다.")
	@Test
	void 답변을_삭제하고_이를_반환한다() throws CannotDeleteException {
		Answer deletedTheFirst = A1.delete();
		Answer deletedTheSecond = A2.delete();

		assertAll(
			() -> assertThat(deletedTheFirst).isEqualTo(A1),
			() -> assertThat(deletedTheSecond).isEqualTo(A2)
		);
	}

	@DisplayName("답변을 삭제하고 DeleteHistory 리스트를 반환한다.")
	@Test
	void 전체_답변을_삭제하고_삭제_리스트를_반환한다() throws CannotDeleteException {
		List<DeleteHistory> deleteHistories = answers.deleteAll(loginUser);
		assertAll(
			() -> assertThat(deleteHistories).isInstanceOf(List.class),
			() -> assertThat(deleteHistories.get(0)).isInstanceOf(DeleteHistory.class)
		);
	}
}
