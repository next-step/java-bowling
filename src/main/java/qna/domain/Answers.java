package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Answers implements Iterable<Answer> {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> deleteAll() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.delete());
        }

        return deleteHistories;
    }

    public boolean hasOtherAnswers(User owner) {
        List<Answer> otherAnswers = getOtherAnswers(owner);
        return !otherAnswers.isEmpty();
    }

    private List<Answer> getOtherAnswers(User owner) {
        return answers.stream()
                .filter(answer -> !answer.isOwner(owner))
                .collect(Collectors.toList());
    }

    @Override public Iterator<Answer> iterator() {
        return answers.iterator();
    }
}
