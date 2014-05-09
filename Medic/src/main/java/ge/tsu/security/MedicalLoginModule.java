package ge.tsu.security;

import org.apache.log4j.Logger;
import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.spi.UsernamePasswordLoginModule;

import javax.security.auth.login.LoginException;
import java.security.acl.Group;

/**
 * Created by Vako on 5/9/2014.
 */
public class MedicalLoginModule extends UsernamePasswordLoginModule {
    public static final String MEDICAL_SECURITY_DOMAIN = "MedicalSecurityDomain";
    private static final String MEDICAL_WEB_USER = "medical.web.user";

    Logger logger = Logger.getLogger(getClass());

    @Override
    protected String getUsersPassword() throws LoginException {
        logger.info("Attempt to login: " + super.getUsername());
        return super.getUsernameAndPassword()[1];
    }

    @Override
    protected Group[] getRoleSets() throws LoginException {

        // The declarative permissions
        Group roles = new SimpleGroup("Roles");

        Group[] groups = {roles};

        SimplePrincipal role = new SimplePrincipal(MEDICAL_WEB_USER);
        roles.addMember(role);

        return groups;
    }
}
