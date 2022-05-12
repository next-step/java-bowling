package qna.domain;

import java.util.Collections;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void delete(User loginUser) {
        answers.stream()
                .forEach(answer -> answer.delete(loginUser));
    }

    public void record(DeleteHistories deleteHistories) {
        answers.stream()
                .forEach(answer -> deleteHistories.add(answer.createRecord()));
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }
}