package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoriesTest {

	@DisplayName("add 메서드와 생성자 테스트")
	@Test
	void testAddAndConstruct() {
		DeleteHistories deleteHistories = new DeleteHistories();
		deleteHistories.add(new DeleteHistory());
		assertThat(deleteHistories).isEqualTo(new DeleteHistories(new DeleteHistory()));
	}

}
