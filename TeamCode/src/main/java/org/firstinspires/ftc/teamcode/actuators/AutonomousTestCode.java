package org.firstinspires.ftc.teamcode.actuators;

import static com.pedropathing.api.Paths.line;

import com.pedropathing.follower.Follower;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.opencv.core.Point;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;


@Autonomous
public class AutonomousTestCode extends OpMode {
    private Follower follower;
    private final PoseFactory poseFactory = PoseFactory.degrees();
    // Poses
    private final Pose startPose = poseFactory.of(24, 24, 0);
    private final Pose scorePose = poseFactory.of(48, 48, 90);
    private final Pose parkPose = poseFactory.of(72, 48, 90);


    // Path methods
    private Path startToScore() {
        return line(startPose, scorePose).linear(startPose, scorePose);
    }

    private Path park() {
        return line(scorePose, parkPose).linear(scorePose, parkPose);
    }
    private Command autoRoutine() {
        return sequential(
                follow(follower, startToScore()),
                // Add mechanism commands here.
                follow(follower, park())
        );
    }
    @Override
    public void init() {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(startPose);
        follower.update();
    }

    @Override
    public void start() {
        schedule(autoRoutine());
    }

    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();
        // add your other methods needed in the loop here
        telemetry.addData("X", follower.pose().x());
        telemetry.addData("Y", follower.pose().y());
        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        telemetry.addData("Follower Mode", follower.mode());
        telemetry.update();
    }
}


/*package org.firstinspires.ftc.teamcode.actuators;

import static com.pedropathing.api.Paths.line;

import com.pedropathing.follower.Follower;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.pedropathing.utils.Timer;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous
public class AutonomousTestCode extends OpMode {
private Follower follower;

c    PathState pathState;
    private final PoseFactory poseFactory = PoseFactory.degrees();
    private final Pose start = poseFactory.of(59.2955, 8.6179, 90);
    private final Pose point1 = poseFactory.of(12.0148, 46.9929, 140.9358);
    private final Pose point2 = poseFactory.of(47.4413, 125.4667, 65.7035);
    private final Pose point3 = poseFactory.of(129.8693, 93.9012, -20.9542);
    private final Pose point4 = poseFactory.of(94.3152, 12.4164, -113.573);
    private final Pose point5 = poseFactory.of(59.7474, 8.761, -173.9636);

    private CompoundPath driveStartPosS
    public void buildPaths () {
        // Put in coordinates for staring pose > ending pose
                driveStartPosPoint1Pos = follower.pathBuilder()
                .addPath(new BezierLine(startPose,shootPose))
                .setLinerHeadingInerpolation(startPose.getHeading(),shootPose.getHeading())
                .build();
    }
    public void startPathUpdate(){
        switch(pathState) {
            case DRIVE_STARTPOS_PATH1POS:
                ;
                follower.followPath
                setPathState(PathState.DRIVE_PATH1POS_PATH22POS);  // reset timer and make new state
                break;

    @Override
    public void loop() {
        follower.update();
        startPathUpdate();;

        telemetry.addData("path state",pathState.toString());
        telemetry.addData("x",follower.getPos.getX());
        telemetry.addData("y",follower.getPos.getY());
        telemetry.addData("heading",follower.getPose().getHeading());
        telemetry.addData("Path time",follower.getPose().getElapsedTimeSeconds());
    }

    public class Paths {
       public Path path1() {
           return line(start, path1).tangent();
       }

       public Path path2() {
           return line(path1, path2).tangent();
       }

       public Path path3() {
           return line(path2, point3).tangent();
       }

       public Path path4() {
           return line(point3, point4).tangent();
       }

       public Path path5() {
           return line(point4, point5).tangent();
       }
   }
    public void init() {

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
    }

 */