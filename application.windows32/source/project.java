import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import moonlander.library.*; 
import ddf.minim.*; 
import java.util.Map; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class project extends PApplet {

/* 
 * Code for starting a demo project that
 * uses GNU Rocket and Moonlander for
 * syncing.
 *
 * You must install Moonlander as a library
 * into Processing before starting development.
 */


// Minim is needed for the music playback
// (even when using Moonlander)






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
public void setup() {
  // Set up the drawing area size and renderer (P2D / P3D).
  
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
public void draw() {
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
  float s = PI * ( moonlander.getIntValue("spin") / 100.0f);

  int cDis = moonlander.getIntValue("cameraDis");
  int cSpi = moonlander.getIntValue("cameraSpin");

  float cameraAngle = PI * ( ( moonlander.getIntValue("angle") + cSpi ) / 100.0f);
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
    hm.put( PApplet.parseInt(dist(CPOS[0], CPOS[1], CPOS[2], Creatures[i].x, Creatures[i].y, Creatures[i].z)), Creatures[i]);
    }
  
  int[] sorted = {};
  for(Integer i : hm.keySet())
    sorted = append( sorted, PApplet.parseInt(i) );
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
class Creature { 
  float x,y,z; 
  float angle;
  PImage img;
  Creature (float X, float Y, float Z, PImage I) {  
    x = X;
    y = Y;
    z = Z;
    img = I;
    angle = 0.0f;
  } 
  
  public void update() { 
    DrawImg( x, y, z, img, angle);
  } 
}

public void GobboLogic( Creature gobbo ){
  int x = moonlander.getIntValue("enemy0");  
  int z = moonlander.getIntValue("enemy1");  
  int d = moonlander.getIntValue("enemy2");  
  gobbo.x = x;
  gobbo.z = z;
  gobbo.angle = d;
}
public void wall( PImage text, float side) {
    beginShape();
    rotateY(side*PI/2);
    texture(text);
    textureWrap(REPEAT); 
    vertex(-100, -200, 0, 0,   0);
    vertex( 100, -200, 0, 400, 0);
    vertex( 100,  100, 0, 400, 800);
    vertex(-100,  100, 0, 0,   800);
    endShape();
}

public void floor( PImage text, float side) {
    beginShape();
    rotateX(side*PI/2);
    texture(text);
    vertex(-100, -100, 0, 0,   0);
    vertex( 100, -100, 0, 400, 0);
    vertex( 100,  100, 0, 400, 400);
    vertex(-100,  100, 0, 0,   400);
    endShape();
}


public void DrawImg( float x, float y, float z, PImage text, float angle) {
    pushMatrix();
    translate( x, y, z);
    beginShape();
    stroke(0,0,0,0);
    rotateY(angle);
    //rotateY(PCAngle); 
    texture(text);
    vertex(-40, -40, 0, 0,   0);
    vertex( 40, -40, 0, 400, 0);
    vertex( 40,  40, 0, 400, 400);
    vertex(-40,  40, 0, 0,   400);
    endShape();
    
    popMatrix();  
}


public void drawWall(int x,int y,int z, float side, PImage text){
    pushMatrix();
    translate( x, y, z);
    wall( text, side);
    popMatrix();  
}

public void drawFloor(int x,int y,int z, float side, PImage text, PImage textc){
    pushMatrix();
    translate( x, y+100, z);
    floor( text, -1);
    popMatrix();
    pushMatrix();
    translate( x, y-200, z);
    floor( textc, 1);
    popMatrix();
}
int[][] TheMap = { 
{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','u','u','u','u','u','u','u','u','u','u',' '}, 
{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','l','f','f','f','f','f','f','f','f','f','f','r'}, 
{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','l','f','f','f','f','f','f','f','f','f','f','r'},
{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','u','u','u','u','u','u','u','u','u',' ',' ','l','f','f','f','f','f','f','f','f','f','f','r'}, 
{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','l','f','f','f','f','f','f','f','f','f','r',' ','l','f','f','f','f','f','f','f','f','f','f','r'}, 
{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','l','f','=','=','=','=','=','=','=','f','r',' ','l','f','f','f','f','f','f','f','f','f','f','r'},
{' ',' ',' ',' ',' ',' ',' ',' ',' ','u','u','u','u','u','u','u','c','f','=','=','=','=','=','=','=','f','r',' ','l','f','f','f','f','f','f','f','f','f','f','r'}, 
{' ',' ',' ',' ',' ',' ',' ',' ','l','f','f','f','f','f','f','f','f','f','=','=','=','=','=','=','=','f','c','u','c','f','f','f','f','f','f','f','f','f','f','r'}, 
{' ',' ',' ',' ',' ',' ',' ',' ','l','f','f','f','f','f','f','f','f','f','=','=','=','=','=','=','=','f','f','f','f','f','f','f','f','f','f','f','f','f','f','r'}, 
{' ','u','u','u','u','u','u',' ','l','f','f','c','d','d','d','d','c','f','=','=','=','=','=','=','=','f','f','f','f','f','f','f','f','f','f','f','f','f','f','r'}, 
{'l','f','f','f','f','f','f','r','l','f','f','r',' ',' ',' ',' ','l','f','f','f','f','f','f','f','f','f','c','d','c','f','f','f','f','f','f','f','f','f','f','r'}, 
{'l','f','f','f','f','f','f','r','l','f','f','r',' ',' ',' ',' ','l','f','f','f','f','f','f','f','f','f','r',' ','l','f','f','f','f','f','f','f','f','f','f','r'},
{'l','f','f','f','f','f','f','r','l','f','f','r',' ',' ',' ',' ','l','f','f','f','f','f','f','f','f','f','r',' ','l','f','f','f','f','f','f','f','f','f','f','r'}, 
{'l','f','f','>','f','f','f','r','l','f','f','r',' ',' ',' ',' ','l','f','f','f','f','f','f','f','f','f','r',' ','l','f','f','f','f','f','f','f','f','f','f','r'}, 
{'l','f','>','>','>','f','f','r','l','f','f','r',' ',' ',' ',' ',' ','d','d','d','c','f','c','d','d','d',' ',' ','l','f','f','f','f','f','f','f','f','f','f','r'},
{'l','f','f','>','f','f','f','r','l','f','f','r',' ',' ',' ',' ',' ',' ',' ',' ','l','f','r',' ',' ',' ',' ',' ','l','f','f','f','f','f','f','f','f','f','f','r'}, 
{'l','f','f','f','f','f','f','r','l','f','f','r',' ',' ',' ',' ',' ',' ',' ',' ','l','f','r',' ',' ',' ',' ',' ','l','f','f','f','f','f','f','f','f','f','f','r'}, 
{'l','f','f','f','f','f','f','r','l','f','f','r',' ',' ',' ',' ',' ',' ',' ',' ','l','f','r',' ',' ',' ',' ',' ',' ','c','f','c','d','d','d','d','d','d','d',' '}, 
{'l','f','f','f','f','f','f','r','l','f','f','r',' ',' ',' ',' ',' ',' ',' ',' ','l','f','r',' ',' ',' ',' ',' ',' ','l','f','r',' ',' ',' ',' ',' ',' ',' ',' '}, 
{'l','f','f','f','f','f','f','r','l','f','f','r',' ',' ',' ',' ',' ',' ',' ',' ','l','f','r',' ',' ',' ',' ',' ',' ','l','f','r',' ',' ',' ',' ',' ',' ',' ',' '}, 
{'l','f','f','f','f','f','f','r','l','f','f','r',' ',' ',' ',' ',' ',' ','u','u','c','f','c','u','u','u',' ',' ',' ','l','f','r',' ',' ',' ',' ',' ',' ',' ',' '},
{'l','f','f','f','f','f','f','r','l','f','f','r',' ','u','u','u','u','l','f','f','f','f','f','f','f','f','r',' ',' ','l','f','r',' ',' ',' ',' ',' ',' ',' ',' '}, 
{'l','f','f','f','f','f','f','r','l','f','f','r','l','f','f','f','f','c','f','c','d','d','d','d','c','f','r',' ',' ','l','f','r',' ',' ',' ',' ',' ',' ',' ',' '}, 
{'l','f','f','f','f','f','f','r','l','f','f','r','l','f','c','c','f','c','f','r',' ',' ',' ',' ','l','f','r',' ',' ','l','f','r',' ',' ',' ',' ',' ',' ',' ',' '},
{'l','f','f','f','f','f','f','r','l','f','f','r','l','f','r','l','f','c','f','r',' ',' ',' ',' ','c','f','c','u',' ','l','f','r',' ',' ',' ',' ',' ',' ',' ',' '}, 
{'l','f','f','f','f','f','f','r','l','f','f','r','l','f','r',' ','d','l','f','r',' ',' ',' ','l','f','f','f','f','r','l','f','r',' ',' ',' ',' ',' ',' ',' ',' '}, 
{'l','f','f','f','f','f','f','c','c','f','f','r','l','f','c','u','u','c','f','r',' ',' ',' ','l','f','f','f','f','c','c','f','r',' ',' ',' ',' ',' ',' ',' ',' '}, 
{'l','f','f','f','f','f','f','f','f','f','f','r','l','f','f','f','f','f','f','r',' ',' ',' ','l','f','f','f','f','f','f','f','r',' ',' ',' ',' ',' ',' ',' ',' '}, 
{'l','f','f','f','f','f','f','f','f','f','f','r','l','f','c','d','d','d','d',' ',' ',' ',' ','l','f','f','f','f','c','d','d',' ',' ',' ',' ',' ',' ',' ',' ',' '}, 
{' ','d','d','d','d','d','d','d','d','d','d','r','l','f','r',' ',' ',' ',' ',' ',' ',' ',' ','l','f','f','f','f','r',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
{' ',' ','l','f','f','c','u','u','c','f','f','r','l','f','r',' ',' ',' ',' ',' ',' ',' ',' ',' ','d','c','f','c',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}, 
{' ',' ','l','f','f','f','f','f','f','f','f','r','l','f','r',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','l','f','r',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}, 
{' ',' ','l','f','f','f','f','f','f','f','f','r','l','f','c','u','u','u','u','u','u','u','u','u','u','c','f','r',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
{' ',' ',' ','d','d','d','d','d','d','d','d',' ','l','f','f','f','f','f','f','f','f','f','f','f','f','f','f','r',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}, 
{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','l','f','c','d','d','c','f','c','d','d','d','d','d','d','d',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}, 
{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','l','f','r',' ',' ','l','f','r',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}, 
{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','l','f','r',' ',' ','l','f','r',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}, 
{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','l','f','r',' ',' ','l','f','r',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}, 
{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','d',' ',' ',' ','l','f','r',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}, 
{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','l','f','r',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}};   


//void drawMap(int posX, int posY, int posZ){
public void drawMap(){
  for (int i = 0; i < 40; i++) {
    for (int j = 0; j < 40; j++) {
      int point = TheMap[i][j];
      //int relX = width/2  + 200*(j - 20) - posX;
      //int relY = height/2     - posY;
      //int relZ = (200*(i - 20)) +posZ;
      //int relX = width/2  + 200*(j - 20);
      //int relY = height/2;
      
      int relX = 600  + 200*(j - 20);
      int relY = 400;
      int relZ = (200*(i - 20));
      float r = random(100);      
      PImage WTEX;
      PImage FTEX;

      if (r < 45){
        WTEX = SEMI0;
        FTEX = TILDE0;
      }
      else if( r < 90){
        WTEX = SEMI1;
        FTEX = TILDE1;
      }
      else if( r < 95 ){
        WTEX = SEMI2;
        FTEX = TILDE2;
      }
      else{
        WTEX = SEMI3;
        FTEX = TILDE3;
      }
      
      switch(point) {
        case 'l': 
          drawWall( relX+100, relY, relZ, -1, WTEX);
          break;
        case 'r': 
          drawWall( relX-100, relY, relZ, -1, WTEX);
          break;
        case 'd': 
          drawWall( relX, relY, relZ-100, 0, WTEX);
          break;
        case 'u': 
          drawWall( relX, relY, relZ+100, 0, WTEX);
          break;
        case 'c': 
          drawWall( relX+100, relY, relZ, -1, WTEX);
          drawWall( relX-100, relY, relZ, -1, WTEX);
          drawWall( relX, relY, relZ-100, 0, WTEX);
          drawWall( relX, relY, relZ+100, 0, WTEX);
          drawFloor( relX, relY, relZ, -1, FTEX, FTEX);
          break;
        case 'f':
          drawFloor( relX, relY, relZ, -1, FTEX, FTEX);
          break;          
       case '=': 
          drawFloor( relX, relY, relZ, -1, EQUAL, FTEX);
          break;          

        case '>': 
          drawWall( relX+100, relY, relZ, -1, GREAT);
          drawWall( relX-100, relY, relZ, -1, GREAT);
          drawWall( relX, relY, relZ-100, 0, GREAT);
          drawWall( relX, relY, relZ+100, 0, GREAT);
          drawFloor( relX, relY, relZ, -1, GREAT, GREAT);
          break;

        default:
          //println("Zulu");   // Does not execute
          break;
      }    
    }
  }
}
  public void settings() {  size(1920,1080,P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "project" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
