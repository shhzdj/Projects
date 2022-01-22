package com.example.b07projectapplication.data;

/**
 * A generic class that holds a result success w/ data or an error exception.
 */
public class Result_AUTOGEN<T> {
    // hide the private constructor to limit subclass types (Success, Error)
    private Result_AUTOGEN() {
    }

    @Override
    public String toString() {
        if (this instanceof Result_AUTOGEN.Success) {
            Result_AUTOGEN.Success success = (Result_AUTOGEN.Success) this;
            return "Success[data=" + success.getData().toString() + "]";
        } else if (this instanceof Result_AUTOGEN.Error) {
            Result_AUTOGEN.Error error = (Result_AUTOGEN.Error) this;
            return "Error[exception=" + error.getError().toString() + "]";
        }
        return "";
    }

    // Success sub-class
    public final static class Success<T> extends Result_AUTOGEN {
        private T data;

        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }
    }

    // Error sub-class
    public final static class Error extends Result_AUTOGEN {
        private Exception error;

        public Error(Exception error) {
            this.error = error;
        }

        public Exception getError() {
            return this.error;
        }
    }
}