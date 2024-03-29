package org.blueprint.ftc.core;

//import com.acmerobotics.dashboard.config.Config;

//@Config
public final class Constants {

    public static String IMU_CALIBRATION_FILE = "FTC_IMU_Calibration.json";

    //  Component names configured in RobotDriver?
    public static final String LEFT_FRONT_MOTOR_NAME = "left_front_motor" ;
    public static final String RIGHT_FRONT_MOTOR_NAME = "right_front_motor" ;
    public static final String LEFT_BACK_MOTOR_NAME = "left_back_motor" ;
    public static final String RIGHT_BACK_MOTOR_NAME = "right_back_motor" ;

    // Stone system
    public static final String STONE_ARM = "stone_arm" ;
    public static final String STONE_GRIPPER = "stone_gripper" ;

    // motor names for  intake system
    public static final String INTAKE_MOTOR = "intake_motor";

    //  IntakeSystem params/
    public static final String INTAKE_LEFT_SERVO = "intake_left_servo";
    public static final String INTAKE_RIGHT_SERVO = "intake_right_servo";
    public static final boolean INTAKE_LEFT_SERVO_REVERSE = false;
    public static final boolean INTAKE_RIGHT_SERVO_REVERSE = true;
    public static final double INTAKE_LEFT_SERVO_INIT_POWER = 0.19;  //  Was 0.22
    public static final double INTAKE_RIGHT_SERVO_INIT_POWER = 0.20; //  Was 0.23

    //  LinearSlide system
    public static final String LINEAR_SLIDE_MOTOR_NAME = "linear_slide_motor";
    public static final String LINEAR_SLIDE_SERVO = "linear_slide_servo";
    public static final String LINEAR_ARM_SERVO = "linear_arm_servo";

    public static final String TOUCH_SENSOR_NAME = "touch_sensor" ;
    public static final String COLOR_SENSOR_NAME = "colorSensor";

    public static final String IMU_NAME = "imu" ;

    //  FoundationSystem
    public static final String FOUNDATION_LEFT_SERVO = "foundation_left_servo";
    public static final String FOUNDATION_RIGHT_SERVO = "foundation_right_servo";

    //  For AndyMark Neverest 40 motor;  1 rev = 1120 ticks ;  Orbital 20:  537.6 ticks / revolution
    public static final int WHEEL_DIAMETER = 4; // inches ;
    public static final double MOTOR_TICK_COUNT = 537.6;   //  ticks per revoluion for Andymark Orbital 20;
    public static final double TICK_DIAMETER_RATIO = (Constants.MOTOR_TICK_COUNT / (Math.PI * Constants.WHEEL_DIAMETER));
    public static final double DRIVETRAIN_GEAR_RATIO = 45.0/35.0;  //  Ratio with modified gears;
    public static final double TICK_GEAR_RATIO = Constants.TICK_DIAMETER_RATIO / Constants.DRIVETRAIN_GEAR_RATIO ;

    //  strafe != forward distance.  Need this factor to match.
    public static double STRAFE_DISTANCE_FACTOR = 1.1424;

    //  For AndyMark Neverest 40 motor connected to linear slide system
    public static final int SIMPLE_MOTOR_TICK_COUNT = 1120;  //  For neverest classic 40
    public static final int SIMPLE_WHEEL_DIAMETER = 2; // inches ;
    public static final double SIMPLE_WHEEL_DISTANCE = Constants.SIMPLE_WHEEL_DIAMETER* Math.PI;  //  6.2857 inches;
    public static final double SIMPLE_TICK_DIAMETER_RATIO = SIMPLE_MOTOR_TICK_COUNT / SIMPLE_WHEEL_DISTANCE; // 178.1822 ticks / inches
    public static final double SIMPLE_WHEEL_MAX_DISTANCE = 22.5; // inches;
    public static final double SIMPLE_WHEEL_MAX_TICKS = SIMPLE_WHEEL_MAX_DISTANCE*SIMPLE_TICK_DIAMETER_RATIO; // inches;

    //  Color sensor;
    public static final int COLOR_ALPHA=0 ;
    public static final int COLOR_RED=1 ;
    public static final int COLOR_GREEN=2 ;
    public static final int COLOR_BLUE=3 ;

    //  Hue:  340 <  20; sat: 0.6 -->  RED
    //  Hue:  200 - 275; sat: 0.6 -->  Blue
    public static float TARGET_COLOR_BLUE_HUE_LOW = 165;
    public static float TARGET_COLOR_BLUE_HUE_HIGH = 265;

    public static float TARGET_COLOR_RED_HUE_LOW = 320;
    public static float TARGET_COLOR_RED_HUE_HIGH = 15;

    public static float TARGET_COLOR_SATURATION=0.6f ;

    // sometimes it helps to multiply the raw RGB values with a scale factor
    // to amplify/attentuate the measured values.
    public static double SCALE_FACTOR = 255;

    //======================================================================================
    /*  From MaxVelocityTest
    MotorName: NeveRest 20 Orbital Gearmotor
    TicksPerRev: 537.6
    achMaxRPMFrac: 0.85
    achMaxTPS: 2589.44

    Default Position Coeffs for Orbital 20:
      [PIDFCoefficients(p=10.000000 i=0.049988 d=0.000000 f=0.000000 alg=LegacyPID)]
    Default Velocity Coeffs for Orbital 20:
      [PIDFCoefficients(p=10.000000 i=3.000000 d=0.000000 f=0.000000 alg=LegacyPID)]

     */

