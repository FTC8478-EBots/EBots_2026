package org.firstinspires.ftc.teamcode.subsystems;

import static com.pedropathing.api.Paths.*;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class FarFromParkSimple {

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(58.3576, 10.1246, 90);
    private final Pose point1 = poseFactory.of(58.3523, 12.0247, 90.1608);
    private final Pose point2 = poseFactory.of(10.779, 11.1545, -90.4373);
    private final Pose point2Control1 = poseFactory.of(10.7198, 34.8748, 0);
    private final Pose point3 = poseFactory.of(58.845, 10.2751, 88.2134);
    private final Pose point3Control1 = poseFactory.of(58.9971, 7.3646, 0);
    private final Pose point4 = poseFactory.of(14.6924, 94.5715, 96.2723);
    private final Pose point4Control1 = poseFactory.of(19.5437, 48.8304, 0);

    public Path path1() {
        return line(start, point1).tangent();
    }

    public Path path2() {
        return curve(point1, point2Control1, point2).tangent();
    }

    public Path path3() {
        return curve(point2, point3Control1, point3).tangent();
    }

    public Path path4() {
        return curve(point3, point4Control1, point4).tangent();
    }
}