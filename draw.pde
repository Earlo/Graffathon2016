void wall( PImage text, float side) {
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

void floor( PImage text, float side) {
    beginShape();
    rotateX(side*PI/2);
    texture(text);
    vertex(-100, -100, 0, 0,   0);
    vertex( 100, -100, 0, 400, 0);
    vertex( 100,  100, 0, 400, 400);
    vertex(-100,  100, 0, 0,   400);
    endShape();
}


void DrawImg( float x, float y, float z, PImage text, float angle) {
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


void drawWall(int x,int y,int z, float side, PImage text){
    pushMatrix();
    translate( x, y, z);
    wall( text, side);
    popMatrix();  
}

void drawFloor(int x,int y,int z, float side, PImage text, PImage textc){
    pushMatrix();
    translate( x, y+100, z);
    floor( text, -1);
    popMatrix();
    pushMatrix();
    translate( x, y-200, z);
    floor( textc, 1);
    popMatrix();
}