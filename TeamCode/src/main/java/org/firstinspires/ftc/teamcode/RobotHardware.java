package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import org.firstinspires.ftc.teamcode.subsystems.Launch;
import org.firstinspires.ftc.teamcode.subsystems.LaunchLight;

public class RobotHardware {
public Launch launch;
public LaunchLight launchLight;
public void init() {
    launch = new Launch(hardwareMap, gamepad2, telemetry);
    launchLight = new LaunchLight(hardwareMap, telemetry,"LaunchLight");
}
}
