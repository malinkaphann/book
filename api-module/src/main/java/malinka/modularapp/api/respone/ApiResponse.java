package malinka.modularapp.api.respone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is the api response that does not contain any data.
 *
 * @author Malinka Phann
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    @Builder.Default
    int status = 0;

    @Builder.Default
    String message = "success";
}
