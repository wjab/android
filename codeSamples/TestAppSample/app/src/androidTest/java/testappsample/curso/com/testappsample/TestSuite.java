package testappsample.curso.com.testappsample;

import android.test.suitebuilder.TestSuiteBuilder;

import junit.framework.Test;

/**
 * Created by Administrador on 6/12/2017.
 */

public class TestSuite {
    public static Test suite()
    {
        return new TestSuiteBuilder(TestSuite.class).includeAllPackagesUnderHere().build();
    }

    public TestSuite()
    {
        super();
    }
}
