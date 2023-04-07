package malinka.modularapp.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is the create-book dto.
 *
 * @author Malinka Phann
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookDto {
    String title;
}
