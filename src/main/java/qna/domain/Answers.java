package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Answers {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers;

    public Answers() {
    }

    public Answers(List<Answer> answerList) {
        this.answers = new ArrayList<>(answerList);
    }

    public int size() {
        return this.answers.size();
    }

    public List<DeleteHistory> delete(User questionUser) {
        return answers.stream()
                .map(answer -> answer.delete(questionUser))
                .collect(Collectors.toList());
    }

    // TODO: 임시(서비스 인터페이스 유지용)
    public List<Answer> getValues() {
        return new ArrayList<>(this.answers);
    }
}
