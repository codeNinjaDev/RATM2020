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
import frc.robot.RageVictorSPX;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new driveTrain.
   */
  private RageVictorSPX frontLeft;
  private RageVictorSPX frontRight;
  private RageVictorSPX backLeft;
  private RageVictorSPX backRight;

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
    myRobot = new DifferentialDrive(leftDrive, rightDrive);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  
  }

  public void arcadeDrive(double forward, double rotation) {
    myRobot.arcadeDrive(forward, rotation);
  }

  public void tankDrive(double left, double right) {
    myRobot.tankDrive(left, right);
  }
}
