package qna.domain;

import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public boolean checkWriter(User writer) throws CannotDeleteException {
        if (hasAnotherWriter(writer)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        return true;
    }

    private boolean hasAnotherWriter(User writer) {
        return answers.stream().anyMatch(answer -> !answer.isOwner(writer));
    }

    public List<DeleteHistory> toDeleteHistories() {
        return answers.stream().map(answer -> {
            answer.setDeleted(true);
            return new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now());
        }).collect(Collectors.toList());
    }
}
