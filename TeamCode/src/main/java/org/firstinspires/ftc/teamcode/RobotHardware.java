package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Launch;
import org.firstinspires.ftc.teamcode.subsystems.LaunchLight;

public class RobotHardware {
    private RobotHardware() {
    }


    public static RobotHardware getRobot() {
        if (robotHardware == null) {
            robotHardware = new RobotHardware();
        }
        return robotHardware;
    }

    private static RobotHardware robotHardware;
    public Launch launch;
    public LaunchLight launchLight;
    public Intake intake;
    public ElapsedTime motorTimer;
    public ElapsedTime intakeTimer;


    public void init(HardwareMap hardwareMap, Gamepad gamepad1, Gamepad gamepad2, Telemetry telemetry) {
        launch = new Launch(hardwareMap, gamepad2, telemetry);
        intake = new Intake(hardwareMap, gamepad2, telemetry);
        launchLight = new LaunchLight(hardwareMap, telemetry, "launchLight");
        motorTimer = new ElapsedTime();
        intakeTimer = new ElapsedTime();
    }
    //public ElapsedTime stateTimer = new ElapsedTime();
}