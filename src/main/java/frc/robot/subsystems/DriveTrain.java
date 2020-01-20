/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
<<<<<<< HEAD
import edu.wpi.first.wpilibj.Encoder;
=======
>>>>>>> master
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new driveTrain.
   */
  private WPI_VictorSPX frontLeft;
  private WPI_VictorSPX frontRight;
  private WPI_VictorSPX backLeft;
  private WPI_VictorSPX backRight;

  private SpeedControllerGroup leftDrive;
  private SpeedControllerGroup rightDrive;
  private DifferentialDrive myRobot;

<<<<<<< HEAD
  private Encoder leftEncoder;
  private Encoder rightEncoder;
  
  private double reverse;
=======
>>>>>>> master
  /***
   * 
   * <p>Initalizes drive motors and helper classes.</p>
   */
<<<<<<< HEAD
  

=======
>>>>>>> master
  public DriveTrain() {
    frontLeft = new WPI_VictorSPX(0);
    frontRight = new WPI_VictorSPX(1);
    backLeft = new WPI_VictorSPX(2);
    backRight = new WPI_VictorSPX(3);
    frontLeft.setInverted(true);
    frontRight.setInverted(true);
    backLeft.setInverted(true);
    backRight.setInverted(true);

    leftDrive = new SpeedControllerGroup(frontLeft, backLeft);
    rightDrive = new SpeedControllerGroup(frontRight, backRight);
    myRobot = new DifferentialDrive(leftDrive, rightDrive);
<<<<<<< HEAD

    leftEncoder = new Encoder(0, 1);
    rightEncoder = new Encoder(2, 3);

    reverse = 1;
=======
>>>>>>> master
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

<<<<<<< HEAD
  public void arcadeDrive(double left, double right) {
    myRobot.arcadeDrive(left, right);
=======
  public void arcadeDrive(double forward, double rotation) {
    myRobot.arcadeDrive(forward, rotation);
>>>>>>> master
  }

  public void tankDrive(double left, double right) {
    myRobot.tankDrive(left, right);
  }
<<<<<<< HEAD

  public double leftEncoderValue() {
    return leftEncoder.getDistance();
  }

  public void downPov() {
    reverse = -reverse;
  }

  public double getReverse() {
    return reverse;
  }

=======
>>>>>>> master
}
