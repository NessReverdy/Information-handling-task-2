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
    StringBuilder builder = new StringBuilder();

    for (TextComponent component : components) {
      builder.append(component.recover());
    }

    return builder.toString();
  }

  @Override
  public boolean isLeaf() {
    return false;
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
    return buildString(0);
  }

  private String buildString(int level) {
    StringBuilder sb = new StringBuilder();

    sb.append("  ".repeat(level))
      .append(type)
      .append("\n");

    for (TextComponent component : components) {
      if (component instanceof TextComposite composite) {
        sb.append(composite.buildString(level + 1));
      } else {
        sb.append("  ".repeat(level + 1))
          .append(component)
          .append("\n");
      }
    }

    return sb.toString();
  }
}
