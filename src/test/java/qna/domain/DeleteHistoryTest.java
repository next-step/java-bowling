package qna.domain;

import static qna.domain.QuestionTest.Q1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeleteHistoryTest {

	@Test
	@DisplayName("삭제 기록을 남긴다")
	void deleteHistory(){
		DeleteHistory deleteHistory = new DeleteHistory();
		deleteHistory.deleteHistory(Q1);

	}

}
