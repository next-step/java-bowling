package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void areOwner(User loginUser) throws CannotDeleteException {
        if(answers.stream()
                .anyMatch(answer -> !answer.isOwner(loginUser))) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");

        }
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void deleteAll(List<DeleteHistory> deletes) {
        answers.forEach(answer -> {
            answer.delete();
            deletes.add(new DeleteHistory(answer, answer.getWriter()));
        });
    }
}
