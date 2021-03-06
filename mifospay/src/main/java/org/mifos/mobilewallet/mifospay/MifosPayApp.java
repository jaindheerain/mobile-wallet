package org.mifos.mobilewallet.mifospay;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import org.mifos.mobilewallet.mifospay.injection.component.ApplicationComponent;
import org.mifos.mobilewallet.mifospay.injection.component.DaggerApplicationComponent;
import org.mifos.mobilewallet.mifospay.injection.module.ApplicationModule;

import butterknife.ButterKnife;

/**
 * Created by naman on 17/8/17.
 */

public class MifosPayApp extends Application {

    ApplicationComponent applicationComponent;

    private static MifosPayApp instance;

    public static MifosPayApp get(Context context) {
        return (MifosPayApp) context.getApplicationContext();
    }

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static Context getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (MifosPayApp.instance == null) {
            MifosPayApp.instance = this;
        }
        ButterKnife.setDebug(true);
    }

    public ApplicationComponent component() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return applicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }
}

