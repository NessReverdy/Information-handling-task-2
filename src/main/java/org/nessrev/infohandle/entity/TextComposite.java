package org.nessrev.infohandle.entity;

import org.nessrev.infohandle.type.TextType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextComposite implements TextComponent {
  private final List<TextComponent> components = new ArrayList<>();
  private final TextType type;

  public TextComposite(TextType type) {
    this.type = type;
  }


  @Override
  public void add(TextComponent component) {
    if (component != null){
      components.add(component);
    }
  }

  @Override
  public void remove(TextComponent component) {
    if (component != null){
      components.remove(component);
    }
  }

  @Override
  public List<TextComponent> getChildren() {
    return List.copyOf(components);
  }

  @Override
  public TextType getType() {
    return type;
  }

  @Override
  public String recover() {
    return "";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TextComposite that = (TextComposite) o;
    return Objects.equals(components, that.components) && type == that.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(components, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TextComposite{");
    sb.append("type=").append(type);
    sb.append(", components=[");

    for (int i = 0; i < components.size(); i++) {
      sb.append(components.get(i));

      if (i < components.size() - 1) {
        sb.append(", ");
      }
    }

    sb.append("]}");
    return sb.toString();
  }
}
