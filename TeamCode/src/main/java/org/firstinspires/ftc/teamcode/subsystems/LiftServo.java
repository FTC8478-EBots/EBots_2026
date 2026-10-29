package org.firstinspires.ftc.teamcode.subsystems;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LiftServo {
    private double POSITION = 0.;
    Servo lift;
    Telemetry telemetry;


    public LiftServo(HardwareMap hardwareMap, Telemetry telemetry, String liftServo) {
        lift = hardwareMap.get(Servo.class, liftServo);
        this.telemetry = telemetry;
        lift.setPosition(POSITION);
    }
}