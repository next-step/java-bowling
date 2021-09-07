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
    private final List<Answer> answers = new ArrayList<>();

    public Answers() {

    }

    public Answers(List<Answer> answers) {
        this.answers.addAll(answers);
    }

    public boolean deletableBy(User loginUser) {
        if (answers.isEmpty()) {
            return true;
        }
        return answers.stream()
                .allMatch(answer -> answer.isOwner(loginUser));
    }

    public void deleteAll(User loginUser) throws CannotDeleteException {
        if (!deletableBy(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        answers.forEach(Answer::delete);
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public void addAll(Answers answers) {
        this.answers.addAll(answers.getAnswers());
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public void toQuestion(Question question) {
        this.answers.forEach(answer -> answer.toQuestion(question));
    }

}
