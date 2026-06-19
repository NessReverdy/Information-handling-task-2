package org.nessrev.infohandle.entity;

import org.nessrev.infohandle.type.TextType;

import java.util.List;

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
}
