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
// Persons: Nicholas Andershak - Helped with makeMoveDance Method
// Online Sources: (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models a Badger object in the P05 Dancing Badgers III programming assignment
 */
public class Badger extends MovingThing implements Clickable {

  private DanceStep[] danceSteps; // array storing this Badger's dance show steps
  private boolean isDancing; // indicates whether this badger is dancing or not
  private boolean isDragging; // indicates whether this badger is being dragged or not
  private float[] nextDancePosition; // stores the next dance (x, y) position of this Badger.
  private static int oldMouseX; // old x-position of the mouse
  private static int oldMouseY; // old y-position of the mouse
  private int stepIndex; // index position of the current dance step of this badger

  /**
   * Creates a new Badger object positioned at a specific position of the display window and whose
   * moving speed is 2 When created, a new badger is not dragging and is not dancing. This
   * constructor also sets the danceSteps of the created Badger to the one provided as input and
   * initializes stepIndex to 1.
   * 
   * @param x          - x-position of this Badger object within the display window
   * @param y          - y-position of this Badger object within the display window
   * @param danceSteps - perfect-size array storing the dance steps of this badger
   */
  public Badger(float x, float y, DanceStep[] danceSteps) {
    super(x, y, 2, "badger.png");
    this.danceSteps = danceSteps;

    isDragging = false;
    isDancing = false;
    stepIndex = 1;
  }

  /**
   * Draws this badger to the display window. Also, this method: calls the drag() behavior if this
   * Badger is dragging and calls the dance() behavior if this Badger is dancing
   */
  public void draw() {
    super.draw();
    if (this.isDragging)
      this.drag();

    if (this.isDancing)
      this.dance();
  }

  /**
   * Checks whether this badger is being dragged
   * 
   * @return true if the badger is being dragged, false otherwise
   */
  public boolean isDragging() {
    return isDragging;
  }

  /**
   * Helper method to drag this Badger object to follow the mouse moves
   */
  private void drag() {
    int dx = processing.mouseX - oldMouseX;
    int dy = processing.mouseY - oldMouseY;
    x += dx;
    y += dy;

    if (x > 0)
      x = Math.min(x, processing.width);
    else
      x = 0;
    if (y > 0)
      y = Math.min(y, processing.height);
    else
      y = 0;
    oldMouseX = processing.mouseX;
    oldMouseY = processing.mouseY;

  }

  /**
   * Starts dragging this badger
   */
  public void startDragging() {
    oldMouseX = processing.mouseX;
    oldMouseY = processing.mouseY;
    this.isDragging = true;
    drag();
  }

  /**
   * Stops dragging this Badger object
   */
  public void stopDragging() {
    this.isDragging = false;
  }

  /**
   * Defines the behavior of this Badger when it is clicked. If the mouse is over this badger and
   * this badger is NOT dancing, this method starts dragging this badger.
   */
  public void mousePressed() {
    if (this.isMouseOver() && !this.isDancing)
      this.startDragging();
  }

  /**
   * Defines the behavior of this Badger when the mouse is released. If the mouse is released, this
   * badger stops dragging.
   */
  public void mouseReleased() {
    this.stopDragging();
  }

  /**
   * This helper method moves this badger one speed towards its nextDancePosition. Then, it checks
   * whether this Badger is facing right and updates the isFacingRight data field accordingly. After
   * making one move dance, a badger is facing right if the x-move towards its next dance position
   * is positive, otherwise, it is facing left.
   * 
   * @return true if this Badger almost reached its next dance position, meaning that the distance
   *         to its next dance position is less than 2 times its speed. Otherwise, return false.
   * 
   */
  private boolean makeMoveDance() {

    float dx = nextDancePosition[0] - this.x; // x-move towards destination
    float dy = nextDancePosition[1] - this.y; // y-move towards destination
    int d = (int) Math.sqrt(dx * dx + dy * dy); // distance to destination

    if (d != 0) { // move!
      this.x += speed * dx / d;
      this.y += speed * dy / d;
    }

    if (x > 0)
      x = Math.min(x, processing.width);
    else
      x = 0;
    if (y > 0)
      y = Math.min(y, processing.height);
    else
      y = 0;

    if ((this.nextDancePosition[0] - this.x) > 0)
      super.isFacingRight = true;
    else
      super.isFacingRight = false;

    int tempIndex = stepIndex;
    tempIndex--;
    if (tempIndex == -1) {
      tempIndex = danceSteps.length - 1;
    }

    switch (this.danceSteps[tempIndex]) {
      case LEFT: {
        return (Math.abs(dx) < (2 * speed));
      }

      case RIGHT: {
        return (Math.abs(dx) < (2 * speed));
      }

      case UP: {
        return (Math.abs(dy) < (2 * speed));
      }

      case DOWN: {
        return (Math.abs(dy) < (2 * speed));
      }
    }

    return false;


  }

  /**
   * Implements the dance behavior of this Badger. This method prompts the Badger to make one move
   * dance. If the makeMoveDance method call returns true (meaning the badger almost reached its
   * nextDancePosition), this method MUST: update its next dance position (see
   * DanceStep.getPositionAfter()), and increment the stepIndex.
   */
  private void dance() {
    if (this.makeMoveDance()) {

      this.nextDancePosition = danceSteps[stepIndex].getPositionAfter(super.x, super.y);
      stepIndex = (stepIndex + 1) % danceSteps.length;
    }

  }

  /**
   * Prompts this badger to start dancing. This method: updates the isDancing data field, stops
   * dragging this badger, sets stepIndex to zero, Resets the nextDancePosition
   */
  public void startDancing() {
    isDancing = true;
    this.stopDragging();
    stepIndex = 0;
    this.nextDancePosition = danceSteps[stepIndex].getPositionAfter(super.x, super.y);

    this.nextDancePosition[0] = danceSteps[stepIndex].getPositionAfter(super.x, super.y)[0];
    this.nextDancePosition[1] = danceSteps[stepIndex].getPositionAfter(super.x, super.y)[1];

  }

  /**
   * Prompts this badger to stop dancing. Sets the isDancing data field to false.
   */
  public void stopDancing() {
    isDancing = false;
  }

}
