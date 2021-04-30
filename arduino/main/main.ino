#include <SoftwareSerial.h>   //Software Serial Port
#include <OS_SerialTFT.h>   //Software Serial Port

#define TS_MINX  140
#define TS_MINY  130
#define TS_MAXX  925
#define TS_MAXY  950
#define TFT_RX 0//RX of Serial TFT module connect to D4 of Arduino / OPEN-SMART UNO
#define TFT_TX 1//TX of Serial TFT to D2
SerialTFT myTFT(TFT_RX, TFT_TX);

void setup() {
  myTFT.begin(9600);
  myTFT.reset();
  myTFT.setBacklight(200);//0~255
  myTFT.fillScreen(BLACK);

  myTFT.drawRect(0,0,100,100,YELLOW);
  myTFT.drawRect(102,0,100,100,YELLOW);
  myTFT.drawRect(204,0,100,100,YELLOW);
  myTFT.drawRect(0,120,100,100,YELLOW);
  myTFT.drawRect(102,120,100,100,YELLOW);
  myTFT.drawRect(204,120,100,100,YELLOW);
}

void loop() {
  if (myTFT.touch()) {
    uint16_t temp = myTFT.touchX;
    myTFT.touchX = map(myTFT.touchY, TS_MINY, TS_MAXY, 320, 0);
    myTFT.touchY= map(temp, TS_MINX, TS_MAXX, 0, 240);
    if (myTFT.touchX < 100 && myTFT.touchY < 100) {
      myTFT.fillRect(1,1,98,98,BLUE);
      delay(200);
      myTFT.fillRect(1,1,98,98,BLACK);
    }
  } 
}