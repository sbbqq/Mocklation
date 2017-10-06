package de.p72b.mocklation.service;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

import de.p72b.mocklation.dagger.MocklationApp;
import de.p72b.mocklation.service.location.LocationService;
import de.p72b.mocklation.service.permission.PermissionService;
import de.p72b.mocklation.service.setting.ISetting;
import de.p72b.mocklation.service.setting.Setting;

public final class AppServices {

    public static final String WEB_SERVICE = "WEB_SERVICE";
    public static final String SETTINGS = "SETTINGS";
    public static final String PERMISSIONS = "PERMISSIONS";
    public static final String LOCATION = "LOCATION";

    private final static Map<String, Object> SERVICES = new HashMap<>();

    static {
        ISetting settings = new Setting(MocklationApp.getInstance());
        SERVICES.put(SETTINGS, settings);
        SERVICES.put(PERMISSIONS, new PermissionService(settings));
        SERVICES.put(LOCATION, new LocationService());
    }

    public AppServices() {
        // do nothing hide this
    }

    public static Object getService(@Service String service) {
        return SERVICES.get(service);
    }

    @StringDef({WEB_SERVICE, SETTINGS, PERMISSIONS, LOCATION})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Service {
    }
}