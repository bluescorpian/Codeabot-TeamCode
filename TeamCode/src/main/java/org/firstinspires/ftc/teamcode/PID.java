package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PID {
    protected double Kp;
    protected double Ki;
    protected double Kd;

    public double lastReference = 0;
    public double integralSum = 0;
    public double lastError = 0;

    ElapsedTime timer = new ElapsedTime();

    public PID(double Kp, double Ki, double Kd) {
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kd = Kd;
    }

    public double update(double reference, double state) {
        double error = reference - state;
        double derivative = (error - lastError) / timer.seconds();
        integralSum = integralSum + (error * timer.seconds());
        lastReference = reference;
        timer.reset();
        return (Kp * error) + (Ki * integralSum) + (Kd * derivative);
    }

    public void reset() {
        integralSum = 0;
        lastError = 0;
        timer.reset();
    }
}
