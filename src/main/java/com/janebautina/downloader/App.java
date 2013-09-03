package com.janebautina.downloader;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

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
    CommandLine cmd = null;
    try {
      cmd = parser.parse(options, args);
      if (args.length == 0 || cmd.hasOption("help")) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("downloader", options);
        System.exit(1);
      } 
    } catch (ParseException e) {
      e.printStackTrace();
      System.exit(1);
    }
    DefaultHttpClient httpclient = new DefaultHttpClient();
    System.out.println("url=" + cmd.getOptionValue("url"));
    HttpGet httpGet = new HttpGet(cmd.getOptionValue("url"));
    try
    {
      HttpResponse response1 = httpclient.execute(httpGet);
      try {
        System.out.println(response1.getStatusLine());
        HttpEntity entity1 = response1.getEntity();
        // do something useful with the response body
        // and ensure it is fully consumed
        InputStream stream = entity1.getContent();
        byte[] buf = new byte[1024];
        int count = 0;
        while ((count = stream.read(buf)) > 0) {
          System.out.write(buf, 0, count);
        }
        stream.close();
      } finally {
        httpGet.releaseConnection();
      }
    } catch (ClientProtocolException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
