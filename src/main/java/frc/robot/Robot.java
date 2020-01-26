/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystem.DisplayManager;
import frc.robot.subsystem.PortMan;
import frc.robot.subsystem.SubsystemFactory;
import frc.robot.util.OzoneLogger;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {

  static Logger logger = Logger.getLogger(Robot.class.getName());
  private static SubsystemFactory subsystemFactory;

  private DisplayManager dManager;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    subsystemFactory = SubsystemFactory.getInstance(isReal());
    OzoneLogger.getInstance().init(Level.FINE);
    dManager = new DisplayManager();

    try {
      subsystemFactory.init(dManager, new PortMan());

    } catch (Exception e) {
      StringWriter writer = new StringWriter();
      PrintWriter pw  = new PrintWriter(writer);
      e.printStackTrace(pw);
      logger.severe(writer.toString());
    }
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
       CommandScheduler.getInstance().run();
       dManager.update();
  }

  /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

  }

  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {

  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {
  }


  @Override
  public void testInit() {
    // TODO Auto-generated method stub

  }
  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    
  }
}
