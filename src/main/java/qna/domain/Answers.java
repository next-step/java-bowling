package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers() {
    }

    public int size() {
        return answers.size();
    }

    public void delete(User loginUser) {
        for (Answer answer : answers) {
            answer.delete(loginUser);
        }
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public List<DeleteHistory> addDeleteHistories() {
        return answers.stream()
                      .filter(answer -> answer.isDeleted())
                      .map(answer -> new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter()))
                      .collect(Collectors.toList());
    }
}
