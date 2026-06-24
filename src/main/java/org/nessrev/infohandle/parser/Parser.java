package org.nessrev.infohandle.parser;

import org.nessrev.infohandle.entity.TextComponent;
import org.nessrev.infohandle.exception.TextException;

public interface Parser {
  String PARAGRAPH_CHECK_REGEX = "\n";

  String SENTENCE_CHECK_REGEX = ".*[.!?…](?:\\s+|$).*";

  String WORD_FIND_REGEX = "\\p{L}+(?:-\\p{L}+)*";

  TextComponent parse(String text) throws TextException;
  void setNext(Parser parser);
  boolean canParse(String text);
}
