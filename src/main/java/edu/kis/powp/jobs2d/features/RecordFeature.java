package edu.kis.powp.jobs2d.features;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.recorder.CommandRecorder;
import edu.kis.powp.jobs2d.drivers.SelectDriverMenuOptionListener;
import edu.kis.powp.jobs2d.events.SelectClearPanelOptionListener;
import edu.kis.powp.jobs2d.events.SelectStartRecordingOptionListener;
import edu.kis.powp.jobs2d.events.SelectStopRecordingOptionListener;
import edu.kis.powp.jobs2d.events.SelectClearRecordingOptionListener;



public class RecordFeature {

    private static Application app;
    private static CommandRecorder commandRecorder;

    private static boolean isRecording = false;

    /**
     * Setup jobs2d drivers Plugin and add to application.
     *
     * @param application Application context.
     */
    public static void setupRecorderPlugin(Application application) {
        commandRecorder = new CommandRecorder();
        SelectStartRecordingOptionListener selectStartRecordingOptionListener = new SelectStartRecordingOptionListener();
        SelectStopRecordingOptionListener selectStopRecordingOptionListener = new SelectStopRecordingOptionListener();
        SelectClearRecordingOptionListener selectClearRecordingOptionListener = new SelectClearRecordingOptionListener();


        app = application;
        app.addComponentMenu(edu.kis.powp.jobs2d.features.RecordFeature.class, "Recorder");
        app.addComponentMenuElement(edu.kis.powp.jobs2d.features.RecordFeature.class, "Start Recording", selectStartRecordingOptionListener);
        app.addComponentMenuElement(edu.kis.powp.jobs2d.features.RecordFeature.class, "Stop Recording", selectStopRecordingOptionListener);
        app.addComponentMenuElement(edu.kis.powp.jobs2d.features.RecordFeature.class, "Clear Recording", selectClearRecordingOptionListener);

    }

    public static void recordCommand(DriverCommand command){
        if(isRecording){
            commandRecorder.addCommand(command);
        }
    }

    public static void setRecording(){
        isRecording = true;
    }

    public static void stopRecording(){
        isRecording = false;
    }

    public static void clearRecording(){
        commandRecorder.clearCommand();
    }

}


        /*
        public static void addDriver(String name, Job2dDriver driver) {
            SelectDriverMenuOptionListener listener = new SelectDriverMenuOptionListener(driver, driverManager);
            app.addComponentMenuElement(edu.kis.powp.jobs2d.features.DriverFeature.class, name, listener);
        }

        /**
         * Update driver info.
         */
        /*
        public static void updateDriverInfo() {
            app.updateInfo(driverManager.getCurrentDriver().toString());
        }
        */

