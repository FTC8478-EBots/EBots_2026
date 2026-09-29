package org.firstinspires.ftc.teamcode.opmodes;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import static com.pedropathing.api.Paths.line;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous
public class Autonomous926 {
    private final PoseFactory poseFactory = PoseFactory.degrees();

    Follower  follower;
    private final Pose start = poseFactory.of(8.1014, 62.0285, 90);
    private final Pose redflowerStart = poseFactory.of(8.1014, 62.0285, 180);
    private final Pose redflower = poseFactory.of(24.0184, 50.161, 180);
    private final Pose bluefarflower = poseFactory.of(94.8139, 18.5693, 270);
    private final Pose blueflower = poseFactory.of(119.3649, 93.4892, 0);
    private final Pose redfarflower = poseFactory.of(46.1186, 123.8451, 90);
    private final Pose park = poseFactory.of(11.5509, 118.5419, 90);

    public Path redflower() {
        return line(redflowerStart, redflower).linear(redflowerStart, redflower);
    }

    public Path bluefarflower() {
        return line(redflower, bluefarflower).linear(redflower, bluefarflower);
    }

    public Path blueflower() {
        return line(bluefarflower, blueflower).linear(bluefarflower, blueflower);
    }

    public Path redfarflower() {
        return line(blueflower, redfarflower).linear(blueflower, redfarflower);
    }

    public Path park() {
        return line(redfarflower, park).linear(redfarflower, park);
    }
    private Command autoRoutine() {

        return sequential(
                follow(follower, redflower()),
                follow(follower, bluefarflower()),
                follow(follower, blueflower()),
                follow(follower, redfarflower()),
                // Add mechanism commands here.
                follow(follower, park())
        );
    }
    //@Override
    public void init() {
        Scheduler.reset();

        follower = Constants.create(hardwareMap);
        follower.setPose(start);
        follower.update();
    }
    //@Override
    public void start() {
        schedule(autoRoutine());
    }
    //@Override
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


