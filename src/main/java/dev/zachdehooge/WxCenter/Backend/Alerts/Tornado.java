package dev.zachdehooge.WxCenter.Backend.Alerts;

import dev.zachdehooge.WxCenter.Backend.Records.Alert;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.net.URL;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class Tornado {

    private static final String TORNADO_URL = "https://api.weather.gov/alerts/active?status=actual&message_type=alert,update&event=tornado%20warning,tornado%20emergency";

    public List<Alert> getTornadoAlerts() {
        List<Alert> alertList = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(new URL(TORNADO_URL).openStream());
            JsonNode features = root.get("features");

            for (JsonNode feature : features) {
                JsonNode props = feature.get("properties");
                JsonNode parameters = props.get("parameters");

                String alertId = feature.path("id").asString("");
                String event = props.get("event").asString();
                String areaDesc = props.get("areaDesc").asString();
                String description = props.get("description").asString();
                String nwsOffice = props.get("senderName").asString();
                String tornadoDetection = getParam(parameters, "tornadoDetection");
                String tornadoDamageThreat = getParam(parameters, "tornadoDamageThreat");
                String expiresRaw = props.path("expires").asString(null);

//                Color color = event.toLowerCase().contains("warning") ? AmbientColors.WARNING : AmbientColors.WATCH;

                String expiresValue = "Unknown";
                OffsetDateTime expiresTime = null;
                if (expiresRaw != null && !expiresRaw.isBlank()) {
                    expiresTime = OffsetDateTime.parse(expiresRaw);
                    expiresValue = "<t:" + expiresTime.toEpochSecond() + ":R>";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return alertList;
    }

    private String getParam(JsonNode parameters, String key) {
        if (parameters == null || !parameters.has(key)) return "N/A";
        JsonNode arr = parameters.get(key);
        return (arr != null && arr.isArray() && arr.size() > 0) ? arr.get(0).asString() : "N/A";
    }
}