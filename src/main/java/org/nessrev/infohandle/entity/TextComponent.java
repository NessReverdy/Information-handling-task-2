package org.nessrev.infohandle.entity;

import org.nessrev.infohandle.type.TextType;

import java.util.List;

public interface TextComponent {
  void add(TextComponent component);

  void remove(TextComponent component);

  List<TextComponent> getChildren();

  TextType getType();

  String recover();
}
