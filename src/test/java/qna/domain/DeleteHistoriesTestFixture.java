package qna.domain;

import java.util.List;

public class DeleteHistoriesTestFixture {

	public static DeleteHistories fromList(List<DeleteHistory> deleteHistories) {
		return new DeleteHistories(deleteHistories);
	}
}
