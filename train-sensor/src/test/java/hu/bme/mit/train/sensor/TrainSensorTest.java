package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {
    TrainController mockTrainController;
    TrainSensorImpl trainSensor;
    TrainUser mockTrainUser;

    @Before
    public void before() {
        mockTrainController = mock(TrainController.class);
        mockTrainUser = mock(TrainUser.class);
        trainSensor = new TrainSensorImpl(mockTrainController, mockTrainUser);
    }

    @Test
    public void underAbsoluteMarginTest() {
        trainSensor.overrideSpeedLimit(-1);
        verify(mockTrainUser, times(1)).setAlarmState(true);
    }

    @Test
    public void overAbsoluteMarginTest() {
        trainSensor.overrideSpeedLimit(550);
        verify(mockTrainUser, times(1)).setAlarmState(true);
    }

    @Test
    public void betweenAbsoluteMarginTest() {
        trainSensor.overrideSpeedLimit(300);
        verify(mockTrainUser, times(1)).setAlarmState(false);
    }

    @Test
    public void overRelativeMarginTest(){
        int speed = (int) (mockTrainController.getReferenceSpeed() * 0.5 - 50);
        trainSensor.overrideSpeedLimit(speed);
        verify(mockTrainUser, times(1)).setAlarmState(true);
    }
}
