package qna.domain;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Embeddable;
import qna.CannotDeleteException;

@Embeddable
public class Answers {

    private List<Answer> answers;

    protected Answers() {
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<DeleteHistory> deleteAll(User writer) throws CannotDeleteException {
        return answers.stream()
            .map(answer -> answer.deleteAnswer(writer))
            .collect(Collectors.toList());
    }

}
