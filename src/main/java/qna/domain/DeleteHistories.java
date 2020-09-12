package qna.domain;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeleteHistories {
	private final List<DeleteHistory> deleteHistories;

	private DeleteHistories(List<DeleteHistory> deleteHistories) {
		this.deleteHistories = deleteHistories;
	}

	public static DeleteHistories of(DeleteHistory deleteHistory) {
		return new DeleteHistories(Arrays.asList(deleteHistory));
	}

	public static DeleteHistories of(List<DeleteHistory> deleteHistories) {
		return new DeleteHistories(deleteHistories);
	}

	public DeleteHistories merge(DeleteHistories other) {
		return of(Stream.of(this.deleteHistories, other.deleteHistories)
						.flatMap(Collection::stream)
						.collect(Collectors.toList()));
	}

	public List<DeleteHistory> getDeleteHistories() {
		return Collections.unmodifiableList(deleteHistories);
	}
}
