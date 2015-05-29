package net.coding.program.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cc191954 on 14-8-18.
 */
public class TopicObject extends BaseComment implements Serializable {
    public int child_count;
    public String current_user_role_id = "";
    public int parent_id;
    public ProjectObject project;
    public String project_id = "";
    public String title = "";
    public long updated_at;
    public List<TopicLabelObject> labels = new ArrayList<>();

    public TopicObject(JSONObject json) throws JSONException {
        super(json);

        child_count = json.optInt("child_count");
        current_user_role_id = json.optString("current_user_role_id");
        parent_id = json.optInt("parent_id");
        if (json.has("project")) {
            project = new ProjectObject(json.optJSONObject("project"));
        }
        project_id = json.optString("project_id");
        title = json.optString("title");
        updated_at = json.optLong("updated_at");

        {
            JSONArray array = json.optJSONArray("labels");
            for (int i = 0, n = array.length(); i < n; i++) {
                labels.add(new TopicLabelObject(array.getJSONObject(i)));
            }
        }
    }
}