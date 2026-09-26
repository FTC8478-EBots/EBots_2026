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
    public static int LAUNCH_VELOCITY = 0;
    public static double PUSHER_OFFSET = -0.03;
    DcMotorEx launchMotor;
    Gamepad gamepad;
    Telemetry telemetry;

    double targetVelocity;
    boolean pressed;

    public Launch(HardwareMap hardwareMap, Gamepad gamepad, Telemetry telemetry) {
        launchMotor = hardwareMap.get(DcMotorEx.class, "launch");
        this.gamepad = gamepad;
        launchMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    //    launchMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        this.telemetry = telemetry;


    }

    public void processGamepad() {

        if (gamepad.dpad_left) {
            if (!pressed) {
                pressed = true;
                LAUNCH_VELOCITY -= 200;
                launchMotor.setVelocity(LAUNCH_VELOCITY);
            }
        } else if (gamepad.dpad_right) {
            if (!pressed) {
                pressed = true;
                LAUNCH_VELOCITY += 200;
                launchMotor.setVelocity(LAUNCH_VELOCITY);
            }
        } else {
            pressed = false;
        }
     //   if (/*!launchDetector.isArtifactDetected()/true && gamepad.triangle) {
       //     nextPostion();
       // }
        telemetry.addData("LAUNCH_VELOCITY:", launchMotor.getVelocity());

    }

    private void nextPostion() {
    }//if error occurs check nextPosition

    boolean isFast() {
        return (launchMotor.getVelocity()/LAUNCH_VELOCITY)>.8;
    }

   // public class LaunchAction implements Action {
      //  private boolean initialized = false;
        public void LaunchAction(double launchVelocity) {
            targetVelocity = launchVelocity;
            launchMotor.setVelocity(targetVelocity);

        }


    }
