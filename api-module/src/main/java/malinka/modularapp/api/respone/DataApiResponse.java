package malinka.modularapp.api.respone;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This is the api response that contains data.
 *
 * @param <T> entity class
 *
 * @author Malinka Phann
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class DataApiResponse<T> extends ApiResponse {
    T data;
    @Builder(builderMethodName = "dataBuilder")
    public DataApiResponse(int status, String message, T data) {
        super(status, message);
        this.data = data;
    }
}
