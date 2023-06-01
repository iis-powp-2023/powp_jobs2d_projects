package edu.kis.powp.jobs2d;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindow;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindowCommandChangeObserver;
import edu.kis.powp.jobs2d.command.gui.UsageManagerWindow;
import edu.kis.powp.jobs2d.command.manager.LoggerDistanceObserver;
import edu.kis.powp.jobs2d.drivers.PositionLoggingDriver;
import edu.kis.powp.jobs2d.drivers.MouseDrawerListener;
import edu.kis.powp.jobs2d.drivers.DriverComposite;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.decorator.DistanceCountingDriver;
import edu.kis.powp.jobs2d.drivers.decorator.TransformationDriver;
import edu.kis.powp.jobs2d.events.*;
import edu.kis.powp.jobs2d.features.*;
import edu.kis.powp.jobs2d.transformations.TransformationFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestJobs2dApp {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Setup test concerning preset figures in context.
     * 
     * @param application Application context.
     */
    private static void setupPresetTests(Application application) {
        SelectTestFigureOptionListener selectTestFigureOptionListener = new SelectTestFigureOptionListener(
                DriverFeature.getDriverManager());
        SelectTestFigure2OptionListener selectTestFigure2OptionListener = new SelectTestFigure2OptionListener(
                DriverFeature.getDriverManager());

        application.addTest("Figure Joe 1", selectTestFigureOptionListener);
        application.addTest("Figure Joe 2", selectTestFigure2OptionListener);
    }

    /**
     * Setup test using driver commands in context.
     * 
     * @param application Application context.
     */
    private static void setupCommandTests(Application application) {
        application.addTest("Load secret command", new SelectLoadSecretCommandOptionListener());
        application.addTest("Load recorded command", new SelectLoadRecordedCommandOptionListener());

        application.addTest("Run command", new SelectRunCurrentCommandOptionListener(DriverFeature.getDriverManager()));

        application.addTest("Visitor Test", new SelectVisitorTestOptionListener());

        application.addTest("Load immutable complex command test", new SelectTestImmutableComplexCommand(DriverFeature.getDriverManager()));

    }

    /**
     * Setup driver manager, and set default Job2dDriver for application.
     * 
     * @param application Application context.
     */
    private static void setupDrivers(Application application) {
        DriverComposite composite = new DriverComposite();

        Job2dDriver loggerDriver = new PositionLoggingDriver();
        DriverFeature.addDriver("Logger driver", loggerDriver);
        composite.addDriver(loggerDriver);

        DeviceUsageManager deviceUsageManager;

        DrawPanelController drawerController = DrawerFeature.getDrawerController();
        DistanceCountingDriver driver = new DistanceCountingDriver(new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic"));
        deviceUsageManager = driver.getDeviceUsageManager();
        deviceUsageManager.getDistanceChangePublisher().addSubscriber(new LoggerDistanceObserver(deviceUsageManager));
        DriverFeature.addDriver("Line Simulator + distance log", driver);
        DriverFeature.getDriverManager().setCurrentDriver(driver);
        composite.addDriver(driver);

        driver = new DistanceCountingDriver(new LineDriverAdapter(drawerController, LineFactory.getSpecialLine(), "special"));

        deviceUsageManager = driver.getDeviceUsageManager();
        deviceUsageManager.getDistanceChangePublisher().addSubscriber(new LoggerDistanceObserver(deviceUsageManager));
        DriverFeature.addDriver("Special line Simulator + distance log", driver);
        DriverFeature.addDriver("Logger + line driver + distance log", composite);

        Job2dDriver verticalFlipDriver = new TransformationDriver(new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic"), TransformationFactory.getHorizontalFlip());
        DriverFeature.addDriver("Vertical flip driver", verticalFlipDriver);

        Job2dDriver horizontalFlipDriver = new TransformationDriver(new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic"), TransformationFactory.getVerticalFlip());
        DriverFeature.addDriver("Horizontal flip driver", horizontalFlipDriver);

        Job2dDriver halfScaleDriver = new TransformationDriver(new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic"), TransformationFactory.getHalfScale());
        DriverFeature.addDriver("Half scale driver", halfScaleDriver);

        Job2dDriver doubleScaleDriver = new TransformationDriver(new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic"), TransformationFactory.getDoubleScale());
        DriverFeature.addDriver("Double scale driver", doubleScaleDriver);

        Job2dDriver clockwiseRotationDriver = new TransformationDriver(new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic"), TransformationFactory.getClockwiseRotation());
        DriverFeature.addDriver("Clockwise rotation driver", clockwiseRotationDriver);

        Job2dDriver counterClockwiseRotationDriver = new TransformationDriver(new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic"), TransformationFactory.getCounterclockwiseRotation());
        DriverFeature.addDriver("Counterclockwise rotation Driver", counterClockwiseRotationDriver);

        DriverFeature.updateDriverInfo();
    }

    private static void setupWindows(Application application) {

        CommandManagerWindow commandManager = new CommandManagerWindow(CommandsFeature.getDriverCommandManager());
        application.addWindowComponent("Command Manager", commandManager);

        UsageManagerWindow usageManager = new UsageManagerWindow();
        application.addWindowComponent("Usage Manager", usageManager);

        CommandManagerWindowCommandChangeObserver windowObserver = new CommandManagerWindowCommandChangeObserver(
                commandManager);
        CommandsFeature.getDriverCommandManager().getChangePublisher().addSubscriber(windowObserver);
    }

    /**
     * Setup menu for adjusting logging settings.
     * 
     * @param application Application context.
     */
    private static void setupLogger(Application application) {

        application.addComponentMenu(Logger.class, "Logger", 0);
        application.addComponentMenuElement(Logger.class, "Clear log",
                (ActionEvent e) -> application.flushLoggerOutput());
        application.addComponentMenuElement(Logger.class, "Fine level", (ActionEvent e) -> logger.setLevel(Level.FINE));
        application.addComponentMenuElement(Logger.class, "Info level", (ActionEvent e) -> logger.setLevel(Level.INFO));
        application.addComponentMenuElement(Logger.class, "Warning level",
                (ActionEvent e) -> logger.setLevel(Level.WARNING));
        application.addComponentMenuElement(Logger.class, "Severe level",
                (ActionEvent e) -> logger.setLevel(Level.SEVERE));
        application.addComponentMenuElement(Logger.class, "OFF logging", (ActionEvent e) -> logger.setLevel(Level.OFF));
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Application app = new Application("Jobs 2D");
                DrawerFeature.setupDrawerPlugin(app);
                CommandsFeature.setupCommandManager();

                DriverFeature.setupDriverPlugin(app);
                RecordFeature.setupRecorderPlugin(app);
                setupDrivers(app);
                setupPresetTests(app);
                setupCommandTests(app);
                setupLogger(app);
                setupWindows(app);
                app.setVisibility(true);
                app.getFreePanel().addMouseListener(
                        new MouseDrawerListener(DriverFeature.getDriverManager(),
                                app.getFreePanel().getWidth(),
                                app.getFreePanel().getHeight())
                );
            }
        });
    }

}
