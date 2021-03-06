package org.onehippo.gogreen.android.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.onehippo.gogreen.android.data.Event;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Service for getting events from a local Hippo Go Green server.
 * @author Jeroen Reijn
 */
public class EventService {

    private static final String RESTAPI_BASE_URI = "http://10.0.2.2:8085/site/restapi";
    private static final String RESTAPI_RESPONSE_TYPE = "_type=json";

    /**
     * Get the upcoming events.
     *
     * @return a list of {@link org.onehippo.gogreen.android.data.Event} items
     */
    public static ArrayList<Event> getUpcomingEvents() {
        RestTemplate restTemplate = getRestTemplate();
        ArrayList<Event> events = new ArrayList<Event>();
        String url = RESTAPI_BASE_URI + "/events./?" + RESTAPI_RESPONSE_TYPE;

        Event[] eventsFromHippo = restTemplate.getForObject(url, Event[].class);
        events.addAll(Arrays.asList(eventsFromHippo));

        return events;
    }

    /**
     * Get the past events.
     *
     * @return a list of {@link Event} items
     */
    public static ArrayList<Event> getPastEvents() {
        RestTemplate restTemplate = getRestTemplate();
        ArrayList<Event> events = new ArrayList<Event>();
        String url = RESTAPI_BASE_URI + "/events./?" + RESTAPI_RESPONSE_TYPE;

        Event[] eventsFromHippo = restTemplate.getForObject(url, Event[].class);
        events.addAll(Arrays.asList(eventsFromHippo));

        return events;
    }


    /**
     * Get all events.
     *
     * @return a list of {@link Event} items
     */
    public static ArrayList<Event> getAllEventsFromHippo() {
        RestTemplate restTemplate = getRestTemplate();

        ArrayList<Event> events = new ArrayList<Event>();
        String url = RESTAPI_BASE_URI + "/events./?" + RESTAPI_RESPONSE_TYPE;

        Event[] eventsFromHippo = restTemplate.getForObject(url, Event[].class);
        events.addAll(Arrays.asList(eventsFromHippo));

        return events;
    }

    /**
     * Gets a new {@link RestTemplate}
     *
     * @return the {@link RestTemplate}
     */
    private static RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        if (!messageConverters.contains(MappingJacksonHttpMessageConverter.class)) {
            messageConverters.add(new MappingJacksonHttpMessageConverter());
        }
        return restTemplate;
    }
}
