package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.blueprint.ftc.core.AbstractLinearOpMode;
import org.blueprint.ftc.core.GamepadDriver;
import org.blueprint.ftc.core.IntakeSystem;
import org.blueprint.ftc.core.LiftSystem;
import org.blueprint.ftc.core.OuttakeSystem;
import org.blueprint.ftc.core.controllers.GamepadButtonController;
import org.blueprint.ftc.core.controllers.IMUController;
import org.blueprint.ftc.core.controllers.ServoController;

import java.util.concurrent.Callable;


/**
 * Gamepad1 Controls:
 * <p>
 * left_stick_y:   drive forward / reverse
 * left_stick_x:   strafe left / right
 * right_stick_x:  turn left / right
 * X:  Put robot in forward, regular drive mode
 * B:  Put robot in reverse drive mode
 * left_trigger:  Foundation system up
 * right_trigger:  Foundation system down
 * left_bumper:  Intake system reverse
 * right_bumper:  Intake system forward
 * A:  Intake system off
 * <p>
 * <p>
 * Gamepad2 Controls:
 * <p>
 * left_stick_y:   Drive lift system up and down
 * right_stick_y:  Drive lift system arm up and down
 * Y:  Lift system automode to pickup block from storage
 * A:  Lift system automode to go back to base
 * dpad_left:   Open lift system gripper
 * dpad_right:  Close lift system gripper
 * <p>
 * Multi-threading based on this example:
 * https://stemrobotics.cs.pdx.edu/node/5184
 */

@TeleOp(name = "CyclopentaneTele")
//  @Disabled
public class CyclopentaneTeleOp extends AbstractLinearOpMode {

//    private MotorControllerEx motor;
    private IMUController imu;

//    private Driver driver;
//    private FoundationSystem foundationSystem;
//    private IntakeSystem intakeSystem;
//    private LiftSstem liftSystem;
    private GamepadDriver gpd;
    private IntakeSystem intake;
    private OuttakeSystem outtake;
    private LiftSystem liftSystem;
    private ServoController gripper;


    // Intake System Gamepad Button Coontroller
    private GamepadButtonController intakeSystemButton = new GamepadButtonController(new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return gamepad1.x;
        }
    }, new Callable<Void>() {
        @Override
        public Void call() throws Exception {
            intake.start();
            return null;
        }
    }, new Callable<Void>() {
        @Override
        public Void call() throws Exception {
            intake.stop();
            return null;
        }
    });

    // Outtake System Gamepad Button Controller
    private GamepadButtonController outtakeSystemButton = new GamepadButtonController(new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return gamepad1.a;
        }
    }, new Callable<Void>() {
        @Override
        public Void call() throws Exception {
            outtake.start();
            return null;
        }
    }, new Callable<Void>() {
        @Override
        public Void call() throws Exception {
            outtake.stop();
            return null;
        }
    });

    // Lift System Gamepad Button Controller
    private GamepadButtonController liftSystemButton = new GamepadButtonController(new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return gamepad1.b;
        }
    }, new Callable<Void>() {
        @Override
        public Void call() throws Exception {
//            liftSystem.lift(7.8);
//            liftSystem.drive(0.1f);

//            liftSystem.tiltPos(0.57);
//            liftSystem.lift(2);
            liftSystem.tiltPos(0.70);
            liftSystem.lift(8.5);
            liftSystem.drive(0.1f);
            liftSystem.tiltPos(0.75);
            return null;
        }
    }, new Callable<Void>() {
        @Override
        public Void call() throws Exception {
//            liftSystem.backToBase(true);
            liftSystem.lift(2);
            liftSystem.tiltPos(0.57);
            liftSystem.lift(0);
            return null;
        }
    });

    // Lift System Gamepad Button Controller
    private GamepadButtonController gripperButton = new GamepadButtonController(new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return gamepad1.b;
        }
    }, new Callable<Void>() {
        @Override
        public Void call() throws Exception {
            gripper.openGripper(true);
            return null;
        }
    }, new Callable<Void>() {
        @Override
        public Void call() throws Exception {
            gripper.closeGripper(true);
            return null;
        }
    });

    @Override
    public void initOpMode() throws InterruptedException {

        telemetry.addData("WAIT FOR INITIALIZATION. OR ELSE...:  "," DON'T PRESS PLAY!");
        telemetry.update();

        this.initRosie();

        this.imu = this.rosie.getIMUController();
        this.imu.resetAngle();

        this.liftSystem = this.rosie.getLiftSystem();
        this.liftSystem.setLinearOpMode(this);

        this.gpd = this.rosie.getGamepadDriver();

        this.intake = this.rosie.getIntakeSystem();

        this.outtake = this.rosie.getOuttakeSystem();

        this.gripper = new ServoController(this.hardwareMap, "gripper");

        telemetry.addData("INITIALIZATION DONE. OR IS IT?...","  PRESS PLAY WHEN READY.");
        telemetry.update();
    }

    //  Post start init logic;  robot can only expand after start of game or after press start
    public void postStartSetup() throws InterruptedException {
        //  Change driving direction
        this.gpd.putInReverse(gamepad1.b);
        this.gpd.putInDrive(gamepad1.x);
    }

    @Override
    public void stopOpMode() {

        this.stop();

        this.stopDriving();
        this.intake.stop();

        this.outtake.stop();



        this.liftSystem.reset();
//        this.foundationSystem.triggerUp(true);
//        this.liftSystem.moveBackSlide();
    }

    private void addGamepadTelemetry() {
        telemetry.addData("Gamepad1.LeftStickY", gamepad1.left_stick_y);
        telemetry.addData("Gamepad1.LeftStickX", gamepad1.left_stick_x);
        telemetry.addData("Gamepad1.RightStickX", gamepad1.right_stick_x);

        telemetry.addData("Gamepad Values: ", "see below");


        //  telemetry.addData("Gamepad1.LeftTrigger", gamepad1.left_trigger);
        //  telemetry.addData("Gamepad1.RightTrigger", gamepad1.right_trigger);
        //  telemetry.addData("Gamepad1.LeftBumper", gamepad1.left_bumper);
        //  telemetry.addData("Gamepad1.RightBumper", gamepad1.right_bumper);

        telemetry.addData("Gamepad2.LeftStickY", gamepad2.left_stick_y);
        telemetry.addData("Gamepad2.RightStickY", gamepad2.right_stick_y);
        //  telemetry.addData("Gamepad2.Y", gamepad2.y);
        //  telemetry.addData("Gamepad2.A", gamepad2.a);
        //  telemetry.addData("Gamepad2.DpadLeft", gamepad2.dpad_left);
        //  telemetry.addData("Gamepad2.DpadRight", gamepad2.dpad_right);
    }

    @Override
    public void runOpMode() throws InterruptedException {

        this.initOpMode();

        this.waitToPressStart();

//        g1.start();
//        g2.start();

        this.postStartSetup();

        while (opModeIsActive()) {


//            this.addGamepadTelemetry();

            // set up Mecanum drive
            this.gpd.drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

            intakeSystemButton.checkForButton();
            outtakeSystemButton.checkForButton();
            liftSystemButton.checkForButton();
            gripperButton.checkForButton();

//            this.liftSystem.drive(0.1f);


            telemetry.update();
        }

        //  Stop thread;
//        g1.interrupt();
//        g2.interrupt();

        this.stopOpMode();
    }
}
