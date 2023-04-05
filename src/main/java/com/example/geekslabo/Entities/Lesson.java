package com.example.geekslabo.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Lesson implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Lesson's name cant be blank")
    private String name;
    private String description;
    private String videoUrl;
    private String VideoTitle ;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


    public static String extractVideoId(String videoUrl) {
        try {
            URL url = new URL(videoUrl);
            String query = url.getQuery();
            String[] params = query.split("&");
            for (String param : params) {
                String[] keyValue = param.split("=");
                if (keyValue[0].equals("v")) {
                    return keyValue[1];
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private String getVideoTitle(String videoUrl) throws IOException, JSONException {

        String videoId = extractVideoId(videoUrl);

        String apiUrl = "https://www.googleapis.com/youtube/v3/videos?id=" + videoId +
                "&key=" + "AIzaSyCR4SsoVJupyUaHtjzZ1tclKKCpWDNvswg" + "&fields=items(snippet(title))&part=snippet";
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + connection.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (connection.getInputStream())));

        String output;
        StringBuilder result = new StringBuilder();

        while ((output = br.readLine()) != null) {
            result.append(output);
        }

        connection.disconnect();

        JSONObject jsonObject = new JSONObject(result.toString());
        JSONArray jsonArray = jsonObject.getJSONArray("items");

        String videoTitle = null;

        if (jsonArray.length() > 0) {
            videoTitle = jsonArray.getJSONObject(0).getJSONObject("snippet").getString("title");
        }

        return videoTitle;
    }


    public Lesson(String name, String description, String videoUrl, Course course) {
        this.name = name;
        this.description = description;
        this.videoUrl = videoUrl;
        this.course = course;
        try {
            this.VideoTitle = getVideoTitle(videoUrl);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }



}

