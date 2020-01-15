package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.hal.SimBoolean;
import edu.wpi.first.hal.SimDevice;
import edu.wpi.first.hal.SimDouble;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.util.Units;

public class RageEncoder extends Encoder {

  private double kV;
  private double kA;

  private DoubleSupplier motorPower;

  private SimBoolean simConnected;

  private SimDevice simDevice;
  private SimDouble simPosition;
  private SimDouble simVelocity;

  private double velocity;
  private double position;

  private double ppr;

  private final double DT = 0.02;

  /**
   * Encoder wrapper class that allows sim
   * 
   * @param port1
   * @param port2
   * @param freeSpeed
   * @param reduction
   * @param motorPower
   */
  public RageEncoder(int port1, int port2, Motor motor, double numMotors, double moment, double reduction, double mass, double ppr,DoubleSupplier motorPower) {

    super(port1, port2);

    if (Robot.isSimulation()) {
      simDevice = SimDevice.create("RageEncoder", port1);
      simConnected = simDevice.createBoolean("Connected", false, true);
      simPosition = simDevice.createDouble("Position", false, 0);
      simVelocity = simDevice.createDouble("Velocity", false, 0);

    }

    // 1 is max power
    this.kV = motor.kv;
    this.kA = 12 / ((numMotors * motor.kt * reduction) / (moment * mass));

    this.motorPower = motorPower;
    this.ppr = ppr;
    position = 0;
    velocity = 0;
  }

  public double getPpr() {
    return ppr;
  }

  public void setPpr(double ppr) {
    this.ppr = ppr;
  }

  public void periodic() {

    double acceleration = getAcceleration(velocity);
    position += velocity * DT;
    //position = position * ppr * (super.getDistancePerPulse());
    velocity += acceleration * DT;
    //velocity = velocity * ppr * (super.getDistancePerPulse());

    simPosition.set(position);
    simVelocity.set(velocity);
  }
  
  @Override
  public double getDistance() {
    if (simDevice == null) {
      return super.getDistance();
    } else {
      return position;
    }
  }

  @Override
  public double getRate() {
    if (simDevice == null) {
      return super.getRate();
    } else {
      return velocity;
    }
  }

  private double getVoltage() {
    return motorPower.getAsDouble() * 12;
  }

  private double getAcceleration(double velocity) {
    return (getVoltage() - kV * velocity) / kA;
  }
  
}