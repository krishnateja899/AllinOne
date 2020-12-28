package com.example.allinone.APIParse;

import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PUTApi {
    public static final String TAG = "TAG";

    /**
     * API- PUT
     * <p>
     * Request:
     * {
     * "imei": 514515854152463,
     * "lista": 2055313,
     * "sistema": "PEGASUS",
     * "franquia": "SAO",
     * "entregas": [
     * {
     * "codHawb": "02305767706",
     * "dataHoraBaixa": "2020-12-03T15:26:22 ",
     * "latitude":" -23.214458905023452 ",
     * "longitude": "-46.86617505263801 ",
     * "nivelBateria": "98 ",
     * "tipoBaixa": "ENTREGA",
     * "foto": "true "
     * }
     * ]
     * }
     * <p>
     * Response:
     * {
     * "timestamp": "2020-12-26T08:46:47-03:00",
     * "statusCode": 200,
     * "statusMessage": "OK",
     * "message": "Processo de baixa foi concluído com sucesso",
     * "errors": [
     * {
     * "codHawb": "02305767706",
     * "mensagem": "O documento não foi encontrado"
     * }
     * ]
     * }
     */

    public void PutJsonRequest() {
        RequestQueue mQueue = null;//Remove null when Implementing. Then add below Line.
        //mQueue = Volley.newRequestQueue(context);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        JSONObject jsonObj1 = new JSONObject();
        try {
            jsonObj.put("codHawb", "0545424545");
            jsonObj.put("dataHoraBaixa", "02/12/2020 15:34");
            jsonObj.put("nivelBateria", "69");
            jsonObj.put("tipoBaixa", "type");
            jsonObj.put("foto", "true");
            jsonObj.put("latitude", "-20.6545151");
            jsonObj.put("longitude", "-26.5262568");
            jsonObj.put("idGrauParentesco", "251");
            jsonArray.put(jsonObj);

            jsonObj1.put("imei", "6302165265265");
            jsonObj1.put("franquia", "SAO");
            jsonObj1.put("sistema", "PEGSUS");
            jsonObj1.put("lista", "2055313");
            jsonObj1.put("entregas", jsonArray);

            Log.e(TAG, "PutJsonRequest: " + jsonObj1);

            final JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, "ENTER URL", jsonObj1, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String statusMessage = response.getString("statusMessage");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "onErrorResponse: ");
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> params = new HashMap<String, String>();
                    String auth1 = "Basic "
                            + Base64.encodeToString(("preferences.getUserName()" + ":" + "preferences.getPaso()").getBytes(),
                            Base64.NO_WRAP);
                    params.put("Authorization", auth1);
                    params.put("x-versao-rt", "3.8.10");
                    params.put("x-rastreador", "ricardo");
                    params.put("Content-Type", "application/json; charset=utf-8");
                    return params;
                }

                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
            };
            request.setTag(TAG);
            mQueue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
