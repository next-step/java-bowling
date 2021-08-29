package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Answers {
    @OneToMany(mappedBy = "question")
    private final List<Answer> answers = new ArrayList<>();

    public void checkDeleteAutorization(User loginUser) {
        for (Answer answer: answers) {
            if (answer.isOwner(loginUser)) {
                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.")
            }
        }
    }
}
