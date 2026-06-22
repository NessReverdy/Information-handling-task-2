package org.nessrev.infohandle.parser.heir;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nessrev.infohandle.entity.TextComponent;
import org.nessrev.infohandle.entity.TextComposite;
import org.nessrev.infohandle.exception.TextException;
import org.nessrev.infohandle.parser.Parser;
import org.nessrev.infohandle.parser.TextParser;
import org.nessrev.infohandle.type.TextType;

public class ParagraphParser extends TextParser {
  private final Logger logger = LogManager.getLogger();

  public ParagraphParser(Parser parser) {
    setNext(parser);
  }

  @Override
  public TextComponent parse(String text) throws TextException {
    checkText(text);

    if (!canParse(text)) {
      return next(text);
    }

    String[] paragraphs = text.split(PARAGRAPH_SPLIT_REGEX);
    logger.info("Paragraphs found: {}", paragraphs.length);

    TextComposite fullText = new TextComposite(TextType.TEXT);

    for (String paragraph : paragraphs) {
      fullText.add(next(paragraph));
    }

    return fullText;
  }

  @Override
  public boolean canParse(String text) {
    return text.contains(PARAGRAPH_CHECK_REGEX);
  }
}
