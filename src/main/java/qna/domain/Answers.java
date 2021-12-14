package qna.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import qna.CannotDeleteException;

public class Answers extends FirstClassCollection<Answer> {
    public static final Answers EMPTY = new Answers(Collections.emptyList());

    private Answers(List<Answer> answers) {
        super(answers);
    }

    public static Answers of(List<Answer> answers) {
        if (answers == null) {
            throw new IllegalArgumentException("invalid answers: cannot be null");
        }

        return new Answers(answers);
    }

    public Answers append(Answer answer) {
        if (answer == null) {
            throw new IllegalArgumentException("invalid answer: cannot be null");
        }

        return new Answers(Stream.concat(stream(), Stream.of(answer))
                .collect(Collectors.toList()));
    }

    public DeleteHistories delete(User loginUser) throws CannotDeleteException {
        if (stream().anyMatch(answer -> !answer.isOwner(loginUser))) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        return DeleteHistories.of(stream()
                .peek(answer -> answer.setDeleted(true))
                .map(DeleteHistory::from)
                .collect(Collectors.toList()));
    }
}
