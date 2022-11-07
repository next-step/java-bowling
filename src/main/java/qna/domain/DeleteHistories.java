package qna.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {

	private List<DeleteHistory> deleteHistories;

	public DeleteHistories() {
		this(new ArrayList<>());
	}

	public DeleteHistories(DeleteHistory deleteHistory) {
		this(Arrays.asList(deleteHistory));
	}

	private DeleteHistories(List<DeleteHistory> deleteHistories) {
		this.deleteHistories = deleteHistories;
	}

	public static DeleteHistories deleteHistories(List<DeleteHistory> deleteHistories) {
		return new DeleteHistories(deleteHistories);
	}

	public void addHistory(DeleteHistory questionHistory) {
		deleteHistories.add(questionHistory);
	}

	public void deleteAnswerHistories(Question question) {
		deleteHistories.addAll(question.answersHistory());
	}

	public List<DeleteHistory> deleteHistories() {
		return deleteHistories;
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
