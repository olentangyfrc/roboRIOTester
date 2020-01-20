int signalPin = 5;
int potPin = 0;
int dutyCycle = 32;
int prevDuty = 0;
float pct;

#include <LiquidCrystal.h>
// initialize the display library with the numbers of the interface pins
LiquidCrystal lcd(4, 6, 10, 11, 12, 13);

void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
  pinMode(signalPin, OUTPUT);
  lcd.begin(16,2);
  lcd.clear();
//TCCR0B = TCCR0B & B11111000 | B00000001; // for PWM frequency of 62500.00 Hz
TCCR0B = TCCR0B & B11111000 | B00000010; // for PWM frequency of 7812.50 Hz
//TCCR0B = TCCR0B & B11111000 | B00000011; // for PWM frequency of 976.56 Hz (The DEFAULT)
//TCCR0B = TCCR0B & B11111000 | B00000100; // for PWM frequency of 244.14 Hz
//TCCR0B = TCCR0B & B11111000 | B00000101; // for PWM frequency of 61.04 Hz  
}

void loop() {
  // put your main code here, to run repeatedly:
  dutyCycle = analogRead(potPin)/4;
  analogWrite(signalPin, dutyCycle);
  if (dutyCycle != prevDuty) {
    prevDuty = dutyCycle;
    pct = (float)dutyCycle / 255.0 * 100;
    Serial.print("Setting duty cycle to ");
    Serial.print(dutyCycle);
    Serial.print(" ");
    Serial.print(pct);
    Serial.println("%");
    //lcd.clear();
    lcd.setCursor(0,0);
    lcd.print("DC: ");
    lcd.print((int)pct);
    lcd.print("%");
  }
}
