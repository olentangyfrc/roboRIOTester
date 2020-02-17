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
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/**
 * Add your docs here.
 */
public class PWMSBTab {
    public PWM pwm;
    private ShuffleboardTab tab;
    private NetworkTableEntry pwm0;
    private NetworkTableEntry pwmselector;
    private static Logger logger = Logger.getLogger(PWMSBTab.class.getName());

    private SendableChooser<Integer> chosePWMPort;
    private Integer prevAnalog;
    private Integer prevPWM;


    public PWMSBTab(PWM te){
        pwm = te;
        
        tab = Shuffleboard.getTab("PWMOut");
        pwm0 = tab.add("pwm0", 0)
                .withWidget(BuiltInWidgets.kGraph)
                .withProperties(Map.of("min", 0, "max", 1))
                .getEntry();
        chosePWMPort = new SendableChooser<Integer>();
        chosePWMPort.setDefaultOption("0", 0);
        chosePWMPort.addOption("1",1);
        chosePWMPort.addOption("2",2);
        chosePWMPort.addOption("3",3);
        chosePWMPort.addOption("4",4);
        chosePWMPort.addOption("5",5);
        chosePWMPort.addOption("6",6);
        chosePWMPort.addOption("7",7);
        chosePWMPort.addOption("8",8);
        chosePWMPort.addOption("9",9);
        SendableRegistry.setName(chosePWMPort, "PWM Port");
        tab.add(chosePWMPort)
                .withWidget(BuiltInWidgets.kComboBoxChooser);
        prevPWM = 0;
    }
    public void update(){
        int newPort = chosePWMPort.getSelected();
        if (newPort != prevPWM) {
            pwm.setPwmPort(newPort);
            prevPWM = newPort;
        }

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
