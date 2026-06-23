package org.nessrev.infohandle.entity;

import org.nessrev.infohandle.type.TextType;

import java.util.List;
import java.util.Objects;

public class CharLeaf implements TextComponent{
  private final char value;
  private final TextType type = TextType.CHAR;

  public CharLeaf(char value) {
    this.value = value;
  }

  @Override
  public void add(TextComponent component) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void remove(TextComponent component) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<TextComponent> getChildren() {
    throw new UnsupportedOperationException();
  }

  @Override
  public TextType getType() {
    return type;
  }

  @Override
  public String recover() {
    return String.valueOf(value);
  }

  @Override
  public String getValue() {
    return toString();
  }

  @Override
  public boolean isLeaf() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CharLeaf charLeaf = (CharLeaf) o;
    return value == charLeaf.value && type == charLeaf.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, type);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
