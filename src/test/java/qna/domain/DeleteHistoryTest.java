package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by seungwoo.song on 2022-11-08
 */
class DeleteHistoryTest {

	public static DeleteHistory D1 = new DeleteHistory(ContentType.QUESTION, UserTest.JAVAJIGI.getId(), QuestionTest.Q1.getWriter(), LocalDateTime.now());

	@BeforeEach
	void setUp() {
		D1 = new DeleteHistory(ContentType.QUESTION, UserTest.JAVAJIGI.getId(), QuestionTest.Q1.getWriter(), LocalDateTime.now());
	}

	@Test
	void 생성_of_question() {
		LocalDateTime createDate = LocalDateTime.now();
		Question q = QuestionTest.Q1;
		DeleteHistory expected = new DeleteHistory(ContentType.QUESTION, q.getId(), q.getWriter(), createDate);

		DeleteHistory deleteHistory = DeleteHistory.of(q, createDate);

		assertThat(deleteHistory).isEqualTo(expected);
	}

	@Test
	void 생성_of_answer() {
		LocalDateTime createDate = LocalDateTime.now();
		Answer a = AnswerTest.A1;
		DeleteHistory expected = new DeleteHistory(ContentType.ANSWER, a.getId(), a.getWriter(), createDate);

		DeleteHistory deleteHistory = DeleteHistory.of(a, createDate);

		assertThat(deleteHistory).isEqualTo(expected);
	}
}