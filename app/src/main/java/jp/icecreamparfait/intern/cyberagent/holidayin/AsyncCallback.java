package jp.icecreamparfait.intern.cyberagent.holidayin;

import java.util.List;

import br.com.condesales.models.Venue;

public interface AsyncCallback {

    void onPreExecute();
    void onPostExecute(List<Venue> result);
    void onProgressUpdate(int progress);
    void onCancelled();

}