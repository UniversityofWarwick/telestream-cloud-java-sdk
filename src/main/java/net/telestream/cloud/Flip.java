package net.telestream.cloud;

import net.telestream.objects.Factory;
import net.telestream.services.FactoryService;

/**
 * Created by maciejwitowski on 1/18/16.
 */
public class Flip implements Resource<Factory> {

    private final FactoryService factoryService;

    public Flip(TelestreamCloudCredentials credentials) {
        this.factoryService = new FactoryService(credentials);
    }

    @Override
    public FactoryService factoryService() {
        return factoryService;
    }
}
