package org.firstinspires.ftc.teamcode.opmodes;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import static com.pedropathing.api.Paths.line;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import org.firstinspires.ftc.teamcode.pedro.Constants;

import static com.pedropathing.api.Paths.*;


@Autonomous
public class auto2 extends  OpMode {
   // private final PoseFactory poseFactory = PoseFactory.degrees();

 /*   public class Paths {

        private final PoseFactory poseFactory = PoseFactory.degrees();

        private final Pose start = poseFactory.of(57.4307, 9.7127, 90);
        private final Pose point1 = poseFactory.of(55.77, 31.075, 94.4453);
        private final Pose point2 = poseFactory.of(21.3863, 49.6705, 151.5944);
        private final Pose point3 = poseFactory.of(15.9229, 90.2926, 97.66);

        public Path path1() {
            return line(start, point1).tangent();
        }

        public Path path2() {
            return line(point1, point2).tangent();
        }

        public Path path3() {
            return line(point2, point3).tangent();
        }
    }*/
    Follower  follower;

        private final PoseFactory poseFactory = PoseFactory.degrees();

        private final Pose start = poseFactory.of(57.4307, 9.7127, 90);
        private final Pose point1 = poseFactory.of(33.1135, 17.2751, 162.7248);
        private final Pose point2 = poseFactory.of(21.3863, 49.6705, 109.9004);
        private final Pose point3 = poseFactory.of(15.9229, 90.2926, 97.66);

        public Path path1() {
            return line(start, point1).tangent();
        }

        public Path path2() {
            return line(point1, point2).tangent();
        }

        public Path path3() {
            return line(point2, point3).tangent();
        }



    private Command autoRoutine() {

        return sequential(
                follow(follower, path1()),
                follow(follower, path2()),
                 follow(follower, path3())
       //         follow(follower, redfarflower()),
                // Add mechanism commands here.
               // follow(follower, park())
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


