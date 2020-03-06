/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.PidShooter;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html

public class TestHighShootCommandGroup extends SequentialCommandGroup {
  Intake intake;
  PidShooter pidShooter;
  /**
   * A temporary test command to shoot for the high goal.
   */
  
  public TestHighShootCommandGroup(double speed, double time, Intake intake, 
                                  PidShooter pidShooter) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
        new InstantCommand(() -> pidShooter.shootPower(-0.2)), 
        new RunCommand(() -> intake.conveyorControl(-0.1), intake).withTimeout(0.3),
        new InstantCommand(() -> intake.conveyorControl(0), intake),
        new InstantCommand(() -> pidShooter.shootPower(speed), pidShooter),
        new WaitCommand(time),
        new ParallelCommandGroup(
          new IntakeCommand(0.8, 0.87, intake)
        )
    );
    this.intake = intake;
    this.pidShooter = pidShooter;
  }
  
  @Override
  public void end(boolean interrupted) {
    intake.conveyorControl(0);
    pidShooter.stopShooting();
    intake.intakeBar(0);
  }
}
