///////////////////////////////////////////////////////////////////////////////
//
// Title: Dancing Badgers
// Course: CS 300 Spring 2023
//
// Author: Annaliese Bero
// Email: abero@wisc.eedu
// Lecturer: Hobbes LeGault
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;

/**
 * This class models a graphic Thing which can be drawn at a give (x,y) position within the display
 * window of a graphic application.
 */
public class Thing {
  private processing.core.PImage image; // PApplet object that represents the display window of this
                                        // graphic application
  protected static processing.core.PApplet processing; // image of this graphic thing of type PImage
  protected float x; // x-position of this thing in the display window
  protected float y; // y-position of this thing in the display window

  /**
   * Creates a new graphic Thing located at a specific (x, y) position of the display window
   * 
   * @param x             - x-position of this thing in the display window
   * @param y             - y-position of this thing in the display window
   * @param imageFilename - filename of the image of this thing, for instance "name.png"
   */
  public Thing(float x, float y, String imageFilename) {
    // x: x-position of this Thing
    this.x = x;
    // y: y-position of this Thing
    this.y = y;
    // imageFileName: filename of the image to be loaded for this object
    this.image = processing.loadImage("images" + File.separator + imageFilename);
  }

  /**
   * Draws this thing to the display window at its current (x,y) position
   */
  public void draw() {
    processing.image(this.image, x, y);
  }

  /**
   * Sets the PApplet object display window where this Thing object will be drawn
   * 
   * @param processing - PApplet object that represents the display window
   */
  public static void setProcessing(processing.core.PApplet processing) {
    Thing.processing = processing;
  }

  /**
   * Returns a reference to the image of this thing
   * 
   * @return the image of type PImage of the thing object
   */
  public processing.core.PImage image() {
    return image;
  }

  /**
   * Checks if the mouse is over this Thing object
   * 
   * @return true if the mouse is over this Thing, otherwise returns false.
   */
  public boolean isMouseOver() {
    /*
     * int badgerWidth = this.image().width; int badgerHeight = this.image().height;
     * 
     * // checks if the mouse is over this badger return Utility.mouseX() >= this.getX() -
     * badgerWidth / 2 && Utility.mouseX() <= this.getX() + badgerWidth / 2 && Utility.mouseY() >=
     * this.getY() - badgerHeight / 2 && Utility.mouseY() <= this.getY() + badgerHeight / 2;
     */

    return (float) processing.mouseX >= this.x - image.width / 2
        && (float) processing.mouseX <= this.x + image.width / 2
        && (float) processing.mouseY >= this.y - image.height / 2
        && (float) processing.mouseY <= this.y + image.height / 2;
  }
}
