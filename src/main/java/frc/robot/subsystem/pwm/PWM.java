/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.pwm;

import java.util.logging.Logger;


import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.OzoneException;
import frc.robot.subsystem.PortMan;

public class PWM extends SubsystemBase {
    private static Logger logger = Logger.getLogger(PWM.class.getName());

  Spark controller;
    public PWM() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    setOutput(0.5);
  }

  public void init(PortMan portman) throws OzoneException {
    logger.entering(PWM.class.getName(), "init()");
    controller = new Spark(portman.acquirePort(PortMan.pwm0_label, "PWMTesting"));
    logger.exiting(PWM.class.getName(), "init()");
  }

  public void setOutput(double speed) {
    controller.set(speed);
    logger.info("speedset "+speed);
  }

public static Double getPeriodms() {
	return null;
}
}
