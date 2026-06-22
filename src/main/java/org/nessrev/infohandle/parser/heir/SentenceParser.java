package org.nessrev.infohandle.parser.heir;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nessrev.infohandle.entity.TextComponent;
import org.nessrev.infohandle.entity.TextComposite;
import org.nessrev.infohandle.exception.TextException;
import org.nessrev.infohandle.parser.Parser;
import org.nessrev.infohandle.parser.TextParser;
import org.nessrev.infohandle.type.TextType;

public class SentenceParser extends TextParser {
  private final Logger logger = LogManager.getLogger();

  public SentenceParser(Parser parser) {
    setNext(parser);
  }


  @Override
  public TextComponent parse(String text) throws TextException {
    checkText(text);

    if (!canParse(text)) {
      return next(text);
    }

    String[] sentences = text.split(SENTENCE_SPLIT_REGEX);
    logger.info("Sentence found: {}", sentences.length);

    TextComposite fullText = new TextComposite(TextType.PARAGRAPH);

    for (String sentence : sentences) {
      TextComposite paragraphComponent = new TextComposite(TextType.SENTENCE);
      paragraphComponent.add(next(sentence));

      fullText.add(paragraphComponent);
    }

    return fullText;
  }

  @Override
  public boolean canParse(String text) {
    return text.matches(SENTENCE_CHECK_REGEX);
  }
}
