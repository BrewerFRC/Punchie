/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * @author Team 4564
 * @author Samuel Woodward
 * @author Jamison Cohen
 * @author Cayen Philbrick
 * @author Thomas Blanchard
 * @author Swaroop Handral
 */ 

public class Drive extends DifferentialDrive {
    private static Drive instance;
    private static final Spark 
			frontL = new Spark(Constants.PWM_DRIVE_FL),
			frontR = new Spark(Constants.PWM_DRIVE_FR),
			backL = new Spark(Constants.PWM_DRIVE_BL),
			backR = new Spark(Constants.PWM_DRIVE_BR);
	private static final SpeedControllerGroup left = new SpeedControllerGroup(frontL, backL);
	private static final SpeedControllerGroup right = new SpeedControllerGroup(frontR, backR);
	
    private static final double ACCELRATE=0.04;
    private double currSpeed=0;

    public Drive() {
		super(left, right);
		
		instance = this;
    }
    
    
    private double accel(double targetSpeed, double currSpeed, double accelRate){
        if (currSpeed > targetSpeed) {
            return currSpeed - accelRate;
        } else if (currSpeed < targetSpeed) {
            return currSpeed + accelRate;
        } else {
            return targetSpeed;
        }
    }

    public void drive(double speed, double turn){
        currSpeed =accel(speed, currSpeed, ACCELRATE);
        arcadeDrive(currSpeed, -turn);
    }
   
}
