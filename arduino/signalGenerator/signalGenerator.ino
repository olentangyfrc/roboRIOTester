int signalPin = 5;      // Connection to DIO on roboRIO
int potPin = 0;         // Analog in for user control 
int voltPin = 1;        // Analog in for reading voltage, i.e. from the roboRIO 5v rails
int dutyCycle = 32;     // duty cycle as 0-255 
int prevDuty = 0;       // so we know when it changed
float pct;              // to convert duty cycle to a percentage
int prettyPWM = -1;     // PWM value for display
unsigned long lastUpdateTime = 0;   // avoid displaying/writing too often
unsigned long curLoopTime = 0;      // this loop time
long sinceUpdate = 0;               // microseconds since previous display update

float volts;

byte pwmInterrupt = 1; // equates to pin 3!
// These are defined volatile because they're used in interrupt service routines
volatile int pwmValue = 0;
volatile int pwmRiseTime = 0;

#include <LiquidCrystal.h>
// initialize the display library with the numbers of the interface pins
LiquidCrystal lcd(4, 6, 10, 11, 12, 13);

// put your setup code here, to run once:
void setup() {
  Serial.begin(115200);  // This is much better than using 9600 as many examples show! (do the math on how slow 9600 is!)
  pinMode(signalPin, OUTPUT);
  lcd.begin(16,2);
  lcd.clear();
  //TCCR0B = TCCR0B & B11111000 | B00000001; // for PWM frequency of 62500.00 Hz
  //TCCR0B = TCCR0B & B11111000 | B00000010; // for PWM frequency of 7812.50 Hz
  //TCCR0B = TCCR0B & B11111000 | B00000011; // for PWM frequency of 976.56 Hz (The DEFAULT)
  //TCCR0B = TCCR0B & B11111000 | B00000100; // for PWM frequency of 244.14 Hz
  //TCCR0B = TCCR0B & B11111000 | B00000101; // for PWM frequency of 61.04 Hz  
 
  //Attach an interrupt to the rising signal on our pwm input pin
  attachInterrupt(pwmInterrupt, catchRising, RISING);
  
}

// Interrupt service routine to record when pin goes high
void catchRising() {
  // set up to catch the falling transition
  attachInterrupt(pwmInterrupt, catchFalling, FALLING);
  // record when this time was 
  pwmRiseTime = micros();
}

// Interrupt service routine to record when pin goes low
void catchFalling() {
  // set up to catch the next rising transition
  attachInterrupt(pwmInterrupt, catchRising, RISING);
  pwmValue = micros() - pwmRiseTime;
}

// put your main code here, to run repeatedly:
void loop() {
  // write a PWM signal to the DIO signal pin on the roboRIO based on the arduino input pot
  dutyCycle = analogRead(potPin)/4;  // divide by 4 because input is 0-1023 and pwm wants 0-255
  analogWrite(signalPin, dutyCycle); // note this is really a pwm output

  // read the voltage on the voltage pin
  volts = analogRead(voltPin) * (5.0 / 1023.0);
  //volts = round(volts * 100) / 100;

  // Update the displays but don't do too frequently to avoid flicker & too much log output 
  curLoopTime = micros();   
  sinceUpdate = curLoopTime - lastUpdateTime;
  if (sinceUpdate > 50000) {  // 50000 = 50ms = 20Hz
    lastUpdateTime = curLoopTime; 
    pct = (float)dutyCycle / 255.0 * 100;
    prettyPWM = pwmValue;
    if (prettyPWM < 0) {
      prettyPWM = 0;
    }
    if (prettyPWM > 9999) {
      prettyPWM = 9999;  // not sure if this could happen, but... protect against it anyways
    }
    Serial.print("DC: ");
    Serial.print(pct);
    Serial.print("%, PWM: ");
    Serial.print(prettyPWM);
    Serial.println("");
    //lcd.clear();
    lcd.setCursor(0,0);
    lcd.print("DC: ");
    lcd.print((int)pct);
    lcd.print("%  ");
    lcd.setCursor(10,0);
    lcd.print(volts);
    lcd.print("v");
    lcd.setCursor(0,1);
    lcd.print("PWM: ");
    lcd.print(prettyPWM);
    lcd.print("us    ");
  }
}
