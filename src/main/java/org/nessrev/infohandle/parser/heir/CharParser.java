package org.nessrev.infohandle.parser.heir;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nessrev.infohandle.entity.CharLeaf;
import org.nessrev.infohandle.entity.TextComponent;
import org.nessrev.infohandle.entity.TextComposite;
import org.nessrev.infohandle.exception.TextException;
import org.nessrev.infohandle.parser.TextParser;
import org.nessrev.infohandle.type.TextType;

public class CharParser extends TextParser {
  private final Logger logger = LogManager.getLogger();

  @Override
  public TextComponent parse(String text) throws TextException {
    checkText(text);

    TextComposite chars = new TextComposite(TextType.CHAR);
    for (char symbol : text.toCharArray()) {
      chars.add(new CharLeaf(symbol));
    }
    return chars;
  }

  @Override
  public boolean canParse(String text) {
    return true;
  }
}
