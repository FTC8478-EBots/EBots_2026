package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
    c.frontLeftName.set("frontLeft");
    c.frontRightName.set("frontRight");
    c.backLeftName.set("backLeft");
    c.backRightName.set("backRight");
    c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
    c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
    c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
    c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
    c.manualBrakeMode.set(true);
});
    public static Follower create(HardwareMap h) {
        // return new Follower(Drivetrain, Localizer, Foresight);
        return null;
    }
    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(2.6125903392401266);
        c.yPodOffset.set(-1.455521020363635);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });
    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.14291673428863613);
                Controller secondaryTranslationalForward = Controller.proportional(0.05280391451417892);
                Controller primaryTranslationalLateral = Controller.proportional(0.19504243736907886);
                Controller secondaryTranslationalLateral = Controller.proportional(0.07206296897796417);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.01760103831043525));
                c.brake.set(Controller.proportionalFeedforward(0.014960882563869961));

                c.headingFeedback.set(Controller.proportional(3.1655989308498294));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.037233614305661356, 0.00589869059522148));

                c.linearBrakeCoefficients.set(Matrix.diag(0.05026476869724857, 0.05531722194335627));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0012029995373518253, 0.0010502399683843483));

                c.maxAchievableForwardVelocity.set(60.36587520395926);
                c.maxAchievableStrafeVelocity.set(52.342732796614555);
                c.naturalForwardDeceleration.set(42.4252194423178);
                c.naturalStrafeDeceleration.set(57.52036661866967);
            }
    );
}

