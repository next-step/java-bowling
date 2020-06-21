package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
	private List<DeleteHistory> deleteHistories = new ArrayList<>();
	
	public DeleteHistories(DeleteHistory deleteHistory) {
		deleteHistories.add(deleteHistory);
	}
}
