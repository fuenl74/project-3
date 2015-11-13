

class Ball {
  //Properties
  float x, y, dx, dy;
  int r, g, b;
  String name="";
  //methods
  void show() {
    fill(r, g, b);
    ellipse(x, y, 30, 30);
    fill(0);
    text(name, x-5, y+5);
  }
  void move() {
    if (t.wall) {                        //if the wall is there
      if (x>t.right-4 || x<t.middle+20) 
      {  
        dx=  -dx;
      }
    } else {                                                
      if (x>t.right-4 || x<t.left+4) 
      {  
        dx=  -dx;
      }
    }
    if (y>t.bottom-4 || y<t.top+4) 
    {  
      dy=  -dy;
    }
    x=  x+dx;
    y=  y+dy;
  }
  void reset() {                       //puts the the balls on right side random position
    x=  random( (width/2)+60, t.right );    
    y=  random( t.top, t.bottom );
    dx=  random( -4, 4 );
    dy=  random( -3, 2 );
  }
  void resetCue() {            //puts the cue ball on the left side centered not moving
    x= width/4;
    y= (t.bottom+t.top)/2;
    dx= 0; 
    dy= 0;
  }
 void clickBall(){
    if (dist( mouseX, mouseY, x,y) < 15){
       x=  random( (width/2)+60, t.right );    
       y=  random( t.top, t.bottom );
       dx=  random( -6,2 );
       dy=  random( -3,4 );
       score -=5;
    }
  }
   void clickCue(){
    if (dist( mouseX, mouseY, x,y) < 15){
       x= width/4;
       y= (t.bottom+t.top)/2;
       dx= 0; dy= 0;
       score -=5;
    }
  }
   boolean hit( float x, float y ) {                        
    if (  dist( x,y, this.x,this.y ) < 30 ) return true;   
    else return false;
  }
}
class WTable {
  //PROPERTIES
  float left, right, top, bottom, middle;
  float horizon;
  boolean wall;
  
  //METHODS
  void tableDisplay(){
    noStroke();
    fill( 50, 200, 50 );    
    strokeWeight(20);
    stroke( 100, 27, 20 );      // walls
    rectMode( CORNERS );
    rect( left-20, top-20, right+20, bottom+20 );
    stroke(0);
    strokeWeight(1);
    
    if (wall) {
      stroke( 100, 27, 20 );
      strokeWeight(10);
      line( middle,top-10, middle,bottom+10 );
    }
  }
} 

class Bird {
  //// PROPERTIES:  position, speed, color, etc. ////   (What a Bird "has".)
  float x=0,y=50,by,bDY;
  float w=60;
  int r,g,b;
  int number;
  boolean wingUp=false;
  
  boolean fly,drop;
  
  //// CONSTRUCTORS (if any). ////
  Bird(){
  fly=false;
  drop=false;
  x= -50;
  bDY=2;
  }
  
  //// METHODS:  show, move, detect a "hit", etc. ////  (What a Ball "does".)
  void show() {
    strokeWeight(0);
    fill(0,50,100);
    triangle( x,y, x-w,y-10, x-w,y+10 );
    // Wing
    wingUp=  int(frameCount/30) %2 >0;
    fill(100);
    if (wingUp) {
      triangle( x-w/3,y, x-w*2/3,y, x-w/2,y-40 );
    }else{
      triangle( x-w/3,y, x-w*2/3,y, x-w/2,y+40 );
    }
  }
  
 void moveBird(){
    if (fly)
    x +=4;
    if (x>width+50){
      x= -50;
      fly = false;             
      drop =false;             
      three.counter = false;   
      three.buffer = 0;        
      by = 70;
      bDY = 2;
      
    }
  }
   void bombDrop(){
    if (drop==true){
        noStroke();
        fill(105);
        rect(x,by-50,25,50);
        fill(30);
        rect(x+9,by,10,15);
        fill(250,55,0);
        rect(x+7,by-71,7,23);
        rect(x+21,by-57,7,19);
        rect(x-3,by-57,7,19);
        by += bDY;
        bDY += .25;
    }
  }

