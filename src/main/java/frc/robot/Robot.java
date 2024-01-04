// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


// set spark max encoder from absolute encoder
// add motor CAN numbers
// add motor watch dogs
// configAbsoluteSensorRange
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;

import com.revrobotics.AbsoluteEncoder;

import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.
//import com.ctre.phoenix.sensors.
/*import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.WPI_CANCoder;
import com.ctre.phoenix.sensors.CANCoderConfiguration;
import com.ctre.phoenix.sensors.CANCoderStatusFrame;*/

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  //private final CANCoder AbsoluteEncoder = new CANCoder(0);
  //private WPI_CANCoder CANCoder = new 



  // Joystick
  private XboxController controller = new XboxController(0);

  // Swerve drive
  private WheelDrive backLeft = new WheelDrive(2, 1,0);
  private WheelDrive backRight = new WheelDrive(4, 3,1);
  private WheelDrive frontLeft = new WheelDrive(6, 5,2);
  private WheelDrive frontRight = new WheelDrive(8, 7,3);
  private SwerveDrive swerveDrive = new SwerveDrive(backRight, backLeft, frontRight, frontLeft);

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    //AbsoluteEncoder.configSensorDirection(false);
    //AbsoluteEncoder.configAbsoluteSensorRange
    //AbsoluteEncoder.getAbsolutePosition();
    backLeft.zeroEncoders();
    /*backRight.zeroEncoders();
    frontLeft.zeroEncoders();
    frontRight.zeroEncoders();*/
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *s
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("AbsoluteEncoder", backLeft.returnabsolute());
    //SmartDashboard.putNumber("backLeft relative encoder", backLeft.returnRelative());
    SmartDashboard.putNumber("backRight relative encoder", backLeft.returnRelative());
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    swerveDrive.drive(controller.getLeftX(), controller.getLeftY(), controller.getRightX());
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
