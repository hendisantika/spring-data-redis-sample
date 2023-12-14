package com.hendisantika.rediscache.constant;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-redis-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/14/23
 * Time: 07:37
 * To change this template use File | Settings | File Templates.
 */
public class CommonConstant {
    public static final String TRANSACTION_ID = "TRX-ID";

    public final class FilterOperators {

        public static final String FILTER_EQUALS = "=";
        public static final String NOT_EQUALS = "!=";
        public static final String GREATER_THAN = ">";
        public static final String LESS_THAN = "<";
        public static final String GREATER_THAN_OR_EQUALS = ">=";
        public static final String LESS_THAN_OR_EQUALS = "<=";
        public static final String LIKE = "like";
        public static final String IN = "in";
        private FilterOperators() {
        }
    }

    public final class CommonExceptionMessage {
        public static final String FILTER_INVALID_FORMAT = "Filter Invalid format";

        private CommonExceptionMessage() {
        }
    }
}
