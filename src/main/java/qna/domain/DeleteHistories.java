package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

    private final List<DeleteHistory> histories;

    private DeleteHistories(List<DeleteHistory> histories) {
        this.histories = histories;
    }

    public static DeleteHistories of(Question question) {
        List<DeleteHistory> histories = new ArrayList<>();
        histories.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
        return new DeleteHistories(histories).add(question.getAnswers());
    }

    public static DeleteHistories of(Answers answers) {
        List<DeleteHistory> histories = new ArrayList<>();
        answers.getAnswers()
                .forEach(answer -> histories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now())));
        return new DeleteHistories(histories);
    }

    public DeleteHistories add(Question question) {
        histories.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
        add(question.getAnswers());
        return new DeleteHistories(histories);
    }

    public DeleteHistories add(Answers answers) {
        answers.getAnswers()
                .forEach(answer -> histories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now())));
        return new DeleteHistories(histories);
    }

    public List<DeleteHistory> getHistories() {
        return Collections.unmodifiableList(histories);
    }

    public int size() {
        return histories.size();
    }
}
