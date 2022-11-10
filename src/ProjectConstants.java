package demo.example.demo.constants;

import org.springframework.http.HttpStatus;

public class ProjectConstants {

    public static final String NATIVE_SELECT_QUERY = "{userName:?0}";
    public static final HttpStatus USER_EXISTS = HttpStatus.BAD_REQUEST;
    public static final String DUPLICATE_ENTRY_MESSAGE = "User already exists";
    public static final String CREATE_SUCCESS = "User successfully created";
    public static final String CREATE_FAILURE = "User creation failed";
    public static final String UPLOAD_URL = "/3/image";
    public static final String AUTHORIZATION = "Client-ID 1869d1af4875df7";

}
