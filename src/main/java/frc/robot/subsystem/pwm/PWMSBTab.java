/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.pwm;

import java.util.Map;
import java.util.logging.Logger;

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
    private static Logger logger = Logger.getLogger(PWM.class.getName());

    public PWMSBTab(PWM te){
        pwm = te;
        
        tab = Shuffleboard.getTab("PWM");
        pwm0 = tab.add("pwm0", 0)
                .withWidget(BuiltInWidgets.kGraph)
                .withProperties(Map.of("min", 0, "max", 1))
                .getEntry();
         pwmselector = tab.add("pwmselector", 0)
                .withWidget(BuiltInWidgets.kNumberSlider)
                .withProperties(Map.of("min", 0, "max", 9, "block increment", 1))
                .getEntry();
    }
    public void update(){

    }
}
