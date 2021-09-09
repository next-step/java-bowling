package qna.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = Objects.requireNonNull(answers);
    }

    public void deleteAllBy(User loginUser) {
        answers.forEach(answer -> answer.deleteBy(loginUser));
    }

    public List<DeleteHistory> toDeleteHistories() {
        return answers.stream()
                .map(Answer::toDeleteHistory)
                .collect(Collectors.toList());
    }

}
