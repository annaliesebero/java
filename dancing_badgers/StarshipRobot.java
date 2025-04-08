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
 * This class models StarshipRobot objects which can be triggered to move or do some actions.
 */
public class StarshipRobot extends MovingThing {

  private Thing destination; // destination point of this StarshipRobot at its current journey
                             // delivering food to badgers
  private Thing source; // source point of this StarshipRobot at its current journey delivering food
                        // to badgers


  /**
   * Creates a new StarshipRobot and sets its source, destination, and speed
   * 
   * @param source      - source point of this StarshipRobot at its current journey delivering food
   *                    to badgers
   * @param destination - destination point of this StarshipRobot at its current journey delivering
   *                    food to badgers
   * @param speed       - movement speed of this StarshipRobot
   */
  StarshipRobot(Thing source, Thing destination, int speed) {
    super(source.x, source.y, speed, "starshipRobot.png");
    this.destination = destination;
    this.source = source;

    if (!(source.x < destination.x))
      isFacingRight = false;
  }

  /**
   * Draws this MovingThing at its current position.
   */
  public void draw() {
    this.go();
    super.draw();
  }

  /**
   * Checks whether this StarshipRobot is over a specific Thing
   * 
   * @param thing - a given Thing object
   * @return true if this StarshipRobot is over the Thing object passed as input, otherwise, returns
   *         false.
   * 
   */
  public boolean isOver(Thing thing) {
    // (x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2)
    float x1 = x - this.image().width / 2;
    float x2 = x + this.image().width / 2;
    float y1 = y - this.image().height / 2;
    float y2 = y + this.image().height / 2;

    float x3 = thing.x - thing.image().width / 2;
    float x4 = thing.x + thing.image().width / 2;
    float y3 = thing.y - thing.image().height / 2;
    float y4 = thing.y + thing.image().height / 2;

    return (x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2);
  }

  /**
   * Helper method to move this StarshipRobot towards its destination
   */
  public void moveTowardsDestination() {
    float dx = destination.x - this.x; // x-move towards destination
    float dy = destination.y - this.y; // y-move towards destination
    int d = (int) Math.sqrt(dx * dx + dy * dy); // distance to destination
    if (d != 0) { // move!
      this.x += speed * dx / d;
      this.y += speed * dy / d;
    }
  }

  /**
   * Implements the action of this StarshipRobot. By default, an StarshipRobot object moves
   * back-and-forth between its source and destination. If the starship robot is over its
   * destination, this method: switches the source and destination, and switches the value of
   * isFacingRight to its opposite (!isFacingRight), so that the starship robot faces the opposite
   * direction.
   */
  public void go() {
    moveTowardsDestination();

    // switch source and destination if this StarshipRobot reached its destination
    if (this.isOver(destination)) {
      Thing d = destination;
      destination = source;
      source = d;
      isFacingRight = !isFacingRight;

    }
  }
}
