//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Dancing Badgers
// Course: CS 300 Spring 2023
//
// Author: Annaliese Bero
// Email: abero@wisc.eedu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Jackson Sprenger
// Partner Email: jsprenger@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// __X_ Write-up states that pair programming is allowed for this assignment.
// __X_ We have both read and understand the course Pair Programming Policy.
// __X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources: (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models moving thing objects. A moving thing is defined by its speed and to which
 * direction it is facing (right or left).
 */
public class MovingThing extends Thing implements Comparable<MovingThing> {

  protected int speed; // movement speed of this MovingThing
  protected boolean isFacingRight; // indicates whether this MovingThing is facing right or not

  /**
   * Creates a new MovingThing and sets its speed, image file, and initial x and y position. A
   * MovingThing object is initially facing right.
   * 
   * @param x             - starting x-position of this MovingThing
   * @param y             - starting y-position of this MovingThing
   * @param speed         - movement speed of this MovingThing
   * @param imageFileName - filename of the image of this MovingThing, for instance "name.png"
   */
  public MovingThing(float x, float y, int speed, String imageFileName) {
    super(x, y, imageFileName);
    this.speed = speed;
    isFacingRight = true;
  }

  /**
   * Draws this MovingThing at its current position.
   */
  public void draw() {
    // draw this MovingThing at its current position
    processing.pushMatrix();
    processing.rotate(0.0f);
    processing.translate(x, y);
    if (!isFacingRight) {
      processing.scale(-1.0f, 1.0f);
    }
    processing.image(image(), 0.0f, 0.0f);
    processing.popMatrix();
  }

  /**
   * Compares this object with the specified MovingThing for order, in the increasing order of their
   * speeds.
   * 
   * @param other - the MovingThing object to be compared.
   * @return zero if this object and other have the same speed, a negative integer if the speed of
   *         this moving object is less than the speed of other, and a positive integer otherwise.
   */
  public int compareTo(MovingThing other) {
    return ((Integer) speed).compareTo(other.speed);
  }
}

