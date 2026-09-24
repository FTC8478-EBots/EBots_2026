/*
package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.pedropathing.utils.Timer;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import com.pedropathing.paths.CompoundPath;

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
    private final Pose shootPose = new Pose(87.85182119205298,34.99430877483443,Math.toRadians(0));

    private CompoundPath driveStartPosShootPos; //PathChain?


    public void buildPaths () {
        // Put in coordinates for staring pose > ending pose
        driveStartPosShootPos = follower.pathBuilder()
                .addPath(new BezierLine(startPose,shootPose))
                .setLinerHeadingInerpolation(startPose.getHeading(),shootPose.getHeading())
                .build();
    }
    public void startPathUpdate(){
        switch(pathState) {
            case DRIVE_STARTPOS_SHOOT_POS:
                ;
                follower.followPath(driveStartPosShootPos, holdEnd, true);
                setPathState(PathState.SHOOT_PRELOAD);  // reset timer and make new state
                break;
            case SHOOT_PRELOAD:
                ;
                // check to see if follower is done it's path?
                if (!follower.isBusy()) {
                    // ToDo add Logic to shoot
                    telemetry.addLine("Done Path 1");
                }
                break;
            default:
                telemetry.addLine("No State Commanded");
                break;
        }

    }
    public void setPathState(PathState newState){
        pathState = newState;
        pathTimer.reset();
    }

    @Override
    public void init() {
//
        pathState = PathState.DRIVE_STARTPOS_SHOOT_POS;
        pathTimer = new Timer();
        opModeTimer = new Timer();
        opModeTimer.reset();
        follower = Constants.createFollower(hardwareMap);
        buildPaths();
        follower.setPose(startPose);

    }
    public void start(){
        opModeTimer.reset();
        setPathState(pathState);
    }

    @Override
    public void loop(){
        follower.update();
        startPathUpdate();;

        telemetry.addData("path state",pathState.toString());
        telemetry.addData("x",follower.getPose().getX());
        telemetry.addData("y",follower.getPose().getY());
        telemetry.addData("heading",follower.getPose().getHeading());
        telemetry.addData("Path time",follower.getPose().getElapsedTimeSeconds());
    }
}
*/
