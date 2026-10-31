package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Prism.PrismAnimations;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.subsystems.PrismLights;

import java.util.HashMap;

public class RobotHardware {


    public Follower follower;
    public PrismLights prism;

    private static RobotHardware instance;


    private boolean initialized = false;
    private RobotHardware() {};
    public static RobotHardware getInstance() {
        if (instance == null) {
            instance = new RobotHardware();
        }
        return instance;
    }


    public void init(HardwareMap hardwareMap) {
        if (initialized) return;

        follower = Constants.create(hardwareMap);
        prism = new PrismLights(hardwareMap,"prism");



        initialized = true;
    }
}
