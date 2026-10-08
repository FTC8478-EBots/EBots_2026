package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
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
        return new Follower(
                new PinpointLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }
    //return new Follower(Drivetrain, Localizer, Foresight);
    // return follower;


    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(5.1188581571804255);
        c.yPodOffset.set(-7.558364267424336);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.14036353137158894);
                Controller secondaryTranslationalForward = Controller.proportional(0.05186057425916839);
                Controller primaryTranslationalLateral = Controller.proportional(-22.56785502647913);
                Controller secondaryTranslationalLateral = Controller.proportional(-8.338219408091675);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.01689976730342463));
                c.brake.set(Controller.proportionalFeedforward(0.014364802207910934));

                c.headingFeedback.set(Controller.proportional(2.836393854253073));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.03997878543150817, 0.0051725883165152295));

                c.linearBrakeCoefficients.set(Matrix.diag(0.061866377651676324, 0.04698664085053138));
                c.quadraticBrakeCoefficients.set(Matrix.diag(8.867349251003884E-4, 0.001264691251047718));

                c.maxAchievableForwardVelocity.set(61.758728418425015);
                c.maxAchievableStrafeVelocity.set(51.711837058009166);
                c.naturalForwardDeceleration.set(41.44485481483619);
                c.naturalStrafeDeceleration.set(66.5292790367798);
            }
    );
}

