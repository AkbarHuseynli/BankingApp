package org.example.customer.client;

//import feign.Response;
//import feign.codec.ErrorDecoder;
//import org.apache.commons.io.IOUtils;
//import org.example.account.exception.ExceptionMessage;
//import org.example.account.exception.TransactionNotFoundException;
//
//public class RetreiveMessageErrorDecoder implements ErrorDecoder {
//
//    private final ErrorDecoder errorDecoder = new Default();
//
//    @Override
//    public Exception decode(String methodKey, Response response) {
//        ExceptionMessage message = null;
//        try (InputStream body = response.body().asInputStream()){
//            message = new ExceptionMessage((String) response.headers().get("date").toArray()[0],
//                    response.status(),
//                    HttpStatus.resolve(response.status()).getReasonPhrase(),
//                    IOUtils.toString(body, StandardCharsets.UTF_8),
//                    response.request().url());
//
//        } catch (IOException exception) {
//            return new Exception(exception.getMessage());
//        }
//        switch (response.status()) {
//            case 404:
//                throw new TransactionNotFoundException(message);
//            default:
//                return errorDecoder.decode(methodKey, response);
//        }
//    }
//}