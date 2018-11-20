package graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator;

import android.util.Log;

import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import graphingcaculator.lcpsdysy.android.apps.com.graphingcalculator.Models.Formula;

/**
 * Created by Prasann Singhal on 4/7/2018.
 */

public class WikiSearchTermToFullUrlSvc {

    public static String getHttpsContent(String urlName) {

        String line;
        String result="";

        //String s1 = IPSTicket;
        //String s3 = IPSUserName;
        //String b1 = new String(s1.getBytes(Charset.defaultCharset()));
        //String b3 = new String(s3.getBytes(Charset.defaultCharset()));

        try {

            StringBuilder httpsContent = new StringBuilder();
            URL url = new URL(urlName);
            HttpsURLConnection httpsConnection = (HttpsURLConnection) url.openConnection();
            httpsConnection.setConnectTimeout(5000);
            httpsConnection.setRequestProperty("Content-Type", "application/json");
            httpsConnection.setRequestProperty("Accept", "application/json");
            //httpsConnection.setRequestProperty("IPSWSTicket", b1);
            //httpsConnection.addRequestProperty("IPSUserName", b3);

            httpsConnection.setHostnameVerifier(new AllowAllHostnameVerifier());

            int responseCode = httpsConnection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                InputStream inputStream = httpsConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = br.readLine()) != null) {
                    httpsContent.append(line);
                    Log.d("HTTPS", line);
                }
                httpsConnection.disconnect();
            }
            result = httpsContent.toString();
        }
        catch(Exception ex){
            Log.d("HTTPS", ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public static String getHttpContent(String urlName) {

        String line;
        String result="";
        //String s1 = IPSTicket;
        //String s3 = IPSUserName;
        //String b1 = new String(s1.getBytes(Charset.defaultCharset()));
        //String b3 = new String(s3.getBytes(Charset.defaultCharset()));
        try {
            StringBuilder httpsContent = new StringBuilder();
            URL url = new URL(urlName);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(5000);
            //urlConnection.setRequestProperty("IPSWSTicket", b1);
            //urlConnection.addRequestProperty("IPSUserName", b3);
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = br.readLine()) != null) {
                    httpsContent.append(line);
                    Log.d("HTTP", line);
                }
                urlConnection.disconnect();
            }
            result = httpsContent.toString();
        }
        catch(Exception ex){
            Log.d("HTTP", ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public static SearchResultModel getSearchResultsSummary(String term){


        SearchResultModel m = new SearchResultModel();

        try {
            String query = URLEncoder.encode(term, "utf-8");
            String url01 = "https://en.wikipedia.org/w/api.php?action=query&list=search&srsearch="+query+"&format=json&utf8=";
            URL url = null;
            String jSONResponse ="";

            url = new URL(url01);

            Long now_before = System.currentTimeMillis();
            Log.d("getSearchResultsSummary", now_before.toString());
            String s2 = getHttpsContent(url01);

            Long now_after = System.currentTimeMillis();
            Log.d("getSearchResultsSummary", now_after.toString());

            jSONResponse = s2;
            if (jSONResponse!=null && jSONResponse.length()>0){
                JSONObject response = new JSONObject(jSONResponse);
                JSONObject qry = response.optJSONObject("query");
                JSONArray ary = qry.optJSONArray("search");
                if (ary.length()>0){
                    JSONObject searchResult1 = ary.getJSONObject(0);
                    Long pageId = searchResult1.optLong("pageid");
                    String snippet = searchResult1.optString("snippet");

                    //SearchResultModel srm = new SearchResultModel();
                    m.pageId = pageId;
                    m.snippet = snippet;
                }
            }


        } catch (Exception e) {
            Log.d("getSearchResultsSummary",e.getMessage());
        }
        finally {
            Log.d("getSearchResultsSummary","In finally..");
        }

        return m;
    }

    public static SearchResultModel getURLPartFromPageId(SearchResultModel srm){


        SearchResultModel m = new SearchResultModel();


        try {

            m.pageId = srm.pageId;
            m.snippet = srm.snippet;

            String url01 = "https://en.wikipedia.org/w/api.php?action=query&prop=info&pageids="+srm.pageId+"&format=json&inprop=url";
            URL url = null;
            String jSONResponse ="";

            url = new URL(url01);

            Long now_before = System.currentTimeMillis();
            Log.d("getSearchResultsSummary", now_before.toString());
            String s2 = getHttpsContent(url01);

            Long now_after = System.currentTimeMillis();
            Log.d("getSearchResultsSummary", now_after.toString());

            jSONResponse = s2;
            if (jSONResponse!=null && jSONResponse.length()>0){
                JSONObject response = new JSONObject(jSONResponse);
                JSONObject qry = response.optJSONObject("query");
                JSONObject pages = qry.optJSONObject("pages");
                JSONObject pageIdObject = pages.optJSONObject(""+srm.pageId);
                String fullURL = pageIdObject.optString("fullurl");
                m.URLPart = fullURL.substring(fullURL.lastIndexOf('/')).trim();

            }


        } catch (Exception e) {
            Log.d("getURLPartFromPageId",e.getMessage());
        }
        finally {

        }

        return m;
    }

    public static Formula getFormula(SearchResultModel srm){

        String url01 = "https://en.wikipedia.org/api/rest_v1/page/summary"+srm.URLPart;
        URL url = null;
        String jSONResponse ="";
        Formula f = null;

        try {
            url = new URL(url01);

            Long now_before = System.currentTimeMillis();
            Log.d("getMonumentSummary", now_before.toString());
            String s2 = getHttpsContent(url01);

            Long now_after = System.currentTimeMillis();
            Log.d("getMonumentSummary", now_after.toString());

            jSONResponse = s2;
            if (jSONResponse!=null && jSONResponse.length()>0){
                f = new Formula(jSONResponse);
            }


        } catch (Exception e) {
            Log.d("getMonumentSummary",e.getMessage());
        }
        finally {

        }

        return f;
    }
}

