package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Drive", group = "Example")
public class drive extends LinearOpMode {

    private DcMotor leftMotor, rightMotor;

    @Override
    public void runOpMode() {
        // Initialize hardware for motors
        leftMotor = hardwareMap.get(DcMotor.class, "left_motor");
        rightMotor = hardwareMap.get(DcMotor.class, "right_motor");

        // Set motor directions if needed (e.g., if one side is reversed)
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addLine("Waiting for start...");
        telemetry.update();

        waitForStart();

        // Adjust the turn sensitivity
        final double TURN_SENSITIVITY = 0.5; // Reduce turning sensitivity

        while (opModeIsActive()) {
            // Read joystick inputs
            double forwardBackward = -gamepad1.left_stick_y; // Forward/Backward
            double turn = gamepad1.right_stick_x * TURN_SENSITIVITY; // Scale turning input

            // Calculate motor power
            double leftPower = forwardBackward + turn;
            double rightPower = forwardBackward - turn;

            // Apply power to motors
            leftMotor.setPower(leftPower);
            rightMotor.setPower(rightPower);

            // Add telemetry for motor powers
            telemetry.addData("Left Motor Power", leftPower);
            telemetry.addData("Right Motor Power", rightPower);
            telemetry.addData("Turn Sensitivity", TURN_SENSITIVITY);
            telemetry.update();

            sleep(50); // Small delay to avoid overwhelming the control loop
        }
    }
}
