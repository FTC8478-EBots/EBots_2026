package org.firstinspires.ftc.teamcode.subsystems;

// import static org.firstinspires.ftc.teamcode.subsystems.LaunchLight.MAX_LAUNCH_LIGHT;

import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;

import androidx.annotation.NonNull;

import com.pedropathing.ivy.Command;
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

import java.util.Collections;
import java.util.Set;


public class Launch {

    double F=2.63;
    double P=330.0;
    public static boolean LAUNCH_MOTOR_ON;
    public static int LAUNCH_VELOCITY = 1120;
    public static boolean LAUNCH_AT_VELOCITY;
    public static final int LAUNCH_INCREMENT=20;
    public static double TURN_OFF_TIME = 10.0;
    public static double STOW_POSTION=0;
    public static double LAUNCH_POSTION=0.5;
    //ElapsedTime motorTimer;

    DcMotorEx launchMotor;
    Servo launchServo;
    Gamepad gamepad;
    Telemetry telemetry;

    private ElapsedTime mechanismTimer = new ElapsedTime();
    double targetVelocity;
    boolean pressed;
    boolean trianglePressed;
    RobotHardware robot=RobotHardware.getRobot();


    public Launch(HardwareMap hardwareMap, Gamepad gamepad, Telemetry telemetry) {
        launchMotor = hardwareMap.get(DcMotorEx.class, "launch");
        this.gamepad = gamepad;
        launchMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launchMotor.setVelocityPIDFCoefficients(P,0,0,F);
        launchMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        launchServo= hardwareMap.get(Servo.class, "launchServo");
        this.telemetry = telemetry;

    }
    public Command AutoLaunch(){

        return instant (() ->launchMotor.setVelocity(LAUNCH_VELOCITY));
        }
        Command pause = waitMs(5000);
           return instant(() -> launchMotor.setVelocity(0));


    }

    public void processGamepad() {
        if (gamepad.dpad_up) {
            if (!pressed) {
                pressed = true;
                launchMotor.setVelocity(LAUNCH_VELOCITY);
                LAUNCH_MOTOR_ON = true;
                robot.motorTimer.reset();
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

    if(gamepad.triangle && CheckLaunchVelocity()){
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
        if (!LAUNCH_MOTOR_ON) {
            LaunchMotorOff();
        } else if (currentVelocity < LAUNCH_VELOCITY * .9) {
            LaunchUnderTarget();
        } else if (currentVelocity > LAUNCH_VELOCITY * 1.1) {
            LaunchOverTarget();
        } else {
            LaunchAtTarget();
        }
    }
    public void LaunchMotorOff(){
        RobotHardware robot = RobotHardware.getRobot();
        robot.launchLight.setBlack();
    }
    public void LaunchUnderTarget(){
        RobotHardware robot = RobotHardware.getRobot();
        robot.launchLight.setBlue();
    }
    public void LaunchAtTarget(){
        RobotHardware robot = RobotHardware.getRobot();
        robot.launchLight.setGreen();
    }
    public void LaunchOverTarget(){
        RobotHardware robot = RobotHardware.getRobot();
        robot.launchLight.setRed();
    }
    public boolean CheckLaunchVelocity(){
        double currentVelocity = launchMotor.getVelocity();
        double MinVelocity = LAUNCH_VELOCITY * .9;
        double MaxVelocity = LAUNCH_VELOCITY * 1.1;
        if(currentVelocity > MinVelocity && currentVelocity < MaxVelocity) {
            LAUNCH_AT_VELOCITY = true;
        } else {
            LAUNCH_AT_VELOCITY = false;
        }
        return LAUNCH_AT_VELOCITY;
    }

   // public class LaunchAction implements Action {
      //  private boolean initialized = false;
        public void LaunchAction(double launchVelocity) {
            targetVelocity = launchVelocity;
            launchMotor.setVelocity(targetVelocity);

        }
     public void motorOnMotorOff() {
         double seconds = robot.motorTimer.seconds();
         if (LAUNCH_MOTOR_ON) {
           if (seconds > TURN_OFF_TIME) {
                launchMotor.setVelocity(0);
                LAUNCH_MOTOR_ON = false;
           }
         }
    }
    }