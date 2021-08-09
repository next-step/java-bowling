package qna.domain.question;

import org.hibernate.annotations.Where;
import qna.domain.answer.Answer;
import qna.domain.history.DeleteHistory;
import qna.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<DeleteHistory> delete(User loginUser) {
        return answers.stream()
                .map(iAnswer -> iAnswer.delete(loginUser))
                .collect(Collectors.toList());
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    @Override
    public Iterator<Answer> iterator() {
        return answers.iterator();
    }
}
