package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "DriveMode")
public class DriveMode extends LinearOpMode {

  private DcMotor rearleft;
  private DcMotor frontright;
  private DcMotor frontleft;
  private DcMotor rearright;

  @Override
  public void runOpMode() {
    double drivePower;
    double forward;
    float strafe;
    double turn;

    rearleft = hardwareMap.get(DcMotor.class, "rearleft");
    frontright = hardwareMap.get(DcMotor.class, "frontright");
    frontleft = hardwareMap.get(DcMotor.class, "frontleft");
    rearright = hardwareMap.get(DcMotor.class, "rearright");

    drivePower = 0.6;
    rearleft.setDirection(DcMotorSimple.Direction.REVERSE);
    frontright.setDirection(DcMotorSimple.Direction.REVERSE);
    
    waitForStart();
    if (opModeIsActive()) {
      while (opModeIsActive()) {
        forward = gamepad1.left_stick_y * 0.4;
        strafe = -gamepad1.right_stick_y;
        turn = gamepad1.left_stick_x * 0.55;
        frontleft.setPower(forward + strafe + turn);
        rearright.setPower((forward - strafe) + turn);
        rearleft.setPower((forward + strafe) - turn);
        frontright.setPower((forward - strafe) - turn);
        if (gamepad1.dpad_up) {
          if (drivePower < 1) {
            drivePower += 0.1;
          }
        } else if (gamepad1.dpad_down) {
          if (drivePower > 0.1) {
            drivePower += -0.1;
          }
        } else if (gamepad1.y) {
          drivePower = 1;
        } else if (gamepad1.a) {
          drivePower = 0.8;
        }
        telemetry.update();
        telemetry.addData("Drive Factor", drivePower * 100);
      }
    }
  }
}
