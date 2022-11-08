package qna.domain;

import qna.domain.embeded.Answers;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by seungwoo.song on 2022-11-08
 */
public class DeleteHistories extends AbstractList<DeleteHistory> {

	private final List<DeleteHistory> values;

	public DeleteHistories(List<DeleteHistory> values) {
		this.values = values;
	}

	public static DeleteHistories of(Question question) {
		Objects.requireNonNull(question);
		return new DeleteHistories(toDeleteHistories(question));
	}

	private static List<DeleteHistory> toDeleteHistories(Question question) {
		List<DeleteHistory> deleteHistories = new ArrayList<>();

		deleteHistories.add(DeleteHistory.of(question));
		deleteHistories.addAll(toDeleteHistoryList(question.getAnswers()));

		return deleteHistories;
	}

	private static List<DeleteHistory> toDeleteHistoryList(Answers question) {
		return question.getAnswers()
			.stream()
			.map(DeleteHistory::of)
			.collect(Collectors.toList());
	}

	@Override
	public DeleteHistory get(int index) {
		return values.get(index);
	}

	@Override
	public int size() {
		return values.size();
	}

	@Override
	public boolean add(DeleteHistory deleteHistory) {
		return values.add(deleteHistory);
	}

	// ============================================================================

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		DeleteHistories that = (DeleteHistories)o;
		return Objects.equals(values, that.values);
	}

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), values);
	}

	@Override public String toString() {
		return "DeleteHistories{" +
			"values=" + values +
			'}';
	}
}
