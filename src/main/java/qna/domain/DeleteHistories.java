package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {
	private List<DeleteHistory> deleteHistories = new ArrayList<>();
	
	public DeleteHistories(DeleteHistory deleteHistory) {
		deleteHistories.add(deleteHistory);
	}
	public DeleteHistories(List<DeleteHistory> deleteHistories) {
		this.deleteHistories=deleteHistories;
	}
	public void add(DeleteHistories deleteHistories) {
		this.deleteHistories.addAll(deleteHistories.deleteHistories);
	}
	public List<DeleteHistory> getHistories() {
		return Collections.unmodifiableList(deleteHistories);
	}
}
