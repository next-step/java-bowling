package qna.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.IntPredicate;

public class DeleteHistories {

	private List<DeleteHistory> deleteHistories;

	public DeleteHistories(){
		this(new ArrayList<>());
	}

	public DeleteHistories(DeleteHistory deleteHistory){
		this(Arrays.asList(deleteHistory));
	}

	private DeleteHistories(List<DeleteHistory> deleteHistories) {
		this.deleteHistories = deleteHistories;
	}

	public void addHistory(DeleteHistory questionHistory) {
		deleteHistories.add(questionHistory);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DeleteHistories that = (DeleteHistories) o;
		return Objects.equals(deleteHistories, that.deleteHistories);
	}

	@Override
	public int hashCode() {
		return Objects.hash(deleteHistories);
	}
}
