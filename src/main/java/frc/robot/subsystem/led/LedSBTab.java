/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.led;

import java.util.logging.Logger;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LedSBTab extends SubsystemBase {

    private Led led;
    private ShuffleboardTab tab;
    private NetworkTableEntry relay0;
    private ComplexWidget widget0;
    private SendableChooser<Integer> choice0;
    private Integer prevSelected0;
    private static Logger logger = Logger.getLogger(LedSBTab.class.getName());

    /**
     * Creates a new LedSBTab.
     */
    public LedSBTab(Led led) {
        this.led = led;

        tab = Shuffleboard.getTab("Relays");
        choice0 = new SendableChooser<Integer>();
        choice0.setDefaultOption("Off", 0);
        choice0.addOption("Fwd",1);
        choice0.addOption("Rev",-1);
        choice0.setName("Relay 0");

        widget0 = tab.add(choice0)
                .withWidget(BuiltInWidgets.kComboBoxChooser);


    }

    @Override
    public void periodic() {
        Integer selected = choice0.getSelected();
        if (selected != prevSelected0) {
            logger.info("Choice0="+selected);
            led.setRelay(selected);
            prevSelected0 = selected; 
        }
    }

}
