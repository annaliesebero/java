//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Dancing Badgers Project02
// Course: CS 300 Spring 2023
//
// Author: Annaliese Bero
// Email: abero@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Random;
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class creates a display with a set background and adds/removes Badger objects when a user
 * interacts with the window. It also allows the user to move the badger objects around on the
 * screen.
 * 
 * @author annaliese-UW
 *
 */
public class DancingBadgers {
  private static PImage backgroundImage; // PImage object that represents the background image
  private static Badger[] badgers; // perfect size array storing the badgers present in this
                                   // application
  private static Random randGen; // Generator of random numbers

  /**
   * creates the initial display window with the background color and background image. Only
   * iterates once at the beginning of runApplication
   */
  public static void setup() {
    randGen = new Random();
    // load the image of the background
    backgroundImage = Utility.loadImage("images" + File.separator + "background.png");
    badgers = new Badger[5];
  }

  /**
   * Draws and updates the application display window. This callback method called in an infinite
   * loop.
   */
  public static void draw() {
    Utility.background(Utility.color(255, 218, 185));

    // Draw the background image to the center of the screen
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);

    for (int i = 0; i < badgers.length; i++)
      if (badgers[i] != null)
        badgers[i].draw(); // where i is the index of the created Badger in the badgers array.
  }

  /**
   * Checks if the mouse is over a specific Badger whose reference is provided as input parameter
   *
   * @param Badger reference to a specific Badger object
   * @return true if the mouse is over the specific Badger object passed as input (i.e. over the
   *         image of the Badger), false otherwise
   */
  public static boolean isMouseOver(Badger Badger) {
    if (Badger != null) {
      if (!(Badger.getX() - (Badger.image().width / 2) < Utility.mouseX()))
        return false;
      else if (!(Badger.getX() + (Badger.image().width / 2) > Utility.mouseX()))
        return false;
      else if (!(Badger.getY() - (Badger.image().height / 2) < Utility.mouseY()))
        return false;
      else if (!(Badger.getY() + (Badger.image().height / 2) > Utility.mouseY()))
        return false;
      else
        return true;
    } else
      return false;

  }

  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
    for (int i = 0; i < badgers.length; i++)
      if (isMouseOver(badgers[i]))
        badgers[i].startDragging();
  }

  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
    for (int i = 0; i < badgers.length; i++)
      if (badgers[i] != null)
        badgers[i].stopDragging();
  }

  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {
    if (Utility.key() == 'b' || Utility.key() == 'B')
      for (int i = 0; i < badgers.length; i++)
        if (badgers[i] == null) {
          badgers[i] = new Badger();
          badgers[i].setX(randGen.nextInt(Utility.width()));
          badgers[i].setY(randGen.nextInt(Utility.height()));
          return;
        }

    for (int i = 0; i < badgers.length; i++)
      if (isMouseOver(badgers[i]) && (Utility.key() == 'r' || Utility.key() == 'R'))
        badgers[i] = null;
  }

  public static void main(String[] args) {
    Utility.runApplication(); // starts the application
  }

}
