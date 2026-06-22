package org.nessrev.infohandle;

import org.nessrev.infohandle.entity.TextComponent;
import org.nessrev.infohandle.exception.TextException;
import org.nessrev.infohandle.parser.Parser;
import org.nessrev.infohandle.parser.heir.*;
import org.nessrev.infohandle.reader.TextReaderService;
import org.nessrev.infohandle.reader.impl.TextReaderServiceImpl;

public class Main {
  public static void main(String[] args) throws TextException {
    final TextReaderService reader = new TextReaderServiceImpl();

    String fileName = "textFile1.txt";
    String fileData = reader.readText(fileName);

    Parser charParser = new CharParser();
    Parser wordParser = new WordParser(charParser);
    Parser lexemeParser = new LexemeParser(wordParser);
    Parser sentenceParser = new SentenceParser(lexemeParser);
    Parser paragraphParser = new ParagraphParser(sentenceParser);

    TextComponent result = paragraphParser.parse(fileData);

    System.out.println(result.toString());
  }
}