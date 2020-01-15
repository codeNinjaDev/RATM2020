/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Motor;
import frc.robot.RageEncoder;
import frc.robot.RageVictorSPX;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new driveTrain.
   */

  private RageVictorSPX frontLeft;
  private RageVictorSPX frontRight;
  private RageVictorSPX backLeft;
  private RageVictorSPX backRight;

  private RageEncoder leftEncoder;
  private RageEncoder rightEncoder;

  private SpeedControllerGroup leftDrive;
  private SpeedControllerGroup rightDrive;
  private DifferentialDrive myRobot;

  /***
   * 
   * <p>Initalizes drive motors and helper classes.</p>
   */
  public DriveTrain() {
    frontLeft = new RageVictorSPX(0);
    frontRight = new RageVictorSPX(1);
    backLeft = new RageVictorSPX(2);
    backRight = new RageVictorSPX(3);

    leftDrive = new SpeedControllerGroup(frontLeft, backLeft);
    rightDrive = new SpeedControllerGroup(frontRight, backRight);

    leftEncoder = new RageEncoder(0, 1,  Motor.kCIM, 2, 0.1016, 7.31, 68, 360, () -> leftDrive.get());
    rightEncoder = new RageEncoder(2, 3,  Motor.kCIM, 2, 0.1016, 7.31, 68, 360, () -> rightDrive.get());

    leftEncoder.setDistancePerPulse((4*Math.PI) / leftEncoder.getPpr());
    rightEncoder.setDistancePerPulse((4*Math.PI) / rightEncoder.getPpr());


    myRobot = new DifferentialDrive(leftDrive, rightDrive);
    myRobot.setDeadband(0.05);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    leftEncoder.periodic();
    rightEncoder.periodic();
  }

  public void arcadeDrive(double forward, double rotation) {
    myRobot.arcadeDrive(forward, rotation);
  }

  public void tankDrive(double left, double right) {
    myRobot.tankDrive(left, right);
  }
}
