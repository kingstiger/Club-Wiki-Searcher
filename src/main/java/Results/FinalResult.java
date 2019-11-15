package Results;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class FinalResult {
    private int queryPage;
    private String pageTitle;
    private String pageUrl;
}
