package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchSimple;

import org.firstinspires.ftc.teamcode.Prism.Color;
import org.firstinspires.ftc.teamcode.Prism.GoBildaPrismDriver;
import org.firstinspires.ftc.teamcode.Prism.PrismAnimations;

public class PrismLights {
    GoBildaPrismDriver prismDriver;
    public PrismLights(HardwareMap hardwareMap, String deviceName) {
        prismDriver = hardwareMap.get(GoBildaPrismDriver.class,deviceName);
        prismDriver.setStripLength(8);
        prismDriver.insertAndUpdateAnimation(GoBildaPrismDriver.LayerHeight.LAYER_0, new PrismAnimations.Solid(Color.BLUE));
        prismDriver.saveCurrentAnimationsToArtboard(GoBildaPrismDriver.Artboard.ARTBOARD_0);
        prismDriver.clearAllAnimations();
        prismDriver.insertAndUpdateAnimation(GoBildaPrismDriver.LayerHeight.LAYER_0, new PrismAnimations.Solid(Color.RED));
        prismDriver.saveCurrentAnimationsToArtboard(GoBildaPrismDriver.Artboard.ARTBOARD_1);
        prismDriver.clearAllAnimations();
        prismDriver.insertAndUpdateAnimation(GoBildaPrismDriver.LayerHeight.LAYER_0, new PrismAnimations.Solid(Color.GREEN));
        prismDriver.saveCurrentAnimationsToArtboard(GoBildaPrismDriver.Artboard.ARTBOARD_3);
        prismDriver.clearAllAnimations();
        prismDriver.insertAndUpdateAnimation(GoBildaPrismDriver.LayerHeight.LAYER_0, new PrismAnimations.Blink(Color.RED,Color.BLUE ));
        prismDriver.saveCurrentAnimationsToArtboard(GoBildaPrismDriver.Artboard.ARTBOARD_4);
        prismDriver.clearAllAnimations();
    }

    void redTeamCorrect() {
        prismDriver.loadAnimationsFromArtboard(GoBildaPrismDriver.Artboard.ARTBOARD_1);

    }
    void blueTeamCorrect() {
        prismDriver.loadAnimationsFromArtboard(GoBildaPrismDriver.Artboard.ARTBOARD_0);
    }
    void teamError() {
        prismDriver.loadAnimationsFromArtboard(GoBildaPrismDriver.Artboard.ARTBOARD_4);
    }
    void targetInSight() {
        prismDriver.loadAnimationsFromArtboard(GoBildaPrismDriver.Artboard.ARTBOARD_3);
    }
    public void processGamepad(Gamepad gamepad) {
        if (gamepad.squareWasPressed()) teamError();
        if (gamepad.circleWasPressed()) blueTeamCorrect();
        if (gamepad.crossWasPressed()) redTeamCorrect();
        if (gamepad.triangleWasPressed()) targetInSight();
    }


}
