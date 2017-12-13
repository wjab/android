package testappsample.curso.com.testappsample;

import android.content.Context;

/**
 * Created by Administrador on 13/12/2017.
 */

public class AnotherClass {

    private static AnotherClass anotherClass;

    private Context context;

    private AnotherClass(Context context)
    {
        this.context = context;
    }

    public static AnotherClass getAnotherClassInstance(Context context)
    {
        if (anotherClass == null)
        {
            anotherClass = new AnotherClass(context);
        }

        return anotherClass;
    }
}
