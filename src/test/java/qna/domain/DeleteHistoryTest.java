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
		Question q = QuestionTest.Q1;
		DeleteHistory expected = new DeleteHistory(ContentType.QUESTION, q.getId(), q.getWriter(), LocalDateTime.now());

		DeleteHistory deleteHistory = DeleteHistory.of(q);

		assertThat(deleteHistory).isEqualTo(expected);
	}

	@Test
	void 생성_of_answer() {
		Answer a = AnswerTest.A1;
		DeleteHistory expected = new DeleteHistory(ContentType.ANSWER, a.getId(), a.getWriter(), LocalDateTime.now());

		DeleteHistory deleteHistory = DeleteHistory.of(a);

		assertThat(deleteHistory).isEqualTo(expected);
	}
}