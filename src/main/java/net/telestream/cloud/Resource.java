package net.telestream.cloud;

import net.telestream.services.ApiService;

/**
 * Created by maciejwitowski on 1/23/16.
 */
public interface Resource<T> {
    ApiService<T> factoryService();
}