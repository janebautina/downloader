package com.janebautina.downloader;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    Options options = new Options();
    CommandLineParser parser = new BasicParser();
    options.addOption(
      OptionBuilder.withArgName("url")
                   .hasArg()
                   .withDescription("the url to download")
                   .create("url")
    );
    options.addOption(
      OptionBuilder.withDescription("show help")
                   .create("help")
    );

    try
    {
      CommandLine cmd = parser.parse(options, args);
      if (args.length == 0 || cmd.hasOption("help")) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("downloader", options);
        System.exit(1);
      } 
    } catch (ParseException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
