package me.study.resttemplate.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import me.study.resttemplate.error.CustomException;
import me.study.resttemplate.error.vo.InternalServerErrorVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class CustomResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() ||
               response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InternalServerErrorVO internalServerErrorVO = objectMapper.readValue(getResponseBody(response), InternalServerErrorVO.class);
        if (HttpStatus.INTERNAL_SERVER_ERROR == HttpStatus.valueOf(internalServerErrorVO.getStatus()) &&
            internalServerErrorVO.getError().equals("Internal Server Error")) {
            throw new CustomException();
        }

        DefaultResponseErrorHandler defaultResponseErrorHandler = new DefaultResponseErrorHandler();
        defaultResponseErrorHandler.handleError(response);
    }

    private byte[] getResponseBody(ClientHttpResponse response) {
        try {
            return FileCopyUtils.copyToByteArray(response.getBody());
        } catch (IOException ignored) {
        }
        return new byte[0];
    }
}
