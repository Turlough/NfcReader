package ie.turloughcowman.androidnfc;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by New User on 26/03/2017.
 */

public class NfcApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Stetho is only used for demonstation- it is not required for NFC
        initializeStetho();
    }

    /**
     * Not required for reading NFC. http://facebook.github.io/stetho/
     */
    protected void initializeStetho() {

        // Create an InitializerBuilder
        Stetho.InitializerBuilder initializerBuilder = Stetho.newInitializerBuilder(this);

        // Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this));

        // Enable command line interface
        initializerBuilder.enableDumpapp(Stetho.defaultDumperPluginsProvider(this));

        // Use the InitializerBuilder to generate an Initializer
        Stetho.Initializer initializer = initializerBuilder.build();

        // Initialize Stetho with the Initializer
        Stetho.initialize(initializer);
    }
}
