/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem;

import frc.robot.subsystem.led.Led;
import frc.robot.subsystem.led.LedSBTab;
import frc.robot.subsystem.pwm.PWM;
import frc.robot.subsystem.pwm.PWMSBTab;
import frc.robot.subsystem.telemetry.Telemetry;
import frc.robot.subsystem.telemetry.TelemetrySBTab;

import java.util.logging.Logger;

/**
 * Add your docs here.
 */
public class DisplayManager {
    private TelemetrySBTab telemetryDisplay;
    private PWMSBTab pwmDisplay;
    private LedSBTab ledDisplay;
    private static Logger logger = Logger.getLogger(DisplayManager.class.getName());

    public DisplayManager(){
        
    }

    public void addTelemetry(Telemetry te){
        telemetryDisplay = new TelemetrySBTab(te);
    }
        
    public void update(){
        if (telemetryDisplay != null) {
            telemetryDisplay.update();
        }
        if (pwmDisplay != null) {
            pwmDisplay.update();
        }
    }
    public void addPWM(PWM te) {
        pwmDisplay = new PWMSBTab(te);
    }

	public void addLed(Led led) {
        ledDisplay = new LedSBTab(led);
	}
}
