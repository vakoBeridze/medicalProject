package ge.tsu.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ge.tsu.client.service.AppService;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class AppServiceImpl extends RemoteServiceServlet implements AppService {

	@Override
	public void logout() {
		System.out.println("<<<<<<<<<<<<<<<<<<<<< Logout User: " + getThreadLocalRequest().getUserPrincipal().getName());
		getThreadLocalRequest().getSession().invalidate();
	}

	@Override
	public void logToServer(Throwable th) {
		System.out.println("Error: " + th.getMessage());
		th.printStackTrace();
	}


}
