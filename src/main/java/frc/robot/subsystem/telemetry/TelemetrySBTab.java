/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.telemetry;

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
public class TelemetrySBTab {
    public Telemetry telemetry;
    private ShuffleboardTab tab;
    private NetworkTableEntry dio0;
    private NetworkTableEntry dioselector;
    private static Logger logger = Logger.getLogger(Telemetry.class.getName());

    public TelemetrySBTab(Telemetry te){
        telemetry = te;
        
        tab = Shuffleboard.getTab("Telemetry");
        dio0 = tab.add("dio0", 0)
                .withWidget(BuiltInWidgets.kGraph)
                .withProperties(Map.of("min", 0, "max", 1))
                .getEntry();
         dioselector = tab.add("dioselector", 0)
                .withWidget(BuiltInWidgets.kNumberSlider)
                .withProperties(Map.of("min", 0, "max", 9, "block increment", 1))
                .getEntry();
    }
    public void update(){
        int dio = (int)Math.floor(dioselector.getDouble(0));
        Double ms = telemetry.getPeriodms();
        if (!ms.isInfinite() && !ms.isNaN()) {
            dio0.setDouble(ms);
            logger.info("Counter period="+ms+"ms");
        }
        else {
            logger.info("Counter period is wonky: "+ms);
        
        }

    }
}
