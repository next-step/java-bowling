package qna.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers;

    private Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers of(List<Answer> answers) {
        return new Answers(answers);
    }

    public List<DeleteHistory> delete(User user) {
        return answers.stream()
                .map(answer -> answer.delete(user))
                .collect(Collectors.toList());
    }
}
