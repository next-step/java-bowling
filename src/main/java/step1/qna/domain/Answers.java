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

    public void validateAnswersAuthentication(User loginUser) {
        this.answers.stream()
                .forEach(answer -> answer.validateAnswerAuthentication(loginUser));
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<DeleteHistory> deleteAll() {
        return answers.stream()
            .map(answer -> answer.delete())
            .collect(Collectors.toList());
    }
}
