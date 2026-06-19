package org.nessrev.infohandle.reader.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nessrev.infohandle.exception.TextException;
import org.nessrev.infohandle.reader.TextReaderService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TextReaderServiceImpl implements TextReaderService {
  private static final Logger logger = LogManager.getLogger();

  @Override
  public List<String> readText(String fileName) throws TextException {
    if (fileName != null && !fileName.isBlank()){

      Path filePath = Path.of("data").resolve(fileName);
      logger.debug("Reading file {}", filePath);

      try{
        List<String> lines = Files.readAllLines(filePath);
        logger.info("File successfully read. Lines count: {}", lines.size());
        return lines;

      } catch (IOException e){
        logger.error("Error while reading file: {}", filePath, e);
        throw new TextException("Failed to read file: " + filePath, e);
      }
    } else {
      logger.error("File name is null or empty");
      throw new TextException("File name is null or empty");
    }
  }
}
