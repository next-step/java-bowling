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
        return questionDeleteHistories(question, histories);
    }

    public static DeleteHistories of(Answers answers) {
        List<DeleteHistory> histories = new ArrayList<>();
        return answersDeleteHistories(answers, histories);
    }

    private static DeleteHistories questionDeleteHistories(Question question, List<DeleteHistory> histories) {
        histories.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
        return new DeleteHistories(histories).add(question.getAnswers());
    }

    private static DeleteHistories answersDeleteHistories(Answers answers, List<DeleteHistory> histories) {
        answers.getAnswers()
                .forEach(answer -> histories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now())));
        return new DeleteHistories(histories);
    }

    public DeleteHistories add(Question question) {
        List<DeleteHistory> histories = new ArrayList<>(this.histories);
        return questionDeleteHistories(question, histories);
    }

    public DeleteHistories add(Answers answers) {
        List<DeleteHistory> histories = new ArrayList<>(this.histories);
        return answersDeleteHistories(answers, histories);
    }

    public List<DeleteHistory> getHistories() {
        return Collections.unmodifiableList(histories);
    }

    public int size() {
        return histories.size();
    }
}
