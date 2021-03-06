package net.telestream.services;

import net.telestream.cloud.TelestreamCloudCredentials;
import net.telestream.cloud.TelestreamCloudRequest;
import com.google.gson.reflect.TypeToken;
import net.telestream.objects.Notifications;
import net.telestream.utils.GsonHelper;

import java.lang.reflect.Type;

/**
 * Created by maciejwitowski on 1/25/16.
 */
public class NotificationsService {
    private static final String PATH = "/notifications";

    public static final Type TYPE_NOTIFICATION = new TypeToken<Notifications>(){}.getType();

    protected final TelestreamCloudCredentials credentials;
    private String factoryId;

    private NotificationsService(TelestreamCloudCredentials credentials) {
        this.credentials = credentials;
    }

    public static NotificationsService forFactory(TelestreamCloudCredentials credentials, String factoryId) {
        NotificationsService service = new NotificationsService(credentials);
        service.factoryId = factoryId;
        return service;
    }

    public Notifications get() {
        TelestreamCloudRequest request = new TelestreamCloudRequest.Builder(credentials)
                .get()
                .apiPath(PATH)
                .factoryId(factoryId)
                .build();

        String response = request.send();

        return GsonHelper.get().fromJson(response, TYPE_NOTIFICATION);
    }

    public Notifications save(Notifications notifications) {
        TelestreamCloudRequest request = new TelestreamCloudRequest.Builder(credentials)
                .put()
                .apiPath(PATH)
                .data(notifications.getAttributesMap())
                .factoryId(factoryId)
                .build();

        return GsonHelper.get().fromJson(request.send(), TYPE_NOTIFICATION);
    }
}
