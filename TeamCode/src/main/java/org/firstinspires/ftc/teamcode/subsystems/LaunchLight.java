package org.firstinspires.ftc.teamcode.subsystems;


import static org.firstinspires.ftc.teamcode.subsystems.Launch.LAUNCH_MOTOR_ON;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LaunchLight {
    public static int MIN_LAUNCH_LIGHT = 800;
    public static int MAX_LAUNCH_LIGHT = 2500;
    DcMotorEx launchMotor;
    public static double LAUNCH_VELOCITY;
    Servo hopperServo;
    Servo light;
    Telemetry telemetry;
    int position;

    public LaunchLight(HardwareMap hardwareMap, Telemetry telemetry, String launchLight) {
        light = hardwareMap.get(Servo.class, launchLight);
        this.telemetry = telemetry;
    }
    public void CheckLight() {
        LAUNCH_VELOCITY = launchMotor.getVelocity();
        if (!LAUNCH_MOTOR_ON) {
            setBlack();
        } else if (LAUNCH_VELOCITY < MIN_LAUNCH_LIGHT) {
            setBlue();
        } else if (LAUNCH_VELOCITY > MAX_LAUNCH_LIGHT) {
            setRed();
        } else {
            setGreen();
        }
    }

    public void setRed() {light.setPosition(0.28);}

    public void setGreen() {light.setPosition(0.5);}

    public void setYellow() {light.setPosition(0.388);}

    public void setBlue() {light.setPosition(0.611);}

    public void setViolet() {light.setPosition(0.722);}

    public void setWhite() {light.setPosition(1.0);}

    public void setBlack() {light.setPosition(0.0);}
}




