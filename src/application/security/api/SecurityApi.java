package application.security.api;

public class SecurityApi {
	
	public static final String OK = "OK";
	public static final String SOMETHING_GONE_WRONG = "Unclassified error";
	
	public static final String WRONG_USERNAME = "Username doesnt match requirements or null";
	public static final String REMOVAL_DENIED = "Removal not authorized";
	
	
	public static final String GUEST = "ROLE_GUEST";
	public static final String OWNER = "ROLE_OWNER";
	public static final String MANAGER = "ROLE_MANAGER";
	public static final String ADMIN = "ROLE_ADMIN";
	public static final String USER = "ROLE_USER";
	
	
	public static final String ACCOUNT_CREATED = "Account has been created";
	public static final String ACCOUNT_REMOVED = "Account has been removed";
	public static final String ACCOUNT_ALREADY_EXISTS = "Username already registered"; 
	public static final String ACCOUNT_DOESNT_EXIST = "Username doesnt exist";
	public static final String ACCOUNT_HAD_BEEN_ACTIVATED = "Account had been activated";
	public static final String ACCOUNT_IS_ACTIVE = "Account is already active";
	public static final String ACCOUNT_HAD_BEEN_REVOKED = "Account had been revoked";
	public static final String ACCOUNT_IS_REVOKED = "Account is already revoked";
	public static final String ACCOUNT_IS_REFRESHED = "Account is refreshed";
	
	public static final String ROLES_SET_IS_NULL = "Reles set is null";
	public static final String WRONG_ROLE = "Role doesnt match requirements or null";
	public static final String ROLE_NOT_ALLOWED = "Role doesnt match requirements or null";
	public static final String ROLE_IS_ALREADY_PRESENT = "Role is already present"; 
	public static final String ROLE_DOESNT_PRESENT = "Role doesnt present";
	public static final String ROLE_IS_ADDED = "Role is granted";
	public static final String ROLE_IS_REMOVED = "Role is deprived";
	
	public static final String	PASSWORD_IS_NULL = "Password doesnt match requirements or null";
	public static final String WRONG_PASSWORD = "Wrong login-password pair";
	public static final String PASSWORD_EXPIRED = "Password expired";
	public static final String PASSWORD_HAD_BEEN_ALREADY_USED = "Password had been alraedy used"; 
	public static final String PASSWORD_IS_USING_NOW = "Password is using now";
	public static final String PASSWORD_UPDATED = "Password is updated";
	
	public static final String DTO_IS_NULL = "DTO instance is null";

}
