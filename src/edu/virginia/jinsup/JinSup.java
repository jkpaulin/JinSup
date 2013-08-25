package edu.virginia.jinsup;

import com.beust.jcommander.JCommander;

public class JinSup {

  /**
   * If true, no trade logging information will be saved and graphs will not be
   * updated. Other logging may still be done, however (e.g. IA profit).
   */
  public static final boolean TEST_MODE = true;

  public static void main(String[] args) {

    new JCommander(new Settings(), args);

    // Time conversion from seconds to milliseconds
    Controller.graphFrame = new GraphFrame();
    int buyPrice = (int) (Settings.buyPrice * 100);
    int startTime = Settings.startTime * 1000;
    int endTime = startTime + Settings.tradeTime * 1000;

    MatchingEngine matchingEngine;
    Controller controller;

    matchingEngine = new MatchingEngine(buyPrice, startTime, TEST_MODE);
    controller = new Controller(startTime, endTime, matchingEngine, TEST_MODE);
    System.out.println("Starting simulator...");
    long elapsedTime = System.nanoTime();
    controller.runSimulator();
    Controller.graphFrame.showFinished(System.nanoTime() - elapsedTime);
  }
}
