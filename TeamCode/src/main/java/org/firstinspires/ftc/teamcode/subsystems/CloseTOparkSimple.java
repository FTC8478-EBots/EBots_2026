package org.firstinspires.ftc.teamcode.subsystems;



import static com.pedropathing.api.Paths.*;

        import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class CloseTOparkSimple {

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(9.5432, 85.9208, 90);
    private final Pose point1 = poseFactory.of(60.6232, 15.8917, 88.1749);
    private final Pose point1Control1 = poseFactory.of(60.6361, 7.8457, 0);
    private final Pose point2 = poseFactory.of(11.1019, 12.048, -90.9216);
    private final Pose point2Control1 = poseFactory.of(55.9942, 36.1201, 0);
    private final Pose point2Control2 = poseFactory.of(10.9025, 27.7016, 0);
    private final Pose point3 = poseFactory.of(60.0626, 11.8333, 71.2021);
    private final Pose point3Control1 = poseFactory.of(10.3231, 33.7409, 0);
    private final Pose point3Control2 = poseFactory.of(67.8268, 32.941, 0);
    private final Pose point4 = poseFactory.of(10.573, 122.5831, -164.2053);
    private final Pose point4Control1 = poseFactory.of(70.3675, 140.1274, 0);

    public Path path1() {
        return curve(start, point1Control1, point1).tangent();
    }

    public Path path2() {
        return curve(point1, point2Control1, point2Control2, point2).tangent();
    }

    public Path path3() {
        return curve(point2, point3Control1, point3Control2, point3).reverseTangent();
    }

    public Path path4() {
        return curve(point3, point4Control1, point4).tangent();
    }
}