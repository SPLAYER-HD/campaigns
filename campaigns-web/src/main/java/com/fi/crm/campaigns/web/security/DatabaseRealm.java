package com.fi.crm.campaigns.web.security;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.util.JdbcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.crm.campaigns.infrastructure.localization.ServiceLocator;
import com.fi.crm.campaigns.web.util.CustomCredentialsMatcher;

public class DatabaseRealm extends JdbcRealm {
	
	/**
	 * static logger for class DatabaseRealm
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseRealm.class);

	protected String jndiDataSourceName;

	public DatabaseRealm() {
		//This sentence is the password verification of rewriting the Shiro, let Shiro for verification of my own
		//setCredentialsMatcher(new CustomCredentialsMatcher());
	}

	public String getJndiDataSourceName() {
		return jndiDataSourceName;
	}

	public void setJndiDataSourceName(String jndiDataSourceName) {
		this.jndiDataSourceName = jndiDataSourceName;
		this.dataSource = ServiceLocator.getInstance(null).getDataSource(
				jndiDataSourceName);
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// identify account to log to
		UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
		String username = userPassToken.getUsername().toUpperCase();

		if (username == null) {
			LOGGER.debug("Username is null.");
			return null;
		}

		// read password hash and salt from db
		PasswdSalt passwdSalt = getPasswordForUser(username);

		if (passwdSalt == null) {
			LOGGER.debug("No account found for user [" + username + "]");
			return null;
		}
		
		// return salted credentials
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,
				passwdSalt.password, getName());
		//info.setCredentialsSalt(new SimpleByteSource(passwdSalt.salt));

		return info;
	}

	private PasswdSalt getPasswordForUser(String username) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Connection conn = null;
		try {
			LOGGER.error("start getPasswordForUser method ");
			LOGGER.error("authenticationQuery>>> "+authenticationQuery);
			conn = dataSource.getConnection();

			statement = conn.prepareStatement(authenticationQuery);
			statement.setString(1, username);
			statement.setString(2, username);

			resultSet = statement.executeQuery();

			boolean hasAccount = resultSet.next();
			if (!hasAccount)
				return null;

			String salt = null;
			String password = resultSet.getString(1);
			String encrypt = resultSet.getString(2);
			LOGGER.error("encrypt======"+encrypt);
			if(encrypt != null && !encrypt.equals("")){
				((CustomCredentialsMatcher)getCredentialsMatcher()).setEncrypt(true);
			}else{
				((CustomCredentialsMatcher)getCredentialsMatcher()).setEncrypt(false);
			}
			if (resultSet.next()) {
				throw new AuthenticationException(
						"More than one user row found for user [" + username
								+ "]. Usernames must be unique.");
			}

			return new PasswdSalt(password, salt);
		} catch (SQLException e) {
			final String message = "There was a SQL error while authenticating user ["
					+ username + "]";
				LOGGER.error(message, e);
				
			throw new AuthenticationException(message, e);

		} finally {
			JdbcUtils.closeResultSet(resultSet);
			JdbcUtils.closeStatement(statement);
			JdbcUtils.closeConnection(conn);
		}
	}
	
	/**
	 * Set the Password check.
	 */
//	@PostConstruct
//	public void initCredentialsMatcher() {
//		//This sentence is the password verification of rewriting the Shiro, let Shiro for verification of my own
//		setCredentialsMatcher(new CustomCredentialsMatcher());
//
//	}
}

class PasswdSalt {

	public String password;
	public String salt;

	public PasswdSalt(String password, String salt) {
		super();
		this.password = password;
		this.salt = salt;
	}
}
