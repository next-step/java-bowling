package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Embeddable
public class Answers extends AbstractEntity{

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    public Answers() {}

    private Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    private Answers setDeleted(boolean deleted) {
        answers.forEach(answer -> answer.setDeleted(deleted));
        return this;
    }

    public boolean isOwner(User writer) {
        return answers.stream()
                .allMatch(answer -> answer.isOwner(writer));
    }

    public void delete(User loginUser) throws CannotDeleteException {
        if(!isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        setDeleted(true);
    }

    public List<Answer> answers() {
        return Collections.unmodifiableList(answers);
    }
}
