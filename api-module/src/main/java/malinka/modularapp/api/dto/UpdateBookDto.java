package malinka.modularapp.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is the update-book dto.
 *
 * @author Malinka Phann
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookDto {
    String title;
}
