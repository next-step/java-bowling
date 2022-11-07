package qna.domain;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<DeleteHistory> delete(User deleteBy) {
        return answers.stream()
                .map(answer -> answer.delete(deleteBy))
                .collect(toUnmodifiableList());
    }

}
