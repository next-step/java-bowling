package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by seungwoo.song on 2022-11-08
 */
class DeleteHistoriesTest {

	@Test
	void 생성() {
		Question q = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
		q.addAnswer(AnswerTest.A1);
		q.addAnswer(AnswerTest.A2);

		DeleteHistories deleteHistories = DeleteHistories.of(q);
		assertThat(deleteHistories).hasSize(3);
	}
}