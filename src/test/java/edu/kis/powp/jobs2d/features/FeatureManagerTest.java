package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.features.FeatureManager;
import edu.kis.powp.jobs2d.features.FeatureObject;
import edu.kis.powp.appbase.*;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FeatureManagerTest {

    private static class MockFeatureObject extends FeatureObject {
        private boolean isSetupCalled = false;

        public MockFeatureObject(boolean isSetupCalled) {
            this.isSetupCalled = isSetupCalled;
        }

        @Override
        public void setup() {
            isSetupCalled = true;
        }

        public boolean isSetupCalled() {
            return isSetupCalled;
        }
    }

    @Test
    public void testAddSingleFeature() {
        FeatureManager.add(MockFeatureObject.class);
        List<Class<? extends FeatureObject>> featureList = FeatureManager.getList();

        assertEquals(MockFeatureObject.class, featureList.get(0));
    }

    @Test
    public void testAddMultipleFeatures() {
        List<Class<? extends FeatureObject>> featureClasses = new ArrayList<>();
        featureClasses.add(MockFeatureObject.class);
        featureClasses.add(MockFeatureObject.class);
        featureClasses.add(MockFeatureObject.class);

        FeatureManager.add(featureClasses);
        List<Class<? extends FeatureObject>> featureList = FeatureManager.getList();

        assertEquals(3, featureList.size());
        assertEquals(MockFeatureObject.class, featureList.get(0));
        assertEquals(MockFeatureObject.class, featureList.get(1));
        assertEquals(MockFeatureObject.class, featureList.get(2));
    }

    @Test
    public void testGetFeatureByIndex() {
        FeatureManager.add(MockFeatureObject.class);
        FeatureManager.add(MockFeatureObject.class);
        FeatureManager.add(MockFeatureObject.class);

        Class<? extends FeatureObject> featureClass = FeatureManager.get(1);
        assertEquals(MockFeatureObject.class, featureClass);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetFeatureByInvalidIndex() {
        int invalidIndex = 7;

        FeatureManager.get(invalidIndex);
    }

    private Application mockApplication;

    @Before
    public void setUp() {
        mockApplication = mock(Application.class);
    }
    @Test
    public void testSetupInvokesSetupMethodOnFeatureInstances() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // Arrange
        FeatureObject mockFeatureObject1 = mock(FeatureObject.class);
        FeatureObject mockFeatureObject2 = mock(FeatureObject.class);
        List<Class<? extends FeatureObject>> featureList = new ArrayList<>();
        featureList.add(mockFeatureObject1.getClass());
        featureList.add(mockFeatureObject2.getClass());

        FeatureManager.add(featureList);

        try {
            FeatureManager.setup(mockApplication);
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }




}