package qna.domain.question;

import org.hibernate.annotations.Where;
import qna.domain.answer.Answer;
import qna.domain.user.User;
import qna.dto.DeletePipe;
import qna.exception.CannotDeleteException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Embeddable
public class AnswersInQuestion implements Iterable<Answer> {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers;

    protected AnswersInQuestion() {
        answers = new ArrayList<>();
    }

    public boolean isOwner(User writer) {
        return answers.stream()
                .allMatch(iAnswer -> iAnswer.isOwner(writer));
    }

    public void delete(DeletePipe deletePipe) {
        for (Answer iAnswer : answers) {
            iAnswer.delete(deletePipe);
        }
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    @Override
    public Iterator<Answer> iterator() {
        return answers.iterator();
    }
}
