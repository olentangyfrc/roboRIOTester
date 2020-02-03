/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.pwm;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

/**
 * Add your docs here.
 */
public class PWMSBTab {
    public PWM pwm;
    private ShuffleboardTab tab;
    private NetworkTableEntry pwm0;
    private NetworkTableEntry pwmselector;
    private static Logger logger = Logger.getLogger(PWMSBTab.class.getName());

    public PWMSBTab(PWM te){
        pwm = te;
        
        tab = Shuffleboard.getTab("PWMOut");
        pwm0 = tab.add("pwm0", 0)
                .withWidget(BuiltInWidgets.kGraph)
                .withProperties(Map.of("min", 0, "max", 1))
                .getEntry();
         pwmselector = tab.add("pwmselector", 0)
                .withWidget(BuiltInWidgets.kNumberSlider)
                .withProperties(Map.of("min", 0, "max", 9, "block increment", 1))
                .getEntry();
        // when the user changes the DIO selector on the dashboard we want to have the subsystem change to that DIO
        pwmselector.addListener(event -> {
            int newPort = (int)Math.floor(event.value.getDouble());
            if (newPort != pwm.getPwmPort()) {
                pwm.setPwmPort(newPort);
                pwmselector.setNumber(newPort);  // put the integer back on there
            }
        }, EntryListenerFlags.kUpdate);

    }
    public void update(){
        Double speed = pwm.getOutput();
        if (!speed.isInfinite() && !speed.isNaN()) {
            pwm0.setDouble(speed);
            //logger.log(Level.INFO,"Got speed of "+speed+" from port "+pwm.getPwmPort());
        }
        else {
            logger.warning("PWM Speed is wonky: "+speed);
        }

    }
}
