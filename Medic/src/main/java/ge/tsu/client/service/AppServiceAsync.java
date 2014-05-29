package ge.tsu.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AppServiceAsync
{

    void logout(AsyncCallback<Void> callback);

	void logToServer(Throwable th, AsyncCallback<Void> async);


	/**
     * Utility class to get the RPC Async interface from client-side code
     */
    public static final class Util 
    { 
        private static AppServiceAsync instance;

        public static final AppServiceAsync getInstance()
        {
            if ( instance == null )
            {
                instance = (AppServiceAsync) GWT.create( AppService.class );
            }
            return instance;
        }

        private Util()
        {
            // Utility class should not be instanciated
        }
    }
}
