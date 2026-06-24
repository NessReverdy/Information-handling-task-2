package org.nessrev.infohandle.parser;

import org.nessrev.infohandle.entity.CharLeaf;
import org.nessrev.infohandle.entity.TextComponent;
import org.nessrev.infohandle.entity.TextComposite;
import org.nessrev.infohandle.exception.TextException;
import org.nessrev.infohandle.type.TextType;

import java.util.regex.Pattern;

public class WordParser extends TextParser {
  public WordParser(Parser parser) {
    setNext(parser);
  }

  @Override
  public TextComponent parse(String text) throws TextException {
    checkText(text);

    if (!canParse(text)) {
      return next(text);
    }

    TextComposite wordComposite = new TextComposite(TextType.WORD);
    for (char symbol : text.toCharArray()) {
      wordComposite.add(new CharLeaf(symbol));
    }
    return wordComposite;
  }


  @Override
  public boolean canParse(String text) {
    return Pattern.compile(WORD_FIND_REGEX).matcher(text).find();
  }
}
