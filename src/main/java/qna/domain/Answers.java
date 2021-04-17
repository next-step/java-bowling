package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public List<Answer> list() {
        return Collections.unmodifiableList(answers);
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void checkWriters(User writer) throws CannotDeleteException {
        boolean isNotAllowedWriter = answers.stream()
                .anyMatch(answer -> !answer.isSameWriter(writer));

        if (isNotAllowedWriter) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }
}
