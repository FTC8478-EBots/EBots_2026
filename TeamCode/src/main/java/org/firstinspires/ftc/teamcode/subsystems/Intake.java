package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotHardware;

public class Intake {
    public static boolean INTAKE_MOTOR_ON;
    public static double TURN_OFF_INTAKE_TIME = 10.0;
    //ElapsedTime intakeTimer;
    DcMotorEx intakeMotor;
    Gamepad gamepad;
double INTAKE_VELOCITY =800;
    public Intake(HardwareMap hardwareMap, Gamepad gamepad, Telemetry telemetry) {
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intake");
        this.gamepad = gamepad;
        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //    launchMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    }
    public void processGamepad(){
        if (gamepad.circle) {
            intakeMotor.setVelocity(INTAKE_VELOCITY);
            INTAKE_MOTOR_ON = true;
         //   intakeTimer.reset();
        } else if (gamepad.cross) {
            intakeMotor.setVelocity(0);
        }
    }
    public void intakeMotorOnMotorOff() {
        RobotHardware robot=RobotHardware.getRobot();
        double seconds = robot.motorTimer.seconds();
        if (INTAKE_MOTOR_ON) {
            if (seconds > TURN_OFF_INTAKE_TIME) {
                intakeMotor.setVelocity(0);
                INTAKE_MOTOR_ON = false;
            }
        }
    }
}
