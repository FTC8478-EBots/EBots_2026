package org.firstinspires.ftc.teamcode.actuators;

import static com.pedropathing.api.Paths.line;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
@Autonomous
public class AutonomousTestCode extends OpMode {

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }

    public class Paths {

       private final PoseFactory poseFactory = PoseFactory.degrees();

       private final Pose start = poseFactory.of(59.2955, 8.6179, 90);
       private final Pose point1 = poseFactory.of(12.0148, 46.9929, 140.9358);
       private final Pose path2 = poseFactory.of(47.4413, 125.4667, 65.7035);
       private final Pose point3 = poseFactory.of(129.8693, 93.9012, -20.9542);
       private final Pose point4 = poseFactory.of(94.3152, 12.4164, -113.573);
       private final Pose point5 = poseFactory.of(59.7474, 8.761, -173.9636);

       public Path path1() {
           return line(start, point1).tangent();
       }

       public Path path2() {
           return line(point1, path2).tangent();
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
    }