  boolean hit( float x, float y ) {
    if (  dist( x,y, this.x,this.y ) < 30 ) return true;
    else return true;
  }
  
}

class Cloud{
  
  float x, y;            
  
  Cloud( float x, float y){
    this.x=x;  this.y=y;
  }
 
  void showClouds() {
    stroke(0);
    fill(random(0,220));
    noStroke();
    ellipse(x+22,y-16,25,25);
    ellipse(x+11,y-7,25,25);
    ellipse(x+33,y-7,25,25);
    ellipse(x+22,y-7,25,25);
    stroke(random(100,250),random(100,250),0);
    rectMode(CORNER);
    rect(x,y-5,0,16);
    rect(x+10,y+3,0,16);
    rect(x+20,y+5,0,16);
    rect(x+30,y+3,0,16);
    rect(x+40,y-2,0,16);
    stroke(0,0,0);
    noStroke();
    x++;
    if (x>width+10){
      x= random(-200,-100);
      
    }
  }
}
class Button {
  //PROPERTIES
  float x,y;
  String words;
  boolean counter;
  int buffer;
  //CONSTRUCTOR if any
  Button(float tempX, float tempY) {
    x = tempX;
    y = tempY;
    counter = false;
    buffer = 0;
  }
  
  void buttonDisplay(){
    fill(100,120,130);
    noStroke();
    rectMode( CORNER );
    rect(x, y, 80, 40);
    fill(255);
    textSize(16);
    text( words, x+25, y+25);
    textSize(13);
  }
  
  void buttonReset(){
    if (mouseX >x && mouseX<x+80 && mouseY>y && mouseY<y+40){
    reset();
  }
 }
 //no wall
 void buttonWall(){
  if (mouseX >x && mouseX<x+80 && mouseY>y && mouseY<y+40){
    t.wall = false;
  }
 }
 
 void buttonBird(){
   if (counter == true) {
   buffer +=1;
       } 
   if (mouseX >x && mouseX<x+80 && mouseY>y && mouseY<y+40){
     f.fly = true;
     counter = true;
     if (buffer > 1){
     f.drop = true;
     }
   }
 }
}






























class Ball {
  //Properties
  float x, y, dx, dy;
  int r, g, b;
  String name="";
  //methods
  void show() {
    fill(r, g, b);
    ellipse(x, y, 30, 30);
    fill(0);
    text(name, x-5, y+5);
  }
  void move() {
    if (t.wall) {                        //if the wall is there
      if (x>t.right-4 || x<t.middle+20) 
      {  
        dx=  -dx;
      }
    } else {                                                
      if (x>t.right-4 || x<t.left+4) 
      {  
        dx=  -dx;
      }
    }
    if (y>t.bottom-4 || y<t.top+4) 
    {  
      dy=  -dy;
    }
    x=  x+dx;
    y=  y+dy;
  }
  void reset() {                       //puts the the balls on right side random position
    x=  random( (width/2)+60, t.right );    
    y=  random( t.top, t.bottom );
    dx=  random( -4, 4 );
    dy=  random( -3, 2 );
  }
  void resetCue() {            //puts the cue ball on the left side centered not moving
    x= width/4;
    y= (t.bottom+t.top)/2;
    dx= 0; 
    dy= 0;
  }
 void clickBall(){
    if (dist( mouseX, mouseY, x,y) < 15){
       x=  random( (width/2)+60, t.right );    
       y=  random( t.top, t.bottom );
       dx=  random( -6,2 );
       dy=  random( -3,4 );
       score -=5;
    }
  }
   void clickCue(){
    if (dist( mouseX, mouseY, x,y) < 15){
       x= width/4;
       y= (t.bottom+t.top)/2;
       dx= 0; dy= 0;
       score -=5;
    }
  }
   boolean hit( float x, float y ) {                        
    if (  dist( x,y, this.x,this.y ) < 30 ) return true;   
    else return false;
  }
}
class WTable {
  //PROPERTIES
  float left, right, top, bottom, middle;
  float horizon;
  boolean wall;
  
