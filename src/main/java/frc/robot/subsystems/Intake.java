/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private WPI_VictorSPX intakeBar;
  private WPI_VictorSPX leftConveyor;
  private WPI_VictorSPX rightConveyor;

  private SpeedControllerGroup conveyor;

  private final DigitalInput laserSwitch;
  /**
   * The intake subsystem containing motors and sensors necessary to intake balls
   * and move them into the position to shoot.
   */

  public Intake() {
    intakeBar = new WPI_VictorSPX(4);
    leftConveyor = new WPI_VictorSPX(5);
    rightConveyor = new WPI_VictorSPX(6);
    leftConveyor.setInverted(true);
    rightConveyor.setInverted(false);

    conveyor = new SpeedControllerGroup(leftConveyor, rightConveyor);

    laserSwitch = new DigitalInput(10);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Conveyor Voltage", leftConveyor.getMotorOutputVoltage());
    SmartDashboard.putNumber("Intake Bar Voltage", intakeBar.getMotorOutputVoltage());
  }

  public void intakeBar(double speed) {
    intakeBar.set(ControlMode.PercentOutput, speed);
  }

  public void conveyorControl(double speed) {
    conveyor.set(speed);
  }

  public boolean haveBall() {
    return laserSwitch.get();
  }

  
}
