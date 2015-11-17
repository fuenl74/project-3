/// Luis Fuentes
/// Project 3
Rat rt;
Bird f;
Ball a,b,c,d,e,q;
WTable t;
Button one, two, three, four;
Cloud[] cloudList;
int frame;
int score;




///setup
 void setup(){
   size(720,480);
   
   ///table
   t= new WTable();
   t.left=   50;
   t.right=  width-50;
   t.top=    150;
   t.bottom= height-50;
   t.middle= t.left + (t.right-t.left) / 2;
   t.horizon= (height/4)-20;
   t.wall=true;
   ////ball
   f=new Bird();
   f.y = 70;
   f.by = 75;
   
   
   a=  new Ball();
   a.r=55;
   a.b=255;
   a.name=  "1";
  
   b=  new Ball();
   b.g=255;
   b.name=  "2";
  
   c=  new Ball();
   c.g=155;
   c.b=205;
   c.name=  "3";
  
   d=  new Ball();
   d.g=227;
   d.b=127;
   d.name=  "4";
  
   e=  new Ball();
   e.r=127;
   e.name=  "5";
  
   q=  new Ball();
   q.r=250;
   q.g=250;
   q.b=250;
   q.name=  " ";
   
   ///buttons
   one= new Button(50,5);
   one.words= "Reset";
  
   two= new Button(140,5);
   two.words= "Wall";
   
   three= new Button(230,5);
   three.words="Bird";
    
   four= new Button(320,5);
   four.words="Rat";
   
   rt= new Rat();
   
   
   ///clouds
  cloudList= new Cloud[7];
  float cloudX=50;
  for( int i=0; i<cloudList.length; i++) {
     cloudList[i]=  new Cloud( cloudX,random(t.horizon-100,t.horizon-25));
     cloudX += 100;
  }
   reset();
}
   void reset() {
   
   a.reset();
   b.reset();
   c.reset();
   d.reset();
   e.reset();
   q.resetCue();
   t.wall = true;
}
  
  
  void draw() {
    background( 120,120,250 );
    t.tableDisplay();
    drawGrass();
    drawClouds();
    balls();
    bird();
    rat();
    buttons();
    frame +=1;
    showScore();
}

void rat(){
  rt.moveRat();
  rt.showRat();
  rt.scoreFix();
}


void bird(){
  f.show();
  f.bombDrop();
  f.moveBird();
}
  void drawGrass(){
  int x = 0;
  strokeWeight(2);
  while (x <= width){
   fill(0,200,0);
   noStroke();
   triangle(x,height,x+10,height-20,x+10,height);
   triangle(x+10,height,x+15,height-25,x+20,height);
    x +=15;
  }
}
 
 
 ///showing the clouds
void drawClouds(){
   for (int i=0; i<cloudList.length; i++) {
    cloudList[i].showClouds();
  }
}


///to make the ellastic collision
void collision( Ball p, Ball q ) {
  if ( p.hit( q.x,q.y ) ) {
    float tmp;
    tmp=p.dx;  p.dx=q.dx;  q.dx=tmp;     
    tmp=p.dy;  p.dy=q.dy;  q.dy=tmp; 
    score += 1;
  }
}

void ratCollision( Ball p, Rat q ) {
  if ( p.hit( q.x,q.y ) ) {
    p.dx=0;
    p.dy=0;
    if (rt.scoreBuffer == false){          //convoluted fix for the problem with the rat collision scoring.
      score -= 10;                         // b/c the rat doesn't instantly go in the opposite direction of the ball
      rt.scoreBufferCounter = 0;           // there is a 'collision' for many frames in a row, causing the score to plumet.
      rt.scoreBuffer = true;               // w/ the fix, the -10 score can only happen every 10 frames, usually enough time 
    }                                      // for the rat to move away from the ball
  }
}
 /////here is where the main action of the ball happens collision, motion and appearance of the ball
 void balls() {
  
  a.show();     a.move();
  b.show();     b.move();
  c.show();     c.move();
  d.show();     d.move();
  e.show();     e.move();
  q.show();     q.move();

  ratCollision(a, rt);
  ratCollision(b, rt);
  ratCollision(c, rt);
  ratCollision(d, rt);
  ratCollision(e, rt);
  ratCollision(q, rt);

  collision( a, b );
  collision( a, c );
  collision( a, d );
  collision( a, e );
  collision( a, q );
  
  collision( b, c );
  collision( b, d );
  collision( b, e );
  collision( b, q );
  
  collision( c, d );
  collision( c, e );
  collision( c, q );
  
  collision( d, e );
  collision( d, q );
  collision( e, q );
 

}  


//DISPLAY BUTTONS
  void buttons(){
    one.buttonDisplay();
    two.buttonDisplay();
    three.buttonDisplay();
    four.buttonDisplay();
}

void showScore(){
  text("Score", 50, height-50);
  text(score , 90, height -50);
}


void mousePressed() {
   rt.clickRat();
   a.clickBall();
   b.clickBall();
   c.clickBall();
   d.clickBall();
   e.clickBall();
   q.clickCue();

   one.buttonReset();
   two.buttonWall();
   three.buttonBird();
   four.buttonRat();
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
  float fillColor=100;  
  
  Cloud( float x, float y){
    this.x=x;  this.y=y;
  }
 
  void showClouds() {
    stroke(0);
    // Change fill every second
    if (frameCount % 30 == 0) {
      fillColor=  random(50,220);
    }
    fill(fillColor);
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
    rect(x+43,y-2,0,16);
    stroke(0,0,0);
    noStroke();
    x++;
    if (x>width+10){
      x= random(-200,-100);
      
    }
  }
}


class Rat {   float x,y,DX,DY;
  boolean crawl;
  boolean scoreBuffer;
  int scoreBufferCounter;
  
  Rat() {
    crawl = false;
    scoreBuffer = false;
    x = -50;
    y = random(170,height-50);
  }
  
  void moveRat(){
    if (crawl){
      DX=random(0,5);
      DY=random(-5,5);
      x+=DX;
      y+=DY;
      if (x>width+50){
        crawl = false;
        x = -50;
        y = random(170,height-50);
      }
    }
  }
  
  void showRat(){
      noStroke();                        //only display mouse if true
      fill(150,150,150);
      ellipse(x, y,45,40);//body
      ellipse(x+27, y+5,25,20); //head 
      ellipse(x-33, y,35,7);//tail
      ellipse(x+36, y+5,15,14);//nose
      ellipse(x-15, y+20,5,20);//leg
      ellipse(x+15, y+20,5,20);//leg
      fill(100,100,125);//leg
      ellipse(x+27, y+5,4,4);//eye
       
      
  
    }
 

  void clickRat(){
    if (dist(mouseX,mouseY,x,y)<20){
         crawl = false;
        x = -50;
        y = random(170,height-50);
        score +=50;
    }
  }
      
  void scoreFix(){
    if (scoreBuffer == true){
      scoreBufferCounter +=1;
      if (scoreBufferCounter>10){
        scoreBuffer = false;
      }
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
  void buttonRat(){
   if (mouseX >x && mouseX<x+80 && mouseY>y && mouseY<y+40){
     rt.crawl = true;
   }
 }
}