    //  Will need to tune these for driving straight;  See MaxVelocityTest.java
    //  Set for velocity PID, same val for all motors;

    public static double PID_DRIVE_KP = 1.1538;
    public static double PID_DRIVE_KI = PID_DRIVE_KP/10;
    public static double PID_DRIVE_KD = 0;
    public static double PID_DRIVE_KF = PID_DRIVE_KP*10;

    public static int MOTOR_MAX_VELOCITY = 2820;  //  ticks per second;  Set from MaxVelocityTest;  was 2900
    public static int DEFAULT_VELOCITY = (int) (0.60*MOTOR_MAX_VELOCITY);  //  ticks per second;
    public static double TURN_SPEED = 0.45*Constants.MOTOR_MAX_VELOCITY;   //  Turning speed 987
    public static double STRAFE_SPEED = 0.25*Constants.MOTOR_MAX_VELOCITY;   //  Turning speed 987

    //======================================================================================

    //  set for Positional PID
    public static double POSITIONAL_DRIVE_KP = 1.7;

    //  Will need to tune these for turning;
    // Speed of wheels while slowing down
    public static double PID_ROTATE_KP = 0.035;       //  0.009

    // Do not need - once the robot reaches 90°, the angle does not change.
    public static double PID_ROTATE_KI = 0;           //  0.0000374

    // How much the robot slows down before it reaches 90°
    public static double PID_ROTATE_KD = 0.025;


    //  Skystone detection;
    public static final String CAMERA_NAME = "Webcam 1";
    public static final String VUFORIA_KEY =
            "ARBNGpP/////AAABmbLvzx0Qekiui2o+DSSa3YJIIuD7Q0UL6sLKYRh6/OCm/uvQLlRLPNs/o72itb3SXgG71435htgeXTLgMciuPUca8vG5BbLoR5k9K5L6pbe8XLD9VFAG4Llh55ETmOQzz+S7yyjN69HtY34ahSjsi4bzZzwrfeTrTsCPfa1ZTAdf6MxWbZ5yn6LKmanzxLbnmBiftmRbgVVtxeMbOdxPv/f2uxXWqnKEHz5/LDvoacDFVQwu07AnvUXk0cDRSKEObQs5lE+IjdxSbYMOHPYbJy9jWf+2tZURyVZF1atz0nHaW1yra8YXg0HYQvWDzkt9+2S831dsB25sJElDK4xLGFFb/GVCSFGfnjvRHgbvD1AP";

    // Since ImageTarget trackables use mm to specifiy their dimensions, we must use mm for all the physical dimension.
    public static final float MM_PER_INCHES = 25.4f;
    public static final String VISIBLE_TARGET_NAME = "Stone Target";
    public static final boolean CAMERA_MONITOR_ON = false;

    //  front facing robot on the red aliance side;  cartesian coordinates; right hand rule
    public static final float CAMERA_X = -90.0f;   //
    public static final float CAMERA_Y = 90.0f;   //
    public static final float CAMERA_Z = 0.0f;   //

    //  relative to robot origin;
    public static final float CAMERA_DX_MM = -6.375f * MM_PER_INCHES;   //  width; was +6.375
    public static final float CAMERA_DY_MM = 9.25f * MM_PER_INCHES;   //  front, includes intake system; was 8.875f
    public static final float CAMERA_DZ_MM = 4.5f * MM_PER_INCHES;    //  height; was 4.75

    public static final double HEADING_THRESHOLD = 1 ;      // As tight as we can make it with an integer gyro
    public static final double P_TURN_COEFF = 0.1;     // Larger is more responsive, but also less stable
    public static final double P_DRIVE_COEFF = 0.15;     // Larger is more responsive, but also less stable

    public static final float TILE_SIZE = 24.0f;  //  24 inches square according to doc.

    //  DriveTrain size;  in forward direction from red alliance side
    public static final float DRIVETRAIN_LENGTH = 17.75f;   //  dy
    public static final float DRIVETRAIN_WIDTH = 16.75f;    //  dx
    public static final float DRIVETRAIN_HEIGHT = 4.0f;     //  dz, includes wheels

    public static final float DRIVETRAIN_LENGTH_MM = DRIVETRAIN_LENGTH * MM_PER_INCHES;   //  dy in mm;
    public static final float DRIVETRAIN_WIDTH_MM = DRIVETRAIN_WIDTH*MM_PER_INCHES;    //  width in mm;
    public static final float DRIVETRAIN_HEIGHT_MM = DRIVETRAIN_HEIGHT*MM_PER_INCHES;     //  dz, includes wheels, mm

    public static final float FIELD_WIDTH = 144;
    public static final float FIELD_WIDTH_MM = FIELD_WIDTH*MM_PER_INCHES;

    public static final float DRIVETRAIN_SECOND_QUADRANT_CENTER = Constants.TILE_SIZE + ((Constants.TILE_SIZE - Constants.DRIVETRAIN_LENGTH) / 2);

    //  Tune this for gamepad.
    public static float DEADZONE = 0.30f;

    // Robot & Tile Dimensions
    public static final float TILE_LENGTH = 24.0f;

    //  For joystick sensitivity;
    public static final int SENSITIVITY_LEVEL = 3;
}
