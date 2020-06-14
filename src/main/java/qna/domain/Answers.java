package qna.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> answers;

    private Answers(final List<Answer> answers) {
        this.answers = Collections.unmodifiableList(answers);
    }

    public static Answers of(final List<Answer> answers) {
        return new Answers(answers);
    }

    public List<DeleteHistory> delete(final User user) {
        verifyDeletable(user);

        return answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }

    private void verifyDeletable(final User user) {
        answers.forEach(answer -> answer.verifyOwner(user));
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
