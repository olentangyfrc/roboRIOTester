/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.telemetry;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/**
 * Add your docs here.
 */
public class TelemetrySBTab {
    public Telemetry telemetry;
    private ShuffleboardTab tab;
    private NetworkTableEntry dio0;

    private NetworkTableEntry aPortVolts;
    private NetworkTableEntry selectedAPort;
    private SendableChooser<Integer> choseAPort;
    private SendableChooser<Integer> choseDPort;
    private Integer prevAnalog;
    private Integer prevDio;
// keep changes with DPort

    private static Logger logger = Logger.getLogger(Telemetry.class.getName());

    public TelemetrySBTab(Telemetry te){
        telemetry = te;
        
        tab = Shuffleboard.getTab("Telemetry");
        setupSBDIO();
        setupSBAPort();

    }

    /**
     * Setup the shuffleboard controls for the DIO value widget and port selector
     */
    private void setupSBDIO() {
        dio0 = tab.add("dio0", 0)
                .withWidget(BuiltInWidgets.kGraph)
                .withProperties(Map.of("min", 0, "max", 1))
                .getEntry();
        choseDPort = new SendableChooser<Integer>();
        choseDPort.setDefaultOption("0", 0);
        choseDPort.addOption("1",1);
        choseDPort.addOption("2",2);
        choseDPort.addOption("3",3);
        choseDPort.addOption("4",4);
        choseDPort.addOption("5",5);
        choseDPort.addOption("6",6);
        choseDPort.addOption("7",7);
        choseDPort.addOption("8",8);
        choseDPort.addOption("9",9);
        SendableRegistry.setName(choseDPort, "DIO Port");
        tab.add(choseDPort)
                .withWidget(BuiltInWidgets.kComboBoxChooser);
        prevDio = 0;
    }

    /**
     * Setup the shuffleboard controls for the analog voltage input widget and the analog port selector
     */
    private void setupSBAPort() {
        aPortVolts = tab.add("Analog In Volts", 0)
                .withWidget(BuiltInWidgets.kGraph)
                .withProperties(Map.of("min", 0, "max", 5))
                .getEntry();

        choseAPort = new SendableChooser<Integer>();
        choseAPort.setDefaultOption("0", 0);
        choseAPort.addOption("1",1);
        choseAPort.addOption("2",2);
        choseAPort.addOption("3",3);
        SendableRegistry.setName(choseAPort, "Analog Port");
        tab.add(choseAPort)
                .withWidget(BuiltInWidgets.kComboBoxChooser);
        prevAnalog = 0;
    }

    /**
     * Update the shuffleboard widgets with current DIO and Analog In values
     */
    public void update(){
        Integer newPort = choseAPort.getSelected();
        if (newPort != prevAnalog) {
            telemetry.setAnalogPort(newPort);
            prevAnalog = newPort;
        }
        newPort = choseDPort.getSelected();
        if (newPort != prevDio) {
            telemetry.setDioPort(newPort);
            prevDio = newPort;
        }

        Double ms = telemetry.getPeriodms();
        if (!ms.isInfinite() && !ms.isNaN()) {
            dio0.setDouble(ms);
            //logger.log(Level.INFO,"Got period of "+ms+" from port "+telemetry.getDioPort());
        }
        else {
            logger.warning("Counter period is wonky: "+ms);
            dio0.setDouble(-1);
        }

        Double volts = telemetry.getVolts();
        if (!volts.isInfinite() && !volts.isNaN()) {
            aPortVolts.setDouble(volts);
        }
        else {
            logger.warning("Analog input volts are wonky: "+volts);
            aPortVolts.setDouble(-1);
        }
    }
}
