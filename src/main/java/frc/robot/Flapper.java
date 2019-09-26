/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * @author Team 4564
 * @author Samuel Woodward
 */
public class Flapper {
    private Spark motor = new Spark(Constants.PWM_FLAPPER_FLAP);
    private DigitalInput flapperSwitch = new DigitalInput(Constants.DIO_FLAPPER_SWITCH);
    private static final double FLAP_SPEED = 1.0;
    
    public enum FlapperStates {
        RUNNING,
        EJECTING,
        STOPPED

    }

    private FlapperStates state = FlapperStates.STOPPED;

    public void start() {
        state = FlapperStates.RUNNING;
    }
    public void eject() {
        state = FlapperStates.EJECTING;
    }
    public void stop() {
        state = FlapperStates.STOPPED;
    }
    public boolean ejecting(){
        return state == FlapperStates.EJECTING;
    }

    public void update() {
        if (state == FlapperStates.RUNNING) {
            motor.set(FLAP_SPEED);
        } else if (state == FlapperStates.EJECTING){
            if (flapperSwitch.get()==false) {  //switch is pressed when false
               state=FlapperStates.STOPPED;
            } else {
                motor.set(FLAP_SPEED);
            }
        } else {
            motor.set(0);
        } 

    }

}
