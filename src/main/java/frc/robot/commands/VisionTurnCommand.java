/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.DrivePid;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class VisionTurnCommand extends PIDCommand {
  private DriveTrain driveTrain;
  /**
   * Turns the robot to the specified degree.
   * 
   * @param driveTrain the driveTrain subsystem used
   */

  public VisionTurnCommand(DriveTrain driveTrain) {
    super(
        // The controller that the command will use
        new PIDController(DrivePid.PTURN, DrivePid.ITURN, DrivePid.DTURN),
        // This should return the measurement
        driveTrain::getGyro,
        // This should return the setpoint (can also be a constant)
        0,
        // This uses the output
        output -> {
          // Use the output here
          driveTrain.arcadeDrive(0, output);
        },
        driveTrain);
    this.driveTrain = driveTrain;
    // Configure additional PID options by calling `getController` here.
    getController().enableContinuousInput(-180, 180);
    getController().setTolerance(DrivePid.TURN_TOLERANCE);
  }

  @Override
  public void initialize() {
    driveTrain.resetGyro();
    super.initialize();
    m_setpoint = () -> driveTrain.getVisionYaw();
  }
  
  @Override
  public void execute() {
    SmartDashboard.putNumber("vision pid setpoint", getController().getSetpoint());
    super.execute();
  }

  @Override
  public void end(boolean interrupted) {
    driveTrain.tankDrive(0, 0);
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
