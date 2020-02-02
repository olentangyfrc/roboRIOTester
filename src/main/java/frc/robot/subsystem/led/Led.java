/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.led;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.OzoneException;
import frc.robot.subsystem.PortMan;

public class Led extends SubsystemBase {

    private static Logger logger = Logger.getLogger(Led.class.getName());

    public Led() {

    }
      
    public void init(PortMan portMan) throws OzoneException {
      logger.entering(Led.class.getName(), "init()");

      logger.exiting(Led.class.getName(), "init()");

    }
  }

