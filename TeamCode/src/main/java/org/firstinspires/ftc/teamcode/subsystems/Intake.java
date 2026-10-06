package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {
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
        } else if (gamepad.cross) {
            intakeMotor.setVelocity(0);
        }

    }

}
