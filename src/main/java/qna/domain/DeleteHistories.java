package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

    private final List<DeleteHistory> histories = new ArrayList<>();


    public void add(DeleteHistory deleteHistory) {
        histories.add(deleteHistory);
    }

    public void add(Question question) {
        histories.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
        add(question.getAnswers());
    }

    public void add(Answers answers) {
        answers.getAnswers()
                .forEach(answer -> histories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now())));
    }

    public List<DeleteHistory> getHistories() {
        return Collections.unmodifiableList(histories);
    }

    public int size() {
        return histories.size();
    }
}
