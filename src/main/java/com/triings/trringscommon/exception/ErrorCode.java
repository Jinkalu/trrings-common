package com.triings.trringscommon.exception;

public class ErrorCode {

    public static final String SUCCESS= "SUCCESS";
    public static final  String FAILED= "FAILED";
    public static final String INVALID_REQUEST_PARAM = "ERR_RQ_PRM_103";
    public static final String FORBIDDEN = "ERR_FRBDN_104";
    public static final String BAD_CREDENTIALS = "ERR_BADCRD_105";
    public static final String OTP_EXPIRED="OTP_EXPIRED";
    public static final String UNAUTHORIZED = "ERR_UNAUTH_106";
    public static final String EXPIRED = "ERR_EXPAUTH_107";
    public static final String INVALID_JWT = "ERR_EXPAUTH_108";

    /* USERS */
    public static final String USER_ID_INVALID = "ERR_USR_101";
    public static  final String  USER_PHONE_DUPLICATED = "ERR_USR_MOB_102";
    public static  final String  USER_EMAIL_DUPLICATED = "ERR_USR_MAIL_103";
    public  static final String EMAIL_EXISTS_WITH_DIFFERENT_STATUS = "ERR_RQ_EMAIL_104";
    public static final String USER_NOT_REGISTERED = "ERR_USR_104";
    public static final String INVALID_USER_STATUS ="ERR_USR_STS_105";
    public static final String USER_NAME_DUPLICATED= "ERR_USR_NME_106";
    //    public static final String INVALID_REQUEST_PARAM = "ERR_RQ_PRM_103";//
    public static final String ARGUMENT_MISMATCH = "ERR_ARG_101";
    public static final String INVALID_INPUT_VALUE = "ERR_RQ_IN_102";
    public static final String PASSWORD_WRONG = "ERR_USR_PWD_101";
    public static  final String PASSWORD_DUPLICATED = "ERR_USR_PWD_102";
    public static  final String PASSWORD_MISMATCH = "ERR_USR_PWD_103";
    public  static final String PASSWORD_INVALID_INPUT = "ERR_USR_PWD_104";
    public  static final String ROLE_NOT_FOUND = "ERR_ROL_AUTH_101";
    public  static final String PERMISSION_NOT_FOUND = "ERR_PRM_AUTH_101";
    public  static final String GENDER_NOT_FOUND = "ERR_RQ_GDR_101";
    public  static final String DOB_NOT_FOUND = "ERR_RQ_DOB_101";
    public  static final String STATUS_NOT_FOUND = "ERR_RQ_STATUS_101";

    /* AUTH */
    public static final String USER_UNAUTHORIZED = "ERR_AUTH_103";

    /* COUNTRY */
    public static final String INVALID_COUNTRY_ID = "ERR_CNT_INV_101";
    public static final String COUNTRY_NAME_DUPLICATED = "ERR_CNT_INV_102";
    public static final String COUNTRY_ID_ASSOCIATED = "ERR_CNT_AST_103";
    /* CITY */
    public static final String INVALID_CITY_ID = "ERR_CTY_INV_101";
    public static final String INTEREST_NAME_DUPLICATED="ERR_CITY_NME_102";
    /* ROLE */
    public static final String ROLE_NAME_DUPLICATED = "ERR_ROLE_NME_101";
    /* ACCOUNT-TYPE */
    public static final String INVALID_ACCOUNT_TYPE = "ERR_ACNT_NME_101";
    /* DOCUMENT-TYPE */
    public static final String DOCUMENT_TYPE_NOT_FOUND  = "ERR_DOC_TYPE_101";
    public static final String DELETED_DOCUMENT_TYPE  = "ERR_DOC_TYPE_102";
    public static final String DOCUMENT_NAME_DUPLICATED = "ERR_DOC_TYPE_103";
    /* CLAIM- REQUEST */
    public static final String REQUEST_TYPE_NOT_FOUND  = "ERR_REQ_TYPE_101";
    public static final String REQUEST_TYPE_USER_REQUIRED  = "ERR_REQ_TYPE_102";
    public static final String INVALID_STATUS_CHANGE  = "ERR_REQ_TYPE_103";

    /* AVAILABILITY-TYPE */
    public static final String INVALID_AVAILABILITY_TYPE  = "ERR_AVL_TYPE_101";
    public static final String BLOCK_USER = "ERR_BLK_USER_101";

    /* APP-LANGUAGE */
    public static final String INVALID_LANGUAGE  = "ERR_LANG_101";
    public static final String  LANGUAGE_NAME_DUPLICATED ="ERR_LANG_102";
}
