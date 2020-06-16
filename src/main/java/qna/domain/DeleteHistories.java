package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteHistories {
    private final List<DeleteHistory> histories;

    public DeleteHistories() {
        this.histories = new ArrayList<>();
    }

    public List<DeleteHistory> getHistories() {
        return Collections.unmodifiableList(histories);
    }

    public void makeQuestionHistory(Question question) {
        histories.add(
                make(ContentType.QUESTION, question.getId(), question.getWriter())
        );
    }

    public void makeAnswersHistory(Answers answers) {
        answers.getAnswers().stream()
                .map(a -> make(ContentType.ANSWER, a.getId(), a.getWriter()))
                .collect(Collectors.toList())
                .forEach(h -> histories.add(h));
    }

    private DeleteHistory make(ContentType type, Long id, User user) {
        return new DeleteHistory(type, id, user, LocalDateTime.now());
    }
}
