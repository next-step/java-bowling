package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

class DeleteHistoriesTest {

	@DisplayName("생성자 테스트")
	@Test
	void testAddAndConstruct() {
		assertThat(new DeleteHistories(List.of(new DeleteHistory()))).isEqualTo(new DeleteHistories(new DeleteHistory()));
	}

	@DisplayName("두 DeleteHistories 합치기 테스트")
	@Test
	void concat() {
		DeleteHistory given1 = new DeleteHistory(ContentType.ANSWER, 1L, JAVAJIGI, LocalDateTime.now());
		DeleteHistory given2 = new DeleteHistory(ContentType.ANSWER, 2L, SANJIGI, LocalDateTime.now());
		DeleteHistories deleteHistory1 = new DeleteHistories(given1);
		DeleteHistories deleteHistory2 = new DeleteHistories(given2);
		List<DeleteHistory> deleteHistories = List.of(given1, given2);
		assertThat(DeleteHistories.concat(deleteHistory1, deleteHistory2)).isEqualTo(new DeleteHistories(deleteHistories));
	}
}
