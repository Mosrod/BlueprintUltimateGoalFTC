package org.blueprint.ftc.core.controllers;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.HashMap;
import java.util.concurrent.Callable;

//enum GamepadButton {
//    BUTTON_X, BUTTON_A, BUTTON_B, BUTTON_Y
//}

// Utility class for assigning a toggle gamepad button
public class GamepadButtonController {
    private boolean isOn;
    private boolean toggleChanged;

    private Callable<Boolean> gamepadButton;
    private Callable<Void> start;
    private Callable<Void> stop;


    public GamepadButtonController(Callable<Boolean> gamepadButton, Callable<Void> start, Callable<Void> stop) {
        this.gamepadButton = gamepadButton;
        this.start = start;
        this.stop = stop;
    }

    public void checkForButton() {
        try {
            if (gamepadButton.call() && !toggleChanged) {
                if (isOn) {
                    stop.call();
                    isOn = false;
                } else {
                    start.call();
                    isOn = true;
                }
                toggleChanged = true;
            } else if (!gamepadButton.call()){
                toggleChanged = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
