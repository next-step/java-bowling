package bowling;

import java.util.Objects;

public class KnockDownNumber {

  private Integer value;

  public KnockDownNumber() {
  }

  public KnockDownNumber(Integer value) {
    this.value = value;
  }

  public int getIntValue() {
    return value == null ? 0 : value;
  }

  public boolean isNull() {
    return value == null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KnockDownNumber that = (KnockDownNumber) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return "NumberOfKnockedDown{" +
        "value=" + value +
        '}';
  }
}
