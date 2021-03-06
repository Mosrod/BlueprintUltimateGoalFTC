package org.blueprint.ftc.core;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.blueprint.ftc.core.controllers.ColorSensorController;
import org.blueprint.ftc.core.controllers.MecanumDriveController;
import org.blueprint.ftc.core.controllers.IMUController;

/**
 * Rosie
 */
public class GameBot {

    /* local OpMode members. */
    //  private HardwareMap hardwareMap;

    private ColorSensorController colorSensor;
    private IMUController imu;
    private MecanumDriveController driver;

    private GamepadDriver gamepadDriver;

    private IntakeSystem intakeSystem;
    private OuttakeSystem outtakeSystem;

//    private SkystoneDetector skystonDetector;
//    private IntakeSystem intakeSystem;
    private LiftSystem liftSystem;
//    private FoundationSystem foundationSystem;
//    private StoneSystem stoneSystem;

    private ElapsedTime period = new ElapsedTime();

    /* Constructor */
    public GameBot() {

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap hardwareMap) throws InterruptedException {
        // Save reference to Hardware map
        // this.hardwareMap = hardwareMap;

        //  IMU ;  DON'T SET MODE.
        this.imu = new IMUController(hardwareMap);

        //  Build driver ;
        this.driver = new MecanumDriveController(hardwareMap);

        //  Drive using gamepad ;
        this.gamepadDriver = new GamepadDriver(this.driver, this.imu);

        this.intakeSystem = new IntakeSystem(hardwareMap);

        this.outtakeSystem = new OuttakeSystem(hardwareMap);

//        this.colorSensor = new ColorSensorController(hardwareMap);
//        this.colorSensor.ledOn();
//
//        this.skystonDetector = new SkystoneDetector(hardwareMap);
//
//        this.foundationSystem = new FoundationSystem(hardwareMap);
//
        this.liftSystem = new LiftSystem(hardwareMap);
//
//        this.intakeSystem = new IntakeSystem(hardwareMap);
//
//        this.stoneSystem = new StoneSystem(hardwareMap);
    }

    public MotorControllerEx getMotorPID() {

        //  MotorPID cannot be a singleton;  Otherwise internal state is maintained and prevents multiple turns;
        MotorControllerEx motorPID = new MotorControllerEx();
        return motorPID;
    }

    public ColorSensorController getColorSensorController() {
        return this.colorSensor;
    }

    public IMUController getIMUController() {
        return this.imu;
    }

    public MecanumDriveController getDriver() {
        return this.driver;
    }

    public IntakeSystem getIntakeSystem() { return this.intakeSystem; }

    public OuttakeSystem getOuttakeSystem() {
        return this.outtakeSystem;
    }

    //    public SkystoneDetector getSkystoneDetector() {
//        //  Skystone detector, activate when ready ;
//        return this.skystonDetector;
//    }

    public GamepadDriver getGamepadDriver() {
        return this.gamepadDriver;
    }

//    public FoundationSystem getFoundationSystem() {
//        return this.foundationSystem;
//    }

    public LiftSystem getLiftSystem() {
        return this.liftSystem;
    }
//
//    public IntakeSystem getIntakeSystem() {
//        return this.intakeSystem;
//    }
//
//    public StoneSystem getStoneSystem() {
//        return this.stoneSystem;
//    }

}

