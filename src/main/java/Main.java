
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.CalendarScopes;
import java.util.Arrays;
import java.util.Date;

import com.google.api.services.calendar.Calendar.Events;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;

public class Main {
    private static HttpTransport httpTransport;
    private static final JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();


    private String clientId="313550682051-9ikbjlh6hhe13f6j4qqm7k8fvt2k3o9k.apps.googleusercontent.com";


    private String clientSecret="jJceRhuiVjvCnsaGzAmooqzF";


    public static void main(String[] args) throws Exception {

        httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, "313550682051-0nlvnhnljl4guiv4elt556bm7shai0a1.apps.googleusercontent.com"
                ,"BqmAA1ghCTASBeMm5OUT5eZY",
                Arrays.asList(CalendarScopes.CALENDAR)).setAccessType("offline")
                .setApprovalPrompt("auto").build();

        String url = flow.newAuthorizationUrl().setRedirectUri("http://localhost:9000").build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(9000).build();
//        G
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String code = br.readLine();
//
//        TokenResponse response = flow.newTokenRequest(code)
//                .setRedirectUri("http://localhost:9000").execute();
//        GoogleCredential credential = new GoogleCredential()
//                .setFromTokenResponse(response);
//        GoogleCredential credential= new GoogleCredential.Builder()
//                .setTransport(httpTransport)
//                .setJsonFactory(jsonFactory)
//                .setClientSecrets("313550682051-9ikbjlh6hhe13f6j4qqm7k8fvt2k3o9k.apps.googleusercontent.com"
//                        ,"jJceRhuiVjvCnsaGzAmooqzF")
//               .build();

        Calendar service = new Calendar.Builder(httpTransport
                , jsonFactory
                , new AuthorizationCodeInstalledApp(flow, receiver).authorize("313550682051-0nlvnhnljl4guiv4elt556bm7shai0a1.apps.googleusercontent.com")).setApplicationName("applicationName").build();
        Events events = service.events();
        DateTime date1 = new DateTime("2017-05-05T16:30:00.000+05:30");
        DateTime date2 = new DateTime(new Date());
        com.google.api.services.calendar.model.Events eventList;
        eventList = events.list("primary").setTimeMin(date1).setTimeMax(date2).execute();
        System.out.println("My:" + eventList.getItems());
    }

}
