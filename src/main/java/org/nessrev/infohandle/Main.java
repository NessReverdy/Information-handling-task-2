package org.nessrev.infohandle;

import org.nessrev.infohandle.entity.TextComponent;
import org.nessrev.infohandle.exception.TextException;
import org.nessrev.infohandle.parser.*;
import org.nessrev.infohandle.reader.TextReaderService;
import org.nessrev.infohandle.reader.impl.TextReaderServiceImpl;
import org.nessrev.infohandle.service.CommonTaskService;
import org.nessrev.infohandle.service.impl.CommonTaskServiceImpl;

import java.util.List;

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

    String fullText = result.recover();
    System.out.println(fullText);

    final CommonTaskService taskService = new CommonTaskServiceImpl(result);

//    List<TextComponent> task1 = taskService.findSentencesWithMostRepeatedWord(result);
//    task1.forEach(sentence -> System.out.println(sentence.getValue()));

    List<TextComponent> task2 = taskService.sortSentencesByLetterCount('y');


  }
}