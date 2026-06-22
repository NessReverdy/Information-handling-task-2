package org.nessrev.infohandle.parser;

import org.nessrev.infohandle.entity.TextComponent;
import org.nessrev.infohandle.exception.TextException;

public abstract class TextParser implements Parser {
  private Parser next;

  @Override
  public void setNext(Parser next) {
    this.next = next;
  }

  protected TextComponent next(String text) throws TextException {
    if (next != null) {
      return next.parse(text);
    }
    throw new TextException("No parser found");
  }

  protected void checkText (String text) throws TextException {
    if (text == null || text.isBlank()) {
      throw new TextException("Text cannot be null or blank");
    }
  }
}