  //METHODS
  void tableDisplay(){
    noStroke();
    fill( 50, 200, 50 );    
    strokeWeight(20);
    stroke( 100, 27, 20 );      // walls
    rectMode( CORNERS );
    rect( left-20, top-20, right+20, bottom+20 );
    stroke(0);
    strokeWeight(1);
    
    if (wall) {
      stroke( 100, 27, 20 );
      strokeWeight(10);
      line( middle,top-10, middle,bottom+10 );
    }
  }
} 

class Bird {
  //// PROPERTIES:  position, speed, color, etc. ////   (What a Bird "has".)
  float x=0,y=50,by,bDY;
  float w=60;
  int r,g,b;
  int number;
  boolean wingUp=false;
  
  boolean fly,drop;
  
  //// CONSTRUCTORS (if any). ////
  Bird(){
  fly=false;
  drop=false;
  x= -50;
  bDY=2;
  }
  
  //// METHODS:  show, move, detect a "hit", etc. ////  (What a Ball "does".)
  void show() {
    strokeWeight(0);
    fill(0,50,100);
    triangle( x,y, x-w,y-10, x-w,y+10 );
    // Wing
    wingUp=  int(frameCount/30) %2 >0;
    fill(100);
    if (wingUp) {
      triangle( x-w/3,y, x-w*2/3,y, x-w/2,y-40 );
    }else{
      triangle( x-w/3,y, x-w*2/3,y, x-w/2,y+40 );
    }
  }
  
 void moveBird(){
    if (fly)
    x +=4;
    if (x>width+50){
      x= -50;
      fly = false;             
      drop =false;             
      three.counter = false;   
      three.buffer = 0;        
      by = 70;
      bDY = 2;
      
    }
  }
   void bombDrop(){
    if (drop==true){
        noStroke();
        fill(105);
        rect(x,by-50,25,50);
        fill(30);
        rect(x+9,by,10,15);
        fill(250,55,0);
        rect(x+7,by-71,7,23);
        rect(x+21,by-57,7,19);
        rect(x-3,by-57,7,19);
        by += bDY;
        bDY += .25;
    }
  }

  boolean hit( float x, float y ) {
    if (  dist( x,y, this.x,this.y ) < 30 ) return true;
    else return true;
  }
  
}

class Cloud{
  
  float x, y;            
  
  Cloud( float x, float y){
    this.x=x;  this.y=y;
  }
 
  void showClouds() {
    stroke(0);
    fill(random(0,220));
    noStroke();
    ellipse(x+22,y-16,25,25);
    ellipse(x+11,y-7,25,25);
    ellipse(x+33,y-7,25,25);
    ellipse(x+22,y-7,25,25);
    stroke(random(100,250),random(100,250),0);
    rectMode(CORNER);
    rect(x,y-5,0,16);
    rect(x+10,y+3,0,16);
    rect(x+20,y+5,0,16);
    rect(x+30,y+3,0,16);
    rect(x+40,y-2,0,16);
    stroke(0,0,0);
    noStroke();
    x++;
    if (x>width+10){
      x= random(-200,-100);
      
    }
  }
}
class Button {
  //PROPERTIES
  float x,y;
  String words;
  boolean counter;
  int buffer;
  //CONSTRUCTOR if any
  Button(float tempX, float tempY) {
    x = tempX;
    y = tempY;
    counter = false;
    buffer = 0;
  }
  
  void buttonDisplay(){
    fill(100,120,130);
    noStroke();
    rectMode( CORNER );
    rect(x, y, 80, 40);
    fill(255);
    textSize(16);
    text( words, x+25, y+25);
    textSize(13);
  }
  
  void buttonReset(){
    if (mouseX >x && mouseX<x+80 && mouseY>y && mouseY<y+40){
    reset();
  }
 }
 //no wall
 void buttonWall(){
  if (mouseX >x && mouseX<x+80 && mouseY>y && mouseY<y+40){
    t.wall = false;
  }
 }
 
 void buttonBird(){
   if (counter == true) {
   buffer +=1;
       } 
   if (mouseX >x && mouseX<x+80 && mouseY>y && mouseY<y+40){
     f.fly = true;
     counter = true;
     if (buffer > 1){
     f.drop = true;
     }
   }
 }
}
