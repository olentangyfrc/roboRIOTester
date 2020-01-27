/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem.pwm;

import java.util.logging.Logger;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.OzoneException;
import frc.robot.subsystem.PortMan;

public class PWM extends SubsystemBase {
    private static Logger logger = Logger.getLogger(PWM.class.getName());
  {
    public PWM() {
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void init(Portman portman) throws OzoneException {
    logger.entering(PWM.class.getName(), "init()");

    logger.exiting(PWM.class.getName(), "init()");
  }


  public double getPeriod() {
  }
}
