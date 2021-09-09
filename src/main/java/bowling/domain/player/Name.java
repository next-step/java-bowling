package bowling.domain.player;

import java.util.Objects;

public class Name {
    private String value;

    public Name(String value){
        if(value.length() > 3){
            throw new IllegalArgumentException("이름은 3자리 이내로 입력해주세요.");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
