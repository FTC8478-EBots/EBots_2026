package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@TeleOp
public class FlyWheelTuning extends OpMode {
    public DcMotorEx flywheelMotor;
    double highVelocity = 1500;
    double lowVelocity = 900;

    double targetVelocity = 0;
    double P = 0;
    double F = 0;
    double step = 1;

    double timeToTarget = 0;
    double overshootSpeed = 0;
    double undershootSpeed = 10000;
    PIDFCoefficients pidfCoefficients;
    @Override
    public void init() {
        flywheelMotor = hardwareMap.get(DcMotorEx.class, "flywheel");
        flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        pidfCoefficients = new PIDFCoefficients(P,0,0,F);
        flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidfCoefficients);
    }
    @Override
    public void loop() {
        processGamepad(gamepad1);
        processStatistics();
        pidfCoefficients.p = P;
        pidfCoefficients.f = F;
        flywheelMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidfCoefficients);
        flywheelMotor.setVelocity(targetVelocity);
        telemetry.addLine("Bumpers change step");
        telemetry.addLine("Up/Down => P, Left/Right => F");
        telemetry.addData("Step", step);
        telemetry.addData("Coefficients","P = %f    F = %f",P, F);
        telemetry.addData("Tuning","Velocity %f / %f",flywheelMotor.getVelocity(),targetVelocity);

    }

    void processGamepad(Gamepad gamepad) {
        if (gamepad.leftBumperWasPressed()) step*=10.0;
        if (gamepad.rightBumperWasPressed()) step*=0.1;
        if (gamepad.dpadDownWasPressed()) P += step;
        if (gamepad.dpadUpWasPressed()) P -= step;
        if (gamepad.dpadLeftWasPressed()) F -= step;
        if (gamepad.dpadRightWasPressed()) F += step;
        if (gamepad.crossWasPressed()) {
            targetVelocity = 0;
        }
        if (gamepad.circleWasPressed()) {
            targetVelocity = 800;
        }
        if (gamepad.squareWasPressed()) {
            targetVelocity = 1200;
        }

    }

    void processStatistics() {

    }

}
