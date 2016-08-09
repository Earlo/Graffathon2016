/* 
 * Code for starting a demo project that
 * uses GNU Rocket and Moonlander for
 * syncing.
 *
 * You must install Moonlander as a library
 * into Processing before starting development.
 */
import moonlander.library.*;

// Minim is needed for the music playback
// (even when using Moonlander)
import ddf.minim.*;



import java.util.Map;

// These control how big the opened window is.
// Before you release your demo, set these to 
// full HD resolution (1920x1080).
//static final int CANVAS_WIDTH = 1200;
//static final int CANVAS_HEIGHT = 800;

static final int CANVAS_WIDTH = 1920;
static final int CANVAS_HEIGHT = 1080;






// Our public Moonlander instance
Moonlander moonlander;


PImage PC;
PImage G0;
PImage PCPLUMP;
PImage PLUMP;

PImage SEMI0;
PImage SEMI1;
PImage SEMI2;
PImage SEMI3;
PImage TILDE0;
PImage TILDE1;
PImage TILDE2;
PImage TILDE3;

PImage EQUAL;
PImage GREAT;

PImage CREDITS;


Creature player = new Creature( 200, 400, 3600, PC );
Creature gobbo = new Creature( 1600, 400, 1500, G0 );
Creature plump0 = new Creature( 400, 450, -2400, PLUMP );
Creature plump1 = new Creature( 600, 450, -2600, PLUMP );
Creature plump2 = new Creature( 800, 450, -2400, PLUMP );
Creature plump3 = new Creature( 1000, 450, -2800, PLUMP );
Creature plump4 = new Creature( 1200, 450, -2600, PLUMP );
Creature plump5 = new Creature( 800, 450, -2600, PLUMP );
Creature plump6 = new Creature( 400, 450, -2800, PLUMP );



Creature[] Creatures = {player, gobbo, plump0, plump1, plump2, plump3, plump4, plump5, plump6};
Creature[] Important = {player};

/*
 * Processing's setup method.
 *
 * Do all your one-time setup routines in here.
 */
void setup() {
  // Set up the drawing area size and renderer (P2D / P3D).
  size(1920,1080,P3D);
  surface.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
  //frameRate(60);

  // Parameters: 
  // - PApplet
  // - soundtrack filename (relative to sketch's folder)
  // - beats per minute in the song
  // - how many rows in Rocket correspond to one beat
  moonlander = Moonlander.initWithSoundtrack(this, "../graffa3.wav", 140, 8);
  
  
  PC = loadImage("../textures/at0.png");
  PCPLUMP = loadImage("../textures/atplump.png");

  PLUMP = loadImage("../textures/plump.png");

  G0 = loadImage("../textures/g0.png");


  EQUAL = loadImage("../textures/equal.png");
  GREAT = loadImage("../textures/exit.png");
  SEMI0 = loadImage("../textures/semi0.png");
  SEMI1 = loadImage("../textures/semi1.png");
  SEMI2 = loadImage("../textures/semi2.png");
  SEMI3 = loadImage("../textures/semi3.png");
  TILDE0 = loadImage("../textures/tilde0.png");
  TILDE1 = loadImage("../textures/tilde1.png");
  TILDE2 = loadImage("../textures/tilde2.png");
  TILDE3 = loadImage("../textures/tilde3.png");
  
  CREDITS = loadImage("../textures/credits.png");

  
  // Last thing in setup; start Moonlander. This either
  // connects to Rocket (development mode) or loads data 
  // from 'syncdata.rocket' (player mode).
  // Also, in player mode the music playback starts immediately.
  moonlander.start();
  //camera();
  
  player.img = PC; 
  gobbo.img = G0;
  plump0.img = PLUMP;
  plump1.img = PLUMP;
  plump2.img = PLUMP;
  plump3.img = PLUMP;
  plump4.img = PLUMP;
  plump5.img = PLUMP;
  plump6.img = PLUMP;

        
  //PImage SEMI0 = loadImage("../textures/semi0.png");
}


/*
 * Processing's drawing method
 */
