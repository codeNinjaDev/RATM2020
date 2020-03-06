package frc.robot.modules;

public class TrajectoryMath {
    private static final double ANGLE = 30;
    private static final double ANGLE_RADS = Math.toRadians(ANGLE);
    private static final double SHOOTER_HEIGHT = 1.6;
    private static final double GRAVITY = 32.1522;
    private static final double GOAL_HEIGHT = 8.16;
    public static double getVelocityFromDistance(double distance)
    {
        double a = GRAVITY/2*Math.pow(distance,2);
        double b = (Math.sin(ANGLE_RADS) * Math.cos(ANGLE_RADS) * distance) - (Math.pow(Math.cos(ANGLE_RADS), 2) * (GOAL_HEIGHT-SHOOTER_HEIGHT));
        double c = Math.sqrt(a/b);
        return c*60/(.7*(2.0/12.0)*2*Math.PI);
    }
    public static double getDistanceFromPitch(double pitch)
    {
        return Math.abs((GOAL_HEIGHT - SHOOTER_HEIGHT) / Math.tan(Math.toRadians(pitch) + ANGLE_RADS));
    }
}
