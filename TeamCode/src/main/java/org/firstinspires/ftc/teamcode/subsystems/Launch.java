package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;


public class Launch {
    public static int LAUNCH_VELOCITY = -1300;
    public static double PUSHER_OFFSET = -0.03;
    DcMotorEx launchMotor;
    Gamepad gamepad;
    Telemetry telemetry;
    boolean pressed = false;

    double targetVelocity;


    public Launch(HardwareMap hardwareMap, Gamepad gamepad, Telemetry telemetry, AutoSteerCamera autoSteerCamera) {
        launchMotor = hardwareMap.get(DcMotorEx.class, "launch");
        this.gamepad = gamepad;
        launchMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launchMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        this.telemetry = telemetry;

        // TEST: Always have the motor on
        //launchMotor.setVelocity(LAUNCH_VELOCITY);
    }

    void processGamepad() {
        //Testing always on motor in initialization so this code does nothing right now
        if (gamepad.square) {
            launchMotor.setVelocity(-LAUNCH_VELOCITY);

        } else if (gamepad.triangle) {
            launchMotor.setVelocity(LAUNCH_VELOCITY);

        } else {
            launchMotor.setVelocity(0);
        }

        if (gamepad.dpad_left) {
            if (!pressed) {
                LAUNCH_VELOCITY -= 20;
                pressed = true;
            }
        } else if (gamepad.dpad_right) {
            if (!pressed) {
                LAUNCH_VELOCITY += 20;
                pressed = true;
            }
        } else {
            pressed = false;
        }
        if (/*!launchDetector.isArtifactDetected()*/true && gamepad.triangle) {
            nextPostion();
        }
        telemetry.addData("LAUNCH_VELOCITY:", launchMotor.getVelocity());

    }

    private void nextPostion() {
    }//if error occurs check nextPosition

    boolean isFast() {
        return (launchMotor.getVelocity() / LAUNCH_VELOCITY) > .8;
    }

    public class LaunchAction implements Action {
        private boolean initialized = false;

        public LaunchAction(double launchVelocity) {
            targetVelocity = launchVelocity;
            launchMotor.setVelocity(targetVelocity);

        }


    }
}