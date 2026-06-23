package org.nessrev.infohandle.parser.heir;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nessrev.infohandle.entity.TextComponent;
import org.nessrev.infohandle.entity.TextComposite;
import org.nessrev.infohandle.exception.TextException;
import org.nessrev.infohandle.parser.Parser;
import org.nessrev.infohandle.parser.TextParser;
import org.nessrev.infohandle.type.TextType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends TextParser {
  private final Logger logger = LogManager.getLogger();
  private static final Pattern PARAGRAPH_PATTERN = Pattern.compile(PARAGRAPH_MATCH_REGEX, Pattern.DOTALL);

  public ParagraphParser(Parser parser) {
    setNext(parser);
  }

  @Override
  public TextComponent parse(String text) throws TextException {
    checkText(text);

    if (!canParse(text)) {
      return next(text);
    }

    logger.info("Parsing paragraphs");

    TextComposite fullText = new TextComposite(TextType.TEXT);
    Matcher matcher = PARAGRAPH_PATTERN.matcher(text);

    while (matcher.find()) {
      String paragraph = matcher.group();

      TextComponent component = next(paragraph);

      if (component != null) {
        TextComposite paragraphComponent =
          new TextComposite(TextType.PARAGRAPH);

        paragraphComponent.add(component);
        fullText.add(paragraphComponent);
      }
    }

    return fullText;
  }

  @Override
  public boolean canParse(String text) {
    return text.contains(PARAGRAPH_CHECK_REGEX);
  }
}
