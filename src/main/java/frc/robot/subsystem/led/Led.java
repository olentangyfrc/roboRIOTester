/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.led;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.OzoneException;
import frc.robot.subsystem.PortMan;

public class Led extends SubsystemBase {

    private static Logger logger = Logger.getLogger(Led.class.getName());
    private Relay relay0, relay1, relay2, relay3;

    public Led() {

    }
     
    public void init(PortMan portMan) throws OzoneException {
      logger.entering(Led.class.getName(), "init()");
      relay0 = new Relay (portMan.acquirePort(PortMan.relay0_label, "LEDrelay"));

      relay1 = new Relay (portMan.acquirePort(PortMan.relay1_label, "LEDrelay"));

      relay2 = new Relay (portMan.acquirePort(PortMan.relay2_label, "LEDrelay"));

      relay3 = new Relay (portMan.acquirePort(PortMan.relay3_label, "LEDrelay"));

      logger.exiting(Led.class.getName(), "init()");

    }
    public void periodic() {
      relay0.setDirection(Direction.kReverse);
      relay0.set(Relay.Value.kOn);
  
    }
  }

