//LCD1602
#include <LiquidCrystal.h>// include the library code
/**********************************************************/
// initialize the library with the numbers of the interface pins
LiquidCrystal lcd(4, 6, 10, 11, 12, 13);
/*********************************************************/
void setup()
{
  lcd.begin(16, 2);  // set up the LCD's number of columns and rows: 
  lcd.print("hello, world!");
  lcd.setCursor(0,1);
  lcd.print("it works!");  
}
/*********************************************************/
void loop() 
{
}
/************************************************************/
