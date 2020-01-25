/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem;

import frc.robot.subsystem.telemetry.Telemetry;
import frc.robot.subsystem.telemetry.TelemetrySBTab;

import java.util.logging.Logger;

/**
 * Add your docs here.
 */
public class DisplayManager {
    private TelemetrySBTab telemetryDisplay;

    private static Logger logger = Logger.getLogger(DisplayManager.class.getName());

    public DisplayManager(){
        
    }

    public void addTelemetry(Telemetry te){
        telemetryDisplay = new TelemetrySBTab(te);
    }
        
    public void update(){
        logger.info("update");
        if (telemetryDisplay != null) {
            telemetryDisplay.update();
        }
    }
}
