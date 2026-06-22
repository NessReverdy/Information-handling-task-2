package org.nessrev.infohandle.parser;

import org.nessrev.infohandle.entity.TextComponent;
import org.nessrev.infohandle.exception.TextException;

public interface Parser {
  String PARAGRAPH_SPLIT_REGEX = "\\n+";
  String PARAGRAPH_CHECK_REGEX = "\n";

  String SENTENCE_SPLIT_REGEX = "(?<=[.!?…])\\s+";
  String SENTENCE_CHECK_REGEX = ".*[.!?…](?:\\s+|$).*";

  String LEXEME_CHECK_REGEX = "\\S+";

  String WORD_CHECK_REGEX = "^\\p{L}+(?:-\\p{L}+)*$";
  String WORD_FIND_REGEX = "\\p{L}+(?:-\\p{L}+)*";

  String PUNCTUATION_CHECK_REGEX = "^\\S*\\p{Punct}\\S*$";

  TextComponent parse(String text) throws TextException;
  void setNext(Parser parser);
  boolean canParse(String text);
}
