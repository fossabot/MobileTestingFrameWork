package MdfService;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * This class creates a MDF Service object.
 */
public class MDFService {

    private String mdfUrl;
    private String authToken;

    public MDFService() {
    }

    public MDFService(String mdfUrl, String authToken) throws MalformedURLException, URISyntaxException {
        this.mdfUrl = new URL(mdfUrl).toURI().resolve("/api/v1/").toString();
        this.authToken = authToken;
    }

    public String getMdfUrl() {
        return mdfUrl;
    }

    public void setMdfUrl(String mdfUrl) throws MalformedURLException, URISyntaxException {
        this.mdfUrl = new URL(mdfUrl).toURI().resolve("/api/v1/").toString();;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
