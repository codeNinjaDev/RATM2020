package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * Class that supports robot simulation.
 * Basically, if the robot is real, use victors, else, use sparks because they can be simmed.
 * Real simple.
 */


public class RageVictorSPX implements SpeedController {
  private SpeedController motor;

  /**
   * Creates a VictorSPX if real, if simulation it creates a spark.
   * 
   * @param deviceNumber port/can id
   */
  public RageVictorSPX(int deviceNumber) {

    if (Robot.isReal()) {
      motor = new WPI_VictorSPX(deviceNumber);
    } else {
      motor = new Spark(deviceNumber);
    }
    
  }

  @Override
  public void pidWrite(double output) {
    motor.set(-output);
  }

  @Override
  public void set(double speed) {
    motor.set(speed);

  }

  @Override
  public double get() {
    return motor.get();
  }

  @Override
  public void setInverted(boolean isInverted) {
    motor.setInverted(isInverted);
  }

  @Override
  public boolean getInverted() {
    return motor.getInverted();
  }

  @Override
  public void disable() {
    motor.disable();

  }

  @Override
  public void stopMotor() {
    motor.stopMotor();
  }

  


}