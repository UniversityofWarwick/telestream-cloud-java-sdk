import cloud.Flip;
import cloud.Resource;
import cloud.TelestreamCloudCredentials;
import cloud.TelestreamCloudRequest;
import services.EncodingService;
import services.ProfileService;
import services.VideoService;
import objects.Encoding;
import objects.Profile;
import objects.Video;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by maciejwitowski on 1/16/16.
 */
public class TelestreamCloud {

    private final TelestreamCloudCredentials credentials;

    public TelestreamCloud(String access_key, String access_secret) {
        this.credentials = new TelestreamCloudCredentials(access_key, access_secret);
    }

    public void setApiHost(String apiHost) {
        this.credentials.setApiHost(apiHost);
    }

    public void setApiPort(int apiPort) {
        this.credentials.setApiPort(apiPort);
    }

    public void setApiVersion(String apiVersion) {
        this.credentials.setApiVersion(apiVersion);
    }

    public String signedParams(String httpMethod, String path) throws IOException {
        return signedParams(httpMethod, path, null);
    }

    public String signedParams(String httpMethod, String path, Map<String, Object> params) throws IOException {
        return new TelestreamCloudRequest.Builder(credentials)
                .httpMethod(httpMethod)
                .apiPath(path)
                .data(params)
                .build()
                .send();
    }

    public enum ResourceType {
        FLIP
    }

    public Resource getResource(ResourceType resourceType) {
        switch (resourceType) {
            case FLIP:
                return new Flip(credentials);
        }

        return null;
    }

    public List<Video> getVideos(String factoryId) throws IOException {
        return VideoService.forFactory(credentials, factoryId).all();
    }

    public Video getVideo(String factoryId, String videoId) throws IOException {
        return VideoService.forFactory(credentials, factoryId).find(videoId);
    }

    public List<Profile> getProfiles(String factoryId) throws IOException {
        return ProfileService.forFactory(credentials, factoryId).all();
    }

    public Profile getProfile(String factoryId, String profileId) throws IOException {
        return ProfileService.forFactory(credentials, factoryId).find(profileId);
    }

    public List<Encoding> getEncodings(String factoryId) throws IOException {
        return EncodingService.forFactory(credentials, factoryId).all();
    }

    public Encoding getEncoding(String factoryId, String encodingId) throws IOException {
        return EncodingService.forFactory(credentials, factoryId).find(encodingId);
    }

    public Encoding createEncoding(String factoryId, Map<String, Object> params) throws IOException {
        return EncodingService.forFactory(credentials, factoryId).create(params);
    }

    public Profile createProfile(String factoryId, Map<String, Object> params) throws IOException {
        return ProfileService.forFactory(credentials, factoryId).create(params);
    }

    public Video createVideo(String factoryId, Map<String, Object> params) throws IOException {
        return VideoService.forFactory(credentials, factoryId).create(params);
    }

    public String deleteProfile(String factoryId, String profileId) throws IOException {
        return ProfileService.forFactory(credentials, factoryId).delete(profileId);
    }

    public String deleteVideo(String factoryId, String videoId) throws IOException {
        return VideoService.forFactory(credentials, factoryId).delete(videoId);
    }

    public String deleteEncoding(String factoryId, String encodingId) throws IOException {
        return EncodingService.forFactory(credentials, factoryId).delete(encodingId);
    }
}