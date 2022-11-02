package step1.qna.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers;

    private Answers(final List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers from(final List<Answer> answers){
        return new Answers(answers);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<DeleteHistory> deleteAll(final User loginUser) {
        return answers.stream()
            .map(answer -> answer.delete(loginUser))
            .collect(Collectors.toList());
    }
}
