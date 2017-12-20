package testappsample.curso.com.myfinalapp.Controller;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Administrador on 9/10/2015.
 */
public class ServiceController
{
    String url;
    int method;
    Map<String, Object> params;
    Map<String, String> header;
    Context context;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public void jsonObjectRequest(String url, int method, Map<String, Object> params, Response.Listener<JSONObject> response, Response.ErrorListener error) {
        this.url = url;
        this.method = method;
        this.params = params;
        String tag_json_obj = "json_obj_req";

        JsonObjectRequest objectRequest;

        if (this.getParams() != null) {
            objectRequest = new JsonObjectRequest(this.getMethod(), this.getUrl(), new JSONObject(this.getParams()), response, error);
        } else {
            objectRequest = new JsonObjectRequest(this.getMethod(), this.getUrl(), response, error);
        }

        AppController.getInstance().addToRequestQueue(objectRequest, tag_json_obj);

    }

    public void jsonObjectRequest(String url, int method, Map<String, Object> params, Map<String,String> headers, Context context, Response.Listener<JSONObject> response, Response.ErrorListener error, int timeoutMiliSec) {
        this.url = url;
        this.method = method;
        this.params = params;
        this.header = headers;
        this.context = context;
        String tag_json_obj = "json_obj_req";
        JsonObjectRequest objectRequest = null;

        if (this.getParams() != null) {

            objectRequest = new JsonObjectRequest(this.getMethod(), this.getUrl(), new JSONObject(this.getParams()), response, error){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return  header;
                }

            };


        } else {

            objectRequest = new JsonObjectRequest(this.getUrl(), null, response, error){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return  header;
                }

            };
        }

        AppController.getInstance().addToRequestQueue(objectRequest, tag_json_obj,timeoutMiliSec);
    }

    public void jsonArrayRequest(String url, int method, Map<String, Object> params, Response.Listener<JSONArray> response, Response.ErrorListener error) {
        this.url = url;
        this.method = method;
        this.params = params;

        String tag_json_arry = "json_array_req";

        JsonArrayRequest arrayRequest;

        if (this.getParams() != null) {
            arrayRequest = new JsonArrayRequest(this.getMethod(), this.getUrl(), new JSONObject(this.getParams()), response, error);
        } else {
            arrayRequest = new JsonArrayRequest(this.getMethod(), this.getUrl(), response, error);
        }
        //Arreglo []
        AppController.getInstance().addToRequestQueue(arrayRequest, tag_json_arry);
    }

    public void stringRequest(String url, int method, Map<String, Object> params, Map<String, String> headers, Context context, Response.Listener<String> response, Response.ErrorListener error) {
        this.url = url;
        this.method = method;
        this.params = params;
        this.header = headers;
        this.context = context;

        String tag_string_req = "string_req";

        StringRequest strReq;

        strReq = new StringRequest(this.getMethod(), this.getUrl(), response, error){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                return header;
            }

        };

        //String
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public void stringRequest(String url, int method, Map<String, Object> params, Response.Listener<String> response, Response.ErrorListener error) {
        this.url = url;
        this.method = method;
        this.params = params;

        String tag_string_req = "string_req";

        StringRequest strReq;

        strReq = new StringRequest(this.getMethod(), this.getUrl(), response, error);

        //String
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

}
