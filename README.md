# roboRIOTester
Test roboRIO with custom arduino hardware and software.
First, you need to get an arduino with the correct code and wiring which could be found in the arduinoWiring.txt file on Github.
Second, get a roborio and plug in the DIO and PWM wires to test them with the PWM signal to 3 and DIO signal to 5 and both grounds to gnd on the arduino and you need both to test for the PWM signal because DIO intakes the signal and PWM outputs that signal from 1000 to 2000 with 1500 being 0 in the code.
Then, you need a WINDOWS computer since drivers station won't work on macOS.
After that, with the drivers station, put in values and move the pins cordingly, moving through 0-9 on the DIO and PWM, one at a time.
Then, with another bread board, you can test the Relay and change values in the drivers station with Green and yellow lights with green LED+ going to relay Forward and Yellow LED+ going to Reverse and Relay 0 ground going to common breadboard ground.
On the LCD Display, it should show PWM and voltage % values.
Use the voltage divider, RIO Voltage Pin to Voltage Divider Input, Arduino Analog A1 to Voltage Divider Output, RIO Ground to Voltage Divider Ground, Arduino Ground to Voltage Divider Ground, to test the RSL so you don't fry the arduino because it can only take up to 5 Volts while RSL give up to 16 volts.
Also, Connect the signal pot connection with this,   Pot Outer1 to Arduino 5v, Pot Outer2 to Arduino Ground, Pot Inner to Arduino A0 & RIO AnalogIn.
the Voltage Divider is using this format.
                          Output Voltage
                               /|\
                                |
Input Voltage -> 10K Resistor --+--> 5K Resistor ---> Ground 


If you are confused with this, everything is on arduinoWiring.txt file.