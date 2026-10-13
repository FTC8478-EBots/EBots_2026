package org.firstinspires.ftc.teamcode.paths;

import static com.pedropathing.api.Paths.line;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class CLOSESIDEADDITION {

        private final PoseFactory poseFactory = PoseFactory.degrees();

        public final Pose start = poseFactory.of(57.9457, 126.7025, -90);
        private final Pose point1 = poseFactory.of(57.9258, 113.7307, -90.0878);
        private final Pose point2 = poseFactory.of(10.2804, 109.5831, 169.5345);



public Path path_1() {return line(start, point1).linear(start,point1);
}

public Path path_2() {
    return line(point1, point2).tangent();
}


    }

