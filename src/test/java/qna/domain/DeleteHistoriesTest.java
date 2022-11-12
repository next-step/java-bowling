package qna.domain;


import static qna.domain.Question.newQuestion;
import static qna.domain.UserTest.JAVAJIGI;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeleteHistoriesTest {

	private Question Q1;

	@BeforeEach
	void setUp() {
		Q1 = newQuestion(1L, "title1", "contents1").writeBy(JAVAJIGI);
	}
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
