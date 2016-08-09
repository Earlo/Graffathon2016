class Creature { 
  float x,y,z; 
  float angle;
  PImage img;
  Creature (float X, float Y, float Z, PImage I) {  
    x = X;
    y = Y;
    z = Z;
    img = I;
    angle = 0.0;
  } 
  
  void update() { 
    DrawImg( x, y, z, img, angle);
  } 
}

void GobboLogic( Creature gobbo ){
  int x = moonlander.getIntValue("enemy0");  
  int z = moonlander.getIntValue("enemy1");  
  int d = moonlander.getIntValue("enemy2");  
  gobbo.x = x;
  gobbo.z = z;
  gobbo.angle = d;
}