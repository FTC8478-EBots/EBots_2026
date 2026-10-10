package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Launch;

@TeleOp
public class TestTeleop extends OpMode    {
    Launch launch;
    public void init(){
        launch = new Launch(hardwareMap,gamepad2,telemetry);
    }
    public void start(){
    }
    public void loop(){
      launch.processGamepad();
    }
}
