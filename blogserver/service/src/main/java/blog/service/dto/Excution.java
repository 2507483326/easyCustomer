package blog.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Epat on 2017/2/11.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Excution <T>{

    private boolean success;

    private T data;
}
