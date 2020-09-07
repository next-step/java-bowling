package qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {
	private final List<DeleteHistory> deleteHistories;

	public DeleteHistories() {
		this.deleteHistories = new ArrayList<>();
	}

	public DeleteHistories(DeleteHistory deleteHistory) {
		List<DeleteHistory> deleteHistories = new ArrayList<>();
		deleteHistories.add(deleteHistory);
		this.deleteHistories = deleteHistories;
	}
	//for test
	DeleteHistories(List<DeleteHistory> deleteHistories) {
		this.deleteHistories = deleteHistories;
	}

	public DeleteHistories add(DeleteHistory deleteHistory) {
		this.deleteHistories.add(deleteHistory);
		return this;
	}

	public List<DeleteHistory> transformToCollection() {
		return deleteHistories;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DeleteHistories that = (DeleteHistories) o;
		return deleteHistories.equals(that.deleteHistories);
	}

	@Override
	public int hashCode() {
		return Objects.hash(deleteHistories);
	}
}
