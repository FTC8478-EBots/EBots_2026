
  /*  import static com.pedropathing.api.Paths.*;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

    public class Paths {

        private final PoseFactory poseFactory = PoseFactory.degrees();

        private final Pose start = poseFactory.of(57.9457, 126.7025, 90);
        private final Pose point1 = poseFactory.of(57.9258, 113.7307, -90.0878);
        private final Pose point2 = poseFactory.of(14.2804, 122.5831, 168.5345);

        public Path path1() {
            return line(start, point1).tangent();
        }

        public Path path2() {
            return line(point1, point2).tangent();
        }
    }
}


   */

  package org.firstinspires.ftc.teamcode.opmodes;
  import com.pedropathing.api.PoseFactory;
  import com.pedropathing.follower.Follower;
  import com.pedropathing.ivy.Command;
  import com.pedropathing.ivy.Scheduler;
  import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
  import com.qualcomm.robotcore.eventloop.opmode.OpMode;

  import static com.pedropathing.api.Paths.line;
  import static com.pedropathing.ivy.Scheduler.schedule;
  import static com.pedropathing.ivy.groups.Groups.sequential;
  import static com.pedropathing.ivy.pedro.PedroCommands.follow;

  import org.firstinspires.ftc.teamcode.paths.CLOSESIDEADDITION;
  import org.firstinspires.ftc.teamcode.paths.FARSIDEADDITION;
  import org.firstinspires.ftc.teamcode.pedro.Constants;
  import org.firstinspires.ftc.teamcode.subsystems.Launch;


  @Autonomous
  public class DinonuggetsCLOSESIDE extends  OpMode {

      Follower follower;
      Launch launcher;
      private final PoseFactory poseFactory = PoseFactory.degrees();
      CLOSESIDEADDITION autoAttempt4 = new CLOSESIDEADDITION();

      private final Command autoRoutine () {

          return sequential(
                  // instant(() -> launcher.LaunchAction(1200)),
                  follow(follower,autoAttempt4.path_1()),

                  follow(follower, autoAttempt4.path_2())
                 // follow(follower, autoAttempt4.path_3())

                  // Add mechanism commands here.
                  //follow(follower, park())
          );
      }
      @Override
      public void init () {
          Scheduler.reset();

          follower = Constants.create(hardwareMap);
          follower.setPose(autoAttempt4.start);
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

