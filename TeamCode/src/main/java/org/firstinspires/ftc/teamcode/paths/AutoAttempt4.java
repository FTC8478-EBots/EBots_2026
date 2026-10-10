package org.firstinspires.ftc.teamcode.paths;

import static com.pedropathing.api.Paths.*;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

public class AutoAttempt4 {

    private final PoseFactory poseFactory = PoseFactory.degrees();

    public final Pose start = poseFactory.of(56, 8, 90);
    private final Pose point1 = poseFactory.of(56, 36, 90);
    private final Pose point2 = poseFactory.of(23.24, 39.1661, 174.4798);
    private final Pose point3 = poseFactory.of(14.7351, 90.925, 99.3314);

    public Path path_1() {return line(start, point1).linear(start,point1);
    }

    public Path path_2() {
        return line(point1, point2).tangent();
    }

    public Path path_3() {
        return line(point2, point3).tangent();
    }
}