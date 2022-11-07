package qna.domain;


import static qna.domain.QuestionTest.Q1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeleteHistoriesTest {

	@Test
	@DisplayName("deleteHistory 보관하는 일급 컬렉션 DeleteHistories 생성한다.")
	void deleteHistories(){
		DeleteHistories deleteHistories = new DeleteHistories();
		DeleteHistory deleteHistory = Q1.questionHistory();

		deleteHistories.addHistory(deleteHistory);

		Assertions.assertThat(deleteHistories)
			.isEqualTo(new DeleteHistories(deleteHistory));
	}

}
