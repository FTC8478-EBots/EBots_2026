package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.utils.Timer;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class SampleAutoPathing extends OpMode {
private Follower follower;
private Timer pathTimer, opModeTimer;
    public enum PathState {
        //Start Position End Position
         DRIVE_STARTPOS_SHOOT_POS,
        SHOOT_PRELOAD
    }
    PathState pathState;
     private final Pose startPose = new Pose(58.342715231788084,8.468543046357619,Math.toRadians(0));
    private final Pose launchpose = new Pose(87.85182119205298,34.99430877483443,Math.toRadians(0));
    private PathChain driveStartposShootPos;

    public void buildPaths (){
        //HAPPY
        driveStartposShootPos = follower.pathBuilder()
    @Override
    public void init() {
// hi ^_^
    }
    @Override
    public void loop(){

    }
}

