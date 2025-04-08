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

import java.util.Random;
import java.util.ArrayList;
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This is the main class for the p05 Dancing Badgers III program
 */
public class DancingBadgers extends PApplet {

  // array storing badgers dance show steps
  private static DanceStep[] badgersDanceSteps = new DanceStep[] {DanceStep.LEFT, DanceStep.RIGHT,
      DanceStep.RIGHT, DanceStep.LEFT, DanceStep.DOWN, DanceStep.LEFT, DanceStep.RIGHT,
      DanceStep.RIGHT, DanceStep.LEFT, DanceStep.UP};
  // array storing the positions of the dancing badgers at the start of the dance show
  private static float[][] startDancePositions =
      new float[][] {{300, 250}, {364, 250}, {428, 250}, {492, 250}, {556, 250}};

  private static processing.core.PImage backgroundImage; // background image
  private static int badgersCountMax; // Maximum number of Badger objects allowed in the basketball
                                      // court
  private boolean danceShowOn; // Tells whether the dance show is on.
  private static Random randGen; // Generator of random numbers
  private static ArrayList<Thing> things; // arraylist storing Thing objects


  /**
   * Sets the size of the display window of this graphic application
   */
  @Override
  public void settings() {
    this.size(800, 600);
  }

  /**
   * Defines initial environment properties of this graphic application. This method initializes all
   * the data fields defined in this class.
   */
  @Override
  public void setup() {
    this.getSurface().setTitle("P5 Dancing Badgers"); // displays the title of the screen
    this.textAlign(3, 3); // sets the alignment of the text
    this.imageMode(3); // interprets the x and y position of an image to its center
    this.focused = true; // confirms that this screen is "focused", meaning
    // it is active and will accept mouse and keyboard input.
    // TODO complete the implementation of this method
    randGen = new Random();
    backgroundImage = this.loadImage("images" + File.separator + "background.png");
    badgersCountMax = 5;
    things = new ArrayList<Thing>();

    Thing.setProcessing(this);
    Badger.setProcessing(this);

    things.add(new Thing(50, 50, "target.png"));
    things.add(new Thing(750, 550, "target.png"));
    things.add(new Thing(750, 50, "shoppingCounter.png"));
    things.add(new Thing(50, 550, "shoppingCounter.png"));

    things.add(new StarshipRobot(things.get(0), things.get(2), 3));
    things.add(new StarshipRobot(things.get(1), things.get(3), 5));

    things.add(new Basketball(50, 300));
    things.add(new Basketball(750, 300));


  }



  /**
   * Callback method that draws and updates the application display window. This method runs in an
   * infinite loop until the program exits.
   */
  @Override
  public void draw() {
    this.background(color(255, 218, 185));
    this.image(backgroundImage, this.width / 2, this.height / 2);

    for (int i = 0; i < things.size(); i++)
      things.get(i).draw();

  }

  /**
   * Callback method called each time the user presses the mouse. This method iterates through the
   * list of things. If the mouse is over a Clickable object, it calls its mousePressed method, then
   * returns.
   */
  @Override
  public void mousePressed() {
    for (int i = 0; i < things.size(); i++)
      if (things.get(i).isMouseOver() && things.get(i) instanceof Clickable) {
        ((Clickable) things.get(i)).mousePressed();
        break;
      }
  }

  /**
   * Callback method called each time the mouse is released. This method calls the mouseReleased()
   * method on every Clickable object stored in the things list.
   */
  @Override
  public void mouseReleased() {
    for (int i = 0; i < things.size(); i++)
      if (things.get(i) instanceof Clickable)
        ((Clickable) things.get(i)).mouseReleased();
  }

  /**
   * Gets the number of Badger objects present in the basketball arena
   * 
   * @return the number of Badger objects present in the basketball arena
   * 
   */
  public int badgersCount() {
    int count = 0;
    for (int i = 0; i < things.size(); i++) {
      if (things.get(i) instanceof Badger)
        count++;
    }
    return count;
  }

  /**
   * Sets the badgers start dance positions. The start dance positions of the badgers are provided
   * in the startDancePositions array. The array startDancePositions contains badgersCountMax dance
   * positions. If there are fewer Badger objects in the basketball arena, they will be assigned the
   * first positions.
   */
  private void setStartDancePositions() {
    int count = 0;
    for (int i = 0; i < things.size(); i++) {
      if (things.get(i) instanceof Badger) {
        things.get(i).x = startDancePositions[count][0];
        things.get(i).y = startDancePositions[count][1];
        count++;
      }
    }

  }

  /**
   * Callback method called each time the user presses a key. b-key: If the b-key is pressed and the
   * danceShow is NOT on, a new badger is added to the list of things. Up to badgersCountMax can be
   * added to the basketball arena. c-key: If the c-key is pressed, the danceShow is terminated
   * (danceShowOn set to false), and ALL MovingThing objects are removed from the list of things.
   * This key removes MovingThing objects ONLY. d-key: This key starts the dance show if the
   * danceShowOn was false, and there is at least one Badger object in the basketball arena.
   * Starting the dance show, sets the danceShowOn to true, sets the start dance positions of the
   * Badger objects, and calls the startDancing() method on every Badger object present in the list
   * of things. Pressing the d-key when the danceShowOn is true or when there are no Badger objects
   * present in the basketball arena has no effect. r-key: If the danceShow is NOT on and the r-key
   * is pressed when the mouse is over a Badger object, this badger is removed from the list of
   * things. If the mouse is over more than one badger, the badger at the lowest index position will
   * be deleted. s-key: If the s-key is pressed, the dancdShow is terminated and all the Badger
   * objects stop dancing. Pressing the s-key does not remove any thing.
   */
  @Override
  public void keyPressed() {
    int count = 0;
    for (int i = 0; i < things.size(); i++)
      if (things.get(i) instanceof Badger)
        count++;


    switch (Character.toUpperCase(this.key)) {
      case 'B':
        if (count < badgersCountMax && !danceShowOn) {
          things.add(new Badger(randGen.nextInt(this.width), randGen.nextInt(this.height),
              badgersDanceSteps));
        }
        break;
      case 'C':
        danceShowOn = false;
        for (int i = things.size() - 1; i >= 0; i--) {
          if (things.get(i) instanceof MovingThing)
            things.remove(i);
        }
        break;

      case 'D':
        if (!danceShowOn && count >= 1) {
          danceShowOn = true;
          this.setStartDancePositions();

          for (int i = 0; i < things.size(); i++)
            if (things.get(i) instanceof Badger) {
              ((Badger) things.get(i)).startDancing();
            }
        }
        break;
      case 'R':
        if (!danceShowOn) {
          for (int i = 0; i < things.size(); i++)
            if (things.get(i) instanceof Badger && things.get(i).isMouseOver())
              things.remove(i);

        }
        break;
      case 'S':
        danceShowOn = false;
        for (int i = 0; i < things.size(); i++)
          if (things.get(i) instanceof Badger)
            ((Badger) things.get(i)).stopDancing();
        break;
    }
  }


  /**
   * Driver method to run this graphic application
   * 
   * @param args - list of input arguments if any
   */
  public static void main(String[] args) {
    PApplet.main("DancingBadgers");
  }
}
