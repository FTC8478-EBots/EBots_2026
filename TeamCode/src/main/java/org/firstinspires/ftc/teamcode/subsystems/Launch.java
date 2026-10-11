package org.firstinspires.ftc.teamcode.subsystems;

// import static org.firstinspires.ftc.teamcode.subsystems.LaunchLight.MAX_LAUNCH_LIGHT;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.teamcode.RobotHardware;


public class Launch {
    public static boolean LAUNCH_MOTOR_ON;
    public static int LAUNCH_VELOCITY = 1120;
    public static final int LAUNCH_INCREMENT=20;
    public static double TURN_OFF_TIME = 10.0;
    public static double STOW_POSTION=0;
    public static double LAUNCH_POSTION=0.5;
    ElapsedTime motorTimer;

    DcMotorEx launchMotor;
    Servo launchServo;
    Gamepad gamepad;
    Telemetry telemetry;

    private ElapsedTime mechanismTimer = new ElapsedTime();
    double targetVelocity;
    boolean pressed;
    boolean trianglePressed;

    public Launch(HardwareMap hardwareMap, Gamepad gamepad, Telemetry telemetry) {
        launchMotor = hardwareMap.get(DcMotorEx.class, "launch");
        this.gamepad = gamepad;
        //launchMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launchMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        launchServo= hardwareMap.get(Servo.class, "launchServo");
        this.telemetry = telemetry;

    }

    public void processGamepad() {
        if (gamepad.dpad_up) {
            if (!pressed) {
                pressed = true;
                launchMotor.setVelocity(LAUNCH_VELOCITY);
                LAUNCH_MOTOR_ON = true;
            }
        } else if (gamepad.dpad_down) {
                if (!pressed) {
                    pressed = true;
                    launchMotor.setVelocity(0);
                    LAUNCH_MOTOR_ON = false;
                }
        } else if (gamepad.dpad_left) {
            if (!pressed) {
                pressed = true;
                LAUNCH_VELOCITY -= LAUNCH_INCREMENT;
                launchMotor.setVelocity(LAUNCH_VELOCITY);
            }
        } else if (gamepad.dpad_right) {
            if (!pressed) {
                pressed = true;
                LAUNCH_VELOCITY += LAUNCH_INCREMENT;
                launchMotor.setVelocity(LAUNCH_VELOCITY);
            }
        } else {
            pressed = false;
        }

    if(gamepad.triangle){
        launchServo.setPosition(LAUNCH_POSTION);
    }else if (gamepad.square) {
        launchServo.setPosition(STOW_POSTION);
    }

    //   if (/*!launchDetector.isArtifactDetected()/true && gamepad.triangle) {
       //     nextPostion();
       // }
        telemetry.addData("CURRENT_VELOCITY:", launchMotor.getVelocity());
        telemetry.addData("LAUNCH_VELOCITY:", LAUNCH_VELOCITY);
        telemetry.addData("VELOCITY_%:", launchMotor.getVelocity() / LAUNCH_VELOCITY);
    }

    private void nextPostion() {
    }//if error occurs check nextPosition

    boolean isFast() {
        return (launchMotor.getVelocity()/LAUNCH_VELOCITY)>.8;
    }
    public void CheckLight() {
        double currentVelocity = launchMotor.getVelocity();
        RobotHardware robot = RobotHardware.getRobot();
        if (!LAUNCH_MOTOR_ON) {
            robot.launchLight.setBlack();
        } else if (currentVelocity < LAUNCH_VELOCITY * .9) {
            robot.launchLight.setBlue();
        } else if (currentVelocity > LAUNCH_VELOCITY * 1.1) {
            robot.launchLight.setRed();
        } else {
            robot.launchLight.setGreen();
        }
    }

   // public class LaunchAction implements Action {
      //  private boolean initialized = false;
        public void LaunchAction(double launchVelocity) {
            targetVelocity = launchVelocity;
            launchMotor.setVelocity(targetVelocity);

        }
     public void motorOnMotorOff() {
        RobotHardware robot=RobotHardware.getRobot();
         double seconds = robot.motorTimer.seconds();
        // if (LAUNCH_MOTOR_ON) {
         //   if (seconds > TURN_OFF_TIME) {
                //launchMotor.setVelocity(0);
                //LAUNCH_MOTOR_ON = false;
          //  }
         //}
    }
    }