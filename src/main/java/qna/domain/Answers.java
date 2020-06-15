package qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private List<Answer> answers;

    private Answers(final List<Answer> answers) {
        this.answers = new ArrayList<>(answers);
    }

    public static Answers of(final List<Answer> answers) {
        return new Answers(answers);
    }

    public List<DeleteHistory> delete(final User user) {
        return answers.stream()
                .map(answer -> answer.delete(user))
                .collect(Collectors.toList());
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