void draw() {
  moonlander.update();
  // Draw something
  // Get values from Rocket using 
  // moonlander.getValue("track_name") or
  int r = moonlander.getIntValue("r");
  int g = moonlander.getIntValue("g");
  int b = moonlander.getIntValue("b");
  int stepper = moonlander.getIntValue("t");
    
  randomSeed(stepper);
  
  int state = moonlander.getIntValue("state");
  
  int x = moonlander.getIntValue("x");
  int y = moonlander.getIntValue("y");
  int z = moonlander.getIntValue("z");
  float s = PI * ( moonlander.getIntValue("spin") / 100.0);

  int cDis = moonlander.getIntValue("cameraDis");
  int cSpi = moonlander.getIntValue("cameraSpin");

  float cameraAngle = PI * ( ( moonlander.getIntValue("angle") + cSpi ) / 100.0);
  //camera(mouseX*4- CANVAS_WIDTH + x, mouseY*4- CANVAS_HEIGHT + height/2 + y, (height/2) / tan(PI/6) + z, width/2, height/2, 0, 0, 1, 0);
  //camera( x, height/2 + y, (height/2) / tan(PI/6) + z, width/2, height/2, 0, 0, 1, 0);
  //camera( 0, height/2 , (height/2) / tan(PI/6) , width/2 + x , height/2 + y, z, 0, 1, 0);
  //camera( x, height/2 + y, (height/2) / tan(PI/6) + z, width/2 + x, height/2 + y, z, 0, 1, 0);

  //camera( 0, height/2 , (height/2) / tan(PI/6) , width/2, height/2, 0, 0, 1, 0);
  
  //camera( x, y-10, z, x-cameraPOS[1] * cDis, y-10+0.2, z-cameraPOS[0] * cDis, 0, 1, 0);
  float[] CPOS = { x +sin(cameraAngle) * cDis, y-cDis/2, z +cos(cameraAngle) * cDis, };
  
  if ( state > 6 )
    CPOS[1] -= moonlander.getIntValue("enemy0");

  camera( CPOS[0], CPOS[1], CPOS[2], x , y, z, 0, 1, 0);
  
  //camera( 0, 100 , 100 , 100, 100, 0, 0, 1, 0);
  
  //println(  0, height/2 , (height/2) / tan(PI/6) , width/2, height/2, 0, 0, 1, 0 );
  //camera( x, y, z, x, y, z, 0, 1, 0);
  //camera( 0, height/2 , (height/2) , width/2, height/2, 0, 0, 1, 0);

  
  
  background(r,g,b);
  
  
  
  drawMap();
    
  
  player.x = x;
  player.y = y;
  player.z = z;
  player.angle = cameraAngle + s;
  
  switch(state) {
    case 1:
      GobboLogic( gobbo );
      break;
    case 2:
      gobbo.y = moonlander.getIntValue("enemy0");
      gobbo.angle = moonlander.getIntValue("enemy2");
      break;
     case 4:
      plump1.y = moonlander.getIntValue("enemy0");
      break;
     case 5:
      player.img = PCPLUMP;
      break;
     case 8:
     Creatures = Important;
     background(CREDITS);
      break;
  }
  
  HashMap<Integer,Creature> hm = new HashMap<Integer,Creature>();
  for (int i = 0; i < Creatures.length; i++) {
    hm.put( int(dist(CPOS[0], CPOS[1], CPOS[2], Creatures[i].x, Creatures[i].y, Creatures[i].z)), Creatures[i]);
    }
  
  int[] sorted = {};
  for(Integer i : hm.keySet())
    sorted = append( sorted, int(i) );
  sorted = reverse(sort(sorted));
  
  for (int i = 0; i < sorted.length; i++) {
    int key = sorted[i];
    hm.get(key).update();
  }

  //sort(hm.keySet().toArray());   
  //Creatures[i].update();

    
}






/*

  for (int i = 0; i < 20; i = i+1) {
    pushMatrix();
    translate(width/2+10' ', height/2, offset-200*i);
    wall( SEMI' ', -1);
    popMatrix();
    pushMatrix();
    translate(width/2-10' ', height/2, offset-200*i);
    wall( SEMI' ', 1);
    popMatrix();
    
    pushMatrix();
    translate(width/2, height/2+10' ', offset-200*i);
    floor( TILDE1, -1);
    popMatrix();
    pushMatrix();
    translate(width/2, height/2-10' ', offset-200*i);
    floor( TILDE1, 1);
    popMatrix();
  }

*/