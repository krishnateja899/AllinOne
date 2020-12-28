package com.example.allinone.APIParse;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import fr.arnaudguyon.xmltojsonlib.XmlToJson;

public class XMLPost {
    public static final String TAG = "TAG";

    /**     API - POST XML:
     *
     * Method: POST
     * Endpoint: http://[host]/FlashOnline/rest/coleta/getconsulta
     * Content-type: application/xml
     *
     * REQUEST:
     *  <consultaColetaXML>
     * <coletaId>999999</coletaId>
     * <usuario>login.user</usuario>
     * <senha>password</senha>
     * </consultaColetaXML>
     *
     * RESPONSE:
     * <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
     * <coleta>
     * <statusRetorno>00</statusRetorno>
     * <coletaId>381168</coletaId>
     * <dnaColeta>0</dnaColeta>
     * <responsavel>RICARDO FERNANDO</responsavel>
     * <dtHoraFimCol>03/03/2016 18:00</dtHoraFimCol>
     * <dtHoraSolicitacao>02/03/2016 12:50</dtHoraSolicitacao>
     * <distanciaAlvoRT>5000</distanciaAlvoRT>
     * <franquia>SPO</franquia>
     * <clienteId>1542</clienteId>
     * <nomeCliente>BV Financeira</nomeCliente>
     * <tipoEncomenda>intelignt doc</tipoEncomenda>
     * <logradouro>Av Paulista</logradouro>
     * <numEnd>1374</numEnd>
     * <complEnd>9ºandar</complEnd>
     * <bairro>Bela Vista</bairro>
     * <cidade>São Paulo</cidade>
     * <uf>SP</uf>
     * <cep>1310916</cep>
     * <obs/>
     * </coleta>
     */

    //Convert the XML response to JSON using the library:
    // https://github.com/smart-fun/XmlToJson
    // You need to add the dependency : implementation 'com.github.smart-fun:XmlToJson:1.4.5'- In build.gradle App && maven { url "https://jitpack.io" }- In build.gradle Project.
    // And parse the JSON accordingly.
    // When the response is converted to JSON, this is how it looks:

    /**
     * XML TO JSON
     * {
     * "coleta": {
     * "obs": "",
     * "cidade": "São Paulo",
     * "dtHoraSolicitacao": "02\/03\/2016 12:50",
     * "bairro": "Bela Vista",
     * "numEnd": "1374",
     * "distanciaAlvoRT": "5000",
     * "cep": "1310916",
     * "uf": "SP",
     * "franquia": "SPO",
     * "statusRetorno": "00",
     * "clienteId": "1542",
     * "coletaId": "381168",
     * "dnaColeta": "0",
     * "dtHoraFimCol": "03\/03\/2016 18:00",
     * "logradouro": "Av Paulista",
     * "complEnd": "9ºandar",
     * "nomeCliente": "BV Financeira",
     * "responsavel": "RICARDO FERNANDO",
     * "tipoEncomenda": "intelignt doc"
     * }
     * }
     */

    private void InJson(int code) {
        RequestQueue queue = null;//Remove null when Implementing. Then add below Line.
        //queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, "ENTER URL", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = String.valueOf(response);
                XmlToJson xmlToJson = new XmlToJson.Builder(s).build();
                Log.e("TAG", "XMLtoJson: " + xmlToJson);
                JSONObject s1 = xmlToJson.toJson();
                try {
                    JSONObject object = s1.getJSONObject("coleta");
                    String s9 = object.getString("statusRetorno");
                    if (s9.equals("00")) {
                        String s2 = object.getString("coletaId");
                    } else {
                        Log.d(TAG, "onResponse: ");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "onErrorResponse: ");
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/xml; charset=" +
                        getParamsEncoding();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                String body = getXML(code, "sao.ricardos", "123");
                return body.getBytes();
            }
        };
        queue.add(request);
    }

    private String getXML(int code, String userName, String paso) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<consultaColetaXML>\n  <coletaId>" + code + "</coletaId>\n")
                .append("<usuario>" + userName + "</usuario>\n")
                .append("<senha>" + paso + "</senha>\n </consultaColetaXML>");
        String result = stringBuilder.toString();
        System.out.println(result);
        return result;
    }


}